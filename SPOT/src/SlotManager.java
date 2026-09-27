package slot;

import vehicle.Vehicle;

public class SlotManager {

    private Floor[] floors;
    private int     totalFloors;

    public SlotManager() {
        totalFloors = 3;
        floors      = new Floor[totalFloors];
        floors[0]   = new Floor("Ground", 10, 20, 5);
        floors[1]   = new Floor("B1",     10, 20, 5);
        floors[2]   = new Floor("B2",     10, 20, 5);
    }

    // Nearest-first allocation across floors
    public ParkingSlot allocateSlot(Vehicle vehicle) {
        String slotType = vehicle.getSlotType();
        for (int i = 0; i < totalFloors; i++) {
            ParkingSlot slot = floors[i].findAvailableSlot(slotType);
            if (slot != null) {
                slot.occupy(vehicle);
                return slot;
            }
        }
        return null; // parking full
    }

    public void freeSlot(ParkingSlot slot) {
        slot.free();
    }

    public void printDashboard() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println(  "║           LIVE SLOT DASHBOARD                ║");
        System.out.println(  "╠══════════════════════════════════════════════╣");
        System.out.printf(   "║  %-8s  %-12s  %-12s  %-8s  ║%n", "Floor", "Bike", "Car", "Truck");
        System.out.println(  "╠══════════════════════════════════════════════╣");

        for (int i = 0; i < totalFloors; i++) {
            Floor f = floors[i];
            String bike  = f.countAvailable("BIKE_SLOT")  + "/" + f.countTotal("BIKE_SLOT");
            String car   = f.countAvailable("CAR_SLOT")   + "/" + f.countTotal("CAR_SLOT");
            String truck = f.countAvailable("TRUCK_SLOT") + "/" + f.countTotal("TRUCK_SLOT");
            System.out.printf("║  %-8s  %-12s  %-12s  %-8s  ║%n",
                f.getFloorName(), bike, car, truck);
        }
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    public Floor[] getFloors()      { return floors; }
    public int     getTotalFloors() { return totalFloors; }
}
