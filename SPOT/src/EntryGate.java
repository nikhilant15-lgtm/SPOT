package entry;

import vehicle.Vehicle;
import slot.ParkingSlot;
import slot.SlotManager;

public class EntryGate {

    private SlotManager slotManager;

    public EntryGate(SlotManager slotManager) {
        this.slotManager = slotManager;
    }

    public Ticket checkIn(Vehicle vehicle) {
        ParkingSlot slot = slotManager.allocateSlot(vehicle);

        if (slot == null) {
            System.out.println("[ENTRY] Sorry! Parking FULL for " + vehicle.getVehicleType() + "s.");
            return null;
        }

        Ticket ticket = new Ticket(vehicle, slot);
        System.out.println("[ENTRY] Vehicle checked in successfully!");
        ticket.print();
        return ticket;
    }
}
