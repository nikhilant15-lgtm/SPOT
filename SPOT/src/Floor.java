package slot;

public class Floor {

    private String       floorName;
    private ParkingSlot[] slots;
    private int          totalSlots;

    public Floor(String floorName, int bikeCount, int carCount, int truckCount) {
        this.floorName  = floorName;
        this.totalSlots = bikeCount + carCount + truckCount;
        this.slots      = new ParkingSlot[totalSlots];

        char prefix = floorName.charAt(0);
        int index   = 0;

        for (int i = 1; i <= bikeCount;  i++)
            slots[index++] = new ParkingSlot(prefix + "-B" + String.format("%02d", i), "BIKE_SLOT",  floorName);
        for (int i = 1; i <= carCount;   i++)
            slots[index++] = new ParkingSlot(prefix + "-C" + String.format("%02d", i), "CAR_SLOT",   floorName);
        for (int i = 1; i <= truckCount; i++)
            slots[index++] = new ParkingSlot(prefix + "-T" + String.format("%02d", i), "TRUCK_SLOT", floorName);
    }

    public ParkingSlot findAvailableSlot(String slotType) {
        for (int i = 0; i < totalSlots; i++) {
            if (slots[i].getSlotType().equals(slotType) && slots[i].isAvailable())
                return slots[i];
        }
        return null;
    }

    public int countAvailable(String slotType) {
        int count = 0;
        for (int i = 0; i < totalSlots; i++)
            if (slots[i].getSlotType().equals(slotType) && slots[i].isAvailable()) count++;
        return count;
    }

    public int countTotal(String slotType) {
        int count = 0;
        for (int i = 0; i < totalSlots; i++)
            if (slots[i].getSlotType().equals(slotType)) count++;
        return count;
    }

    public String        getFloorName()  { return floorName; }
    public ParkingSlot[] getSlots()      { return slots; }
    public int           getTotalSlots() { return totalSlots; }
}
