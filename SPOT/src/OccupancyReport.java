package reports;

import slot.Floor;
import slot.SlotManager;

public class OccupancyReport {

    private SlotManager slotManager;

    public OccupancyReport(SlotManager slotManager) {
        this.slotManager = slotManager;
    }

    public void generate() {
        Floor[] floors = slotManager.getFloors();

        int totalBike = 0, usedBike = 0;
        int totalCar  = 0, usedCar  = 0;
        int totalTruck= 0, usedTruck= 0;

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println(  "║               OCCUPANCY REPORT                   ║");
        System.out.println(  "╠══════════════════════════════════════════════════╣");

        for (int i = 0; i < slotManager.getTotalFloors(); i++) {
            Floor f = floors[i];

            int bT = f.countTotal("BIKE_SLOT"),  bA = f.countAvailable("BIKE_SLOT");
            int cT = f.countTotal("CAR_SLOT"),   cA = f.countAvailable("CAR_SLOT");
            int tT = f.countTotal("TRUCK_SLOT"), tA = f.countAvailable("TRUCK_SLOT");

            System.out.printf("║  Floor: %-41s║%n", f.getFloorName());
            System.out.printf("║    Bikes  : %2d/%2d used   Cars  : %2d/%2d used   ║%n",
                bT - bA, bT, cT - cA, cT);
            System.out.printf("║    Trucks : %2d/%2d used%28s║%n", tT - tA, tT, "");
            System.out.println("║  ──────────────────────────────────────────────║");

            totalBike  += bT; usedBike  += bT - bA;
            totalCar   += cT; usedCar   += cT - cA;
            totalTruck += tT; usedTruck += tT - tA;
        }

        System.out.printf("║  TOTAL Bikes  : %2d/%2d   Cars : %2d/%2d  Trucks: %2d/%2d║%n",
            usedBike, totalBike, usedCar, totalCar, usedTruck, totalTruck);
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}
