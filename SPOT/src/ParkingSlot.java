package slot;

import vehicle.Vehicle;

public class ParkingSlot {

    public static final String STATUS_FREE     = "FREE";
    public static final String STATUS_OCCUPIED = "OCCUPIED";

    private String slotId;
    private String slotType;   // "CAR_SLOT", "BIKE_SLOT", "TRUCK_SLOT"
    private String status;
    private String floorName;
    private Vehicle parkedVehicle;

    public ParkingSlot(String slotId, String slotType, String floorName) {
        this.slotId    = slotId;
        this.slotType  = slotType;
        this.floorName = floorName;
        this.status    = STATUS_FREE;
        this.parkedVehicle = null;
    }

    public boolean isAvailable() {
        return status.equals(STATUS_FREE);
    }

    public void occupy(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.status        = STATUS_OCCUPIED;
    }

    public void free() {
        this.parkedVehicle = null;
        this.status        = STATUS_FREE;
    }

    public String  getSlotId()        { return slotId; }
    public String  getSlotType()      { return slotType; }
    public String  getStatus()        { return status; }
    public String  getFloorName()     { return floorName; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }

    public String toString() {
        return slotId + " [" + slotType + "] - " + status;
    }
}
