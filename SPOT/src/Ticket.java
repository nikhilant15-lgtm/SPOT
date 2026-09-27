package entry;

import vehicle.Vehicle;
import slot.ParkingSlot;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {

    private static int counter = 1;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private String       ticketId;
    private Vehicle      vehicle;
    private ParkingSlot  slot;
    private long         entryMillis;
    private String       entryFormatted;

    public Ticket(Vehicle vehicle, ParkingSlot slot) {
        this.ticketId       = "TKT-" + String.format("%04d", counter++);
        this.vehicle        = vehicle;
        this.slot           = slot;
        this.entryMillis    = System.currentTimeMillis();
        this.entryFormatted = SDF.format(new Date(entryMillis));
    }

    public void print() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println(  "║           PARKING TICKET             ║");
        System.out.println(  "╠══════════════════════════════════════╣");
        System.out.printf(   "║  Ticket  : %-26s║%n", ticketId);
        System.out.printf(   "║  Vehicle : %-26s║%n", vehicle.getVehicleNumber());
        System.out.printf(   "║  Type    : %-26s║%n", vehicle.getVehicleType());
        System.out.printf(   "║  Owner   : %-26s║%n", vehicle.getOwnerName());
        System.out.printf(   "║  Slot    : %-26s║%n", slot.getSlotId());
        System.out.printf(   "║  Floor   : %-26s║%n", slot.getFloorName());
        System.out.printf(   "║  Entry   : %-26s║%n", entryFormatted);
        System.out.println(  "╚══════════════════════════════════════╝");
    }

    public String      getTicketId()       { return ticketId; }
    public Vehicle     getVehicle()        { return vehicle; }
    public ParkingSlot getSlot()           { return slot; }
    public long        getEntryMillis()    { return entryMillis; }
    public String      getEntryFormatted() { return entryFormatted; }
}
