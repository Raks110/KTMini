package models;

import java.util.Date;

public class BusinessAccount {

    private int businessAccountID;
    private int businessID;
    private int bankID;

    private double amount;

    private Date startDate;
    private Date endDate;

    private String status;


    public BusinessAccount(int businessAccountID, int businessID, int bankID, double amount, Date startDate, Date endDate) {

        setBusinessAccountID(businessAccountID);
        setBusinessID(businessID);
        setBankID(bankID);
        setAmount(amount);
        setStartDate(startDate);
        setEndDate(endDate);

        setStatus("active");

    }

    public void setBusinessAccountID(int businessAccountID) { this.businessAccountID = businessAccountID; }
    public void setBusinessID(int businessID) { this.businessID = businessID; }
    public void setBankID(int bankID) { this.bankID = bankID; }
    public void setAmount(double amount) { this.amount = amount; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate;}

    public void setStatus(String status) { this.status = status; }

    public int getBusinessAccountID() { return businessAccountID; }
    public int getBusinessID() { return businessID; }
    public int getBankID() { return bankID; }
    public double getAmount() { return amount; }

    public Date getEndDate() { return endDate; }
    public Date getStartDate() {return startDate; }

    public String getStatus() { return status; }
}
