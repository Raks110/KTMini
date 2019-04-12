package models;

public class UserAccount {

    private int accountID;
    private int userID;
    private int bankID;

    private String status;

    public UserAccount(int accountID, int userID, int bankID) {

        this.accountID = accountID;
        this.userID = userID;

        setStatus("active"); // "active" when UserAccount is added..

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getUserID() {
        return userID;
    }

    public int getBankID() {
        return bankID;
    }

    public String getStatus() {
        return status;
    }

}
