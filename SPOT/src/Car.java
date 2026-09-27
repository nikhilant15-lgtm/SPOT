package vehicle;

public class Car extends Vehicle {

    private static final double HOURLY_RATE = 20.0;

    public Car(String vehicleNumber, String ownerName) {
        super(vehicleNumber, ownerName);
    }

    public String getVehicleType() { return TYPE_CAR; }
    public String getSlotType()    { return "CAR_SLOT"; }
    public double getHourlyRate()  { return HOURLY_RATE; }
}
