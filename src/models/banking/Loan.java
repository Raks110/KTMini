package models.banking;

import java.util.Date;

public class Loan {

    private int loanID;
    private int accountID;

    private double amount;
    private float interest;

    private String status;
    private String collateral;


    private int trusteeID;

    private Date startDate;
    private Date endDate;

    public Loan(int loanID, int accountID, double amount, float interest, String status, String collateral,
                int trusteeID, Date startDate, Date endDate) {

        this.accountID = accountID;
        this.loanID = loanID;

        this.amount = amount;
        this.interest = interest;
        this.collateral = collateral;

        this.trusteeID = trusteeID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getLoanID() { return loanID; }
    public int getAccountID() { return accountID; }

    public double getAmount() { return amount; }
    public float getInterest() { return interest; }

    public String getStatus() { return status; }
    public String getCollateral() { return collateral; }

    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }


}
