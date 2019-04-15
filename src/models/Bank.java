package models;

public class Bank {

    private int bankID;
    private String name;
    private int managerID;
    private String password;

    public Bank(String name,int managerID,String password) {
        this.name = name;
        this.managerID = managerID;
        this.password = password;
    }

    public String getName() { return name; }
    public int getBankID() { return bankID; }
}
