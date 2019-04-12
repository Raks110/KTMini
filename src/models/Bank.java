package models;

public class Bank {

    private int bankID;
    private String name;

    public Bank(int bankID, String name) {
        this.bankID = bankID;
        this.name = name;
    }

    public String getName() { return name; }
    public int getBankID() { return bankID; }
}
