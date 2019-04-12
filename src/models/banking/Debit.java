package models.banking;

import java.util.Date;

public class Debit {

    private int debitID;
    private int accountID;

    private double amountAvailable;

    private Date startDate;

    private String status;

    public Debit(int debitID, int accountID, double amountAvailable, Date startDate, String status) {

        this.debitID = debitID;
        this.accountID = accountID;

        this.amountAvailable = amountAvailable;

        this.startDate = startDate;

        this.status = status;
    }

    public int getDebitID() {
        return debitID;
    }

    public int getAccountID() {
        return accountID;
    }

    public double getAmountAvailable() {
        return amountAvailable;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getStatus() {
        return status;
    }


}
