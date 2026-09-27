package exit;

import entry.Ticket;
import slot.SlotManager;
import reports.RevenueTracker;

public class ExitGate {

    private SlotManager    slotManager;
    private RevenueTracker revenueTracker;

    public ExitGate(SlotManager slotManager, RevenueTracker revenueTracker) {
        this.slotManager    = slotManager;
        this.revenueTracker = revenueTracker;
    }

    public void checkOut(Ticket ticket, boolean passHolder) {
        if (ticket == null) {
            System.out.println("[EXIT] Invalid ticket.");
            return;
        }

        BillCalculator bill = new BillCalculator(ticket, passHolder);
        bill.printReceipt();

        revenueTracker.record(ticket.getVehicle().getVehicleType(), bill.getTotal());

        slotManager.freeSlot(ticket.getSlot());
        System.out.println("[EXIT] Slot " + ticket.getSlot().getSlotId() + " is now FREE.");
    }
}
