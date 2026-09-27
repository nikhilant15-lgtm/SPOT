package reports;

import vehicle.Vehicle;

public class RevenueTracker {

    private double bikeRevenue;
    private double carRevenue;
    private double truckRevenue;

    private int bikeCount;
    private int carCount;
    private int truckCount;

    public RevenueTracker() {
        bikeRevenue  = 0;
        carRevenue   = 0;
        truckRevenue = 0;
        bikeCount    = 0;
        carCount     = 0;
        truckCount   = 0;
    }

    public void record(String vehicleType, double amount) {
        if (vehicleType.equals(Vehicle.TYPE_BIKE)) {
            bikeRevenue += amount;
            bikeCount++;
        } else if (vehicleType.equals(Vehicle.TYPE_CAR)) {
            carRevenue += amount;
            carCount++;
        } else if (vehicleType.equals(Vehicle.TYPE_TRUCK)) {
            truckRevenue += amount;
            truckCount++;
        }
    }

    public double getTotalRevenue() {
        return bikeRevenue + carRevenue + truckRevenue;
    }

    public void printReport() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println(  "║           REVENUE REPORT                 ║");
        System.out.println(  "╠══════════════════════════════════════════╣");
        System.out.printf(   "║  Bikes  : %3d vehicles | Rs. %-11.2f║%n", bikeCount,  bikeRevenue);
        System.out.printf(   "║  Cars   : %3d vehicles | Rs. %-11.2f║%n", carCount,   carRevenue);
        System.out.printf(   "║  Trucks : %3d vehicles | Rs. %-11.2f║%n", truckCount, truckRevenue);
        System.out.println(  "║  ──────────────────────────────────────  ║");
        System.out.printf(   "║  TOTAL  : %3d vehicles | Rs. %-11.2f║%n",
            bikeCount + carCount + truckCount, getTotalRevenue());
        System.out.println(  "╚══════════════════════════════════════════╝");
    }
}
