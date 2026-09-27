package penalty;

public class PenaltyRule {

    private double gracePeriodMinutes;   // no charge within this window
    private int    overstayLimitHours;   // beyond this, overstay fine kicks in
    private double overstayFinePerHour;  // extra charge per hour after limit

    public PenaltyRule(double gracePeriodMinutes, int overstayLimitHours, double overstayFinePerHour) {
        this.gracePeriodMinutes  = gracePeriodMinutes;
        this.overstayLimitHours  = overstayLimitHours;
        this.overstayFinePerHour = overstayFinePerHour;
    }

    public double getGracePeriodMinutes()  { return gracePeriodMinutes; }
    public int    getOverstayLimitHours()  { return overstayLimitHours; }
    public double getOverstayFinePerHour() { return overstayFinePerHour; }
}
