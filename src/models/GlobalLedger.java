package models;

import models.queries.Queries;

public class GlobalLedger extends Queries {

    private double totalCashFlow;
    private float savingsInterest;
    private float loanInterest;

    private double loanAmount;
    private double creditAmount;
    private double debitAmount;

    // USER TRIGGERS TO UPDATE THIS


    public GlobalLedger(double totalCashFlow, float savingsInterest, float loanInterest, double loanAmount, double creditAmount, double debitAmount) {

        this.totalCashFlow = totalCashFlow;
        this.savingsInterest = savingsInterest;
        this.loanInterest = loanInterest;

        this.loanAmount = loanAmount;

        this.creditAmount = creditAmount;
        this.debitAmount = debitAmount;
    }

    public double getTotalCashFlow() {
        return totalCashFlow;
    }

    public float getSavingsInterest() {
        return savingsInterest;
    }

    public float getLoanInterest() {
        return loanInterest;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public double getDebitAmount() {
        return debitAmount;
    }



}
