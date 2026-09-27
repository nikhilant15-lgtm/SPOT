package penalty;

public class FineEngine {

    private PenaltyRule rule;

    public FineEngine(PenaltyRule rule) {
        this.rule = rule;
    }

    // Returns fine amount; durationHours already accounts for grace period
    public double calculate(double durationHours) {
        if (durationHours > rule.getOverstayLimitHours()) {
            double extraHours = durationHours - rule.getOverstayLimitHours();
            return extraHours * rule.getOverstayFinePerHour();
        }
        return 0.0;
    }

    // Converts raw minutes to billable hours using grace period
    public double toBillableHours(double durationMinutes) {
        if (durationMinutes <= rule.getGracePeriodMinutes()) return 0;
        return Math.ceil(durationMinutes / 60.0);
    }

    public PenaltyRule getRule() { return rule; }
}
