package customer;

public class Customer {

    private static int idCounter = 1;

    private String customerId;
    private String name;
    private String contact;
    private String vehicleNumber;  // linked vehicle
    private SeasonPass seasonPass; // null if no pass

    public Customer(String name, String contact, String vehicleNumber) {
        this.customerId    = "CUST-" + String.format("%03d", idCounter++);
        this.name          = name;
        this.contact       = contact;
        this.vehicleNumber = vehicleNumber;
        this.seasonPass    = null;
    }

    public void assignPass(SeasonPass pass) {
        this.seasonPass = pass;
        System.out.println("[PASS] Season pass assigned to " + name + " | Expires: " + pass.getExpiryFormatted());
    }

    public boolean hasValidPass() {
        return seasonPass != null && seasonPass.isValid();
    }

    public void print() {
        System.out.println("  ID      : " + customerId);
        System.out.println("  Name    : " + name);
        System.out.println("  Contact : " + contact);
        System.out.println("  Vehicle : " + vehicleNumber);
        System.out.println("  Pass    : " + (hasValidPass() ? "ACTIVE — " + seasonPass.getPassType() : "NONE"));
    }

    public String     getCustomerId()    { return customerId; }
    public String     getName()          { return name; }
    public String     getContact()       { return contact; }
    public String     getVehicleNumber() { return vehicleNumber; }
    public SeasonPass getSeasonPass()    { return seasonPass; }
}
