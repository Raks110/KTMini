package models.banking;

import java.util.Date;

public class Deposits {

    private int depositID;
    private int accountID;

    private float interest;

    private String status;

    private Date startDate;
    private Date endDate;


    public Deposits(int depositID, int accountID, float interest, String status, Date startDate, Date endDate) {

        this.depositID = depositID;
        this.accountID = accountID;

        this.interest = interest;

        this.status = status;

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getDepositID() {
        return depositID;
    }

    public int getAccountID() {
        return accountID;
    }

    public float getInterest() {
        return interest;
    }

    public String getStatus() {
        return status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
