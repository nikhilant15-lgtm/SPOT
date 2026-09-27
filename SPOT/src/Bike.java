package vehicle;

public class Bike extends Vehicle {

    private static final double HOURLY_RATE = 10.0;

    public Bike(String vehicleNumber, String ownerName) {
        super(vehicleNumber, ownerName);
    }

    public String getVehicleType() { return TYPE_BIKE; }
    public String getSlotType()    { return "BIKE_SLOT"; }
    public double getHourlyRate()  { return HOURLY_RATE; }
}
