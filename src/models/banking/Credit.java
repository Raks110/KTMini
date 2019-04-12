package models.banking;

import java.util.Date;

public class Credit {

    private int creditID;
    private int accountID;

    private double creditLimit; // changed from amountAvailble to creditLimit to avoid confusion
    private double amountRemanining;

    private Date startDate;
    private Date dueDate;

    private String status;

    public Credit(int creditID, int accountID, double creditLimit, double amountRemanining, Date startDate, Date dueDate, String status) {

        this.creditID = creditID;
        this.accountID = accountID;

        this.creditLimit = creditLimit;
        this.amountRemanining = amountRemanining;

        this.startDate = startDate;
        this.dueDate = dueDate;

        this.status = status;
    }

    public int getCreditID() {
        return creditID;
    }

    public int getAccountID() {
        return accountID;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getAmountRemanining() {
        return amountRemanining;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

}
