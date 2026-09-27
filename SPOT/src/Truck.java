package vehicle;

public class Truck extends Vehicle {

    private static final double HOURLY_RATE = 40.0;

    public Truck(String vehicleNumber, String ownerName) {
        super(vehicleNumber, ownerName);
    }

    public String getVehicleType() { return TYPE_TRUCK; }
    public String getSlotType()    { return "TRUCK_SLOT"; }
    public double getHourlyRate()  { return HOURLY_RATE; }
}
