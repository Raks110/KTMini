package models;

public class Business {

    private int businessID;
    private int managerID;
    private String name;

    public Business(int businessID, int managerID, String name) {
        this.businessID = businessID;
        this.managerID = managerID;
        this.name = name;
    }

    public int getBusinessID() { return businessID; }
    public int getManagerID() { return managerID; }

    public String getName() { return name; }

}
