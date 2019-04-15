package models;

import models.queries.UserAccountQueries;

public class UserAccount {

    private int accountID;
    private int UIN;
    private int bankID;
    private int balance;

    private String firstname;

    private String status;

    public UserAccount(int accountID, int UIN, int bankID, int balance) {

        this.accountID = accountID;
        this.UIN = UIN;
        this.bankID = bankID;
        this.balance = balance;

        firstname = UserAccountQueries.getName(UIN);

        setStatus("active"); // "active" when UserAccount is added..

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getUIN() {
        return UIN;
    }

    public int getBankID() {
        return bankID;
    }

    public int getBalance(){
    	return balance;
	}

	public void setBalance(int balance){
    	this.balance = balance;
	}

    public String getStatus() {
        return status;
    }

    public String getFirstName(){
    	return firstname;
	}

}
