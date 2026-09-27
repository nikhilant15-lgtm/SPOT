package customer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SeasonPass {

    public static final String WEEKLY  = "WEEKLY  (Rs. 500)";
    public static final String MONTHLY = "MONTHLY (Rs. 1500)";

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

    private String passType;
    private long   issuedMillis;
    private long   expiryMillis;
    private double feePaid;

    public SeasonPass(String passType) {
        this.passType     = passType;
        this.issuedMillis = System.currentTimeMillis();

        if (passType.equals(WEEKLY)) {
            this.expiryMillis = issuedMillis + 7L  * 24 * 60 * 60 * 1000;
            this.feePaid      = 500.0;
        } else {
            this.expiryMillis = issuedMillis + 30L * 24 * 60 * 60 * 1000;
            this.feePaid      = 1500.0;
        }
    }

    public boolean isValid() {
        return System.currentTimeMillis() < expiryMillis;
    }

    public String getPassType()       { return passType; }
    public double getFeePaid()        { return feePaid; }
    public String getIssuedFormatted(){ return SDF.format(new Date(issuedMillis)); }
    public String getExpiryFormatted(){ return SDF.format(new Date(expiryMillis)); }
}
