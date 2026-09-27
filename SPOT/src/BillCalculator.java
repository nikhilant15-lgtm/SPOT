package exit;

import entry.Ticket;
import penalty.FineEngine;
import penalty.PenaltyRule;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillCalculator {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    // Default penalty rules: 15-min grace, 12-hr limit, Rs.50/hr overstay
    private static final FineEngine FINE_ENGINE =
        new FineEngine(new PenaltyRule(15, 12, 50.0));

    private Ticket ticket;
    private long   exitMillis;
    private double billableHours;
    private double baseFare;
    private double penalty;
    private double total;
    private boolean passHolder;

    public BillCalculator(Ticket ticket, boolean passHolder) {
        this.ticket     = ticket;
        this.passHolder = passHolder;
        this.exitMillis = System.currentTimeMillis();
        calculate();
    }

    private void calculate() {
        if (passHolder) {
            billableHours = 0;
            baseFare      = 0;
            penalty       = 0;
            total         = 0;
            return;
        }

        double durationMinutes = (exitMillis - ticket.getEntryMillis()) / (1000.0 * 60);
        billableHours = FINE_ENGINE.toBillableHours(durationMinutes);
        baseFare      = billableHours * ticket.getVehicle().getHourlyRate();
        penalty       = FINE_ENGINE.calculate(billableHours);
        total         = baseFare + penalty;
    }

    public void printReceipt() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println(  "║             PAYMENT RECEIPT              ║");
        System.out.println(  "╠══════════════════════════════════════════╣");
        System.out.printf(   "║  Ticket   : %-28s║%n", ticket.getTicketId());
        System.out.printf(   "║  Vehicle  : %-28s║%n", ticket.getVehicle().getVehicleNumber());
        System.out.printf(   "║  Slot     : %-28s║%n", ticket.getSlot().getSlotId());
        System.out.printf(   "║  Entry    : %-28s║%n", ticket.getEntryFormatted());
        System.out.printf(   "║  Exit     : %-28s║%n", SDF.format(new Date(exitMillis)));
        System.out.printf(   "║  Duration : %-28s║%n", (int) billableHours + " hrs (billable)");
        System.out.println(  "╠══════════════════════════════════════════╣");

        if (passHolder) {
            System.out.println("║  Season Pass  : ACTIVE — Rs. 0.00       ║");
        } else {
            System.out.printf( "║  Base Fare    : Rs. %-21.2f║%n", baseFare);
            System.out.printf( "║  Overstay     : Rs. %-21.2f║%n", penalty);
        }

        System.out.println(  "║  ──────────────────────────────────────  ║");
        System.out.printf(   "║  TOTAL        : Rs. %-21.2f║%n", total);
        System.out.println(  "╚══════════════════════════════════════════╝");
    }

    public double getTotal()   { return total; }
    public double getBaseFare(){ return baseFare; }
    public double getPenalty() { return penalty; }
}
