package vehicle;

public abstract class Vehicle {

    public static final String TYPE_CAR   = "CAR";
    public static final String TYPE_BIKE  = "BIKE";
    public static final String TYPE_TRUCK = "TRUCK";

    private String vehicleNumber;
    private String ownerName;

    public Vehicle(String vehicleNumber, String ownerName) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName     = ownerName;
    }

    public abstract String getVehicleType();
    public abstract String getSlotType();
    public abstract double getHourlyRate();

    public String getVehicleNumber() { return vehicleNumber; }
    public String getOwnerName()     { return ownerName; }

    public String toString() {
        return "[" + getVehicleType() + "] " + vehicleNumber + " | Owner: " + ownerName;
    }
}
