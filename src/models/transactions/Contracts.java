package models.transactions;

import java.util.Date;

public class Contracts implements Sendable {

    private int contractID;
    private int contracteeID;
    private int contractorID;

    private String title;
    private String terms;
    private String status;

    private double amount;

    private Date time;
    private float timePeriod;

    public Contracts(int contractID, int contracteeID, int contractorID, String title, String terms, String status, double amount, Date time, float timePeriod) {

        this.contractID = contractID;
        this.contracteeID = contracteeID;
        this.contractorID = contractorID;

        this.title = title;
        this.terms = terms;
        this.status = status;

        this.amount = amount;

        this.time = time;

        this.timePeriod = timePeriod;
    }

    public int getContractID() {
        return contractID;
    }

    public int getContracteeID() {
        return contracteeID;
    }

    public int getContractorID() {
        return contractorID;
    }

    public String getTitle() {
        return title;
    }

    public String getTerms() {
        return terms;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTime() {
        return time;
    }

    public float getTimePeriod() {
        return timePeriod;
    }


}
