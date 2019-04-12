package models.address;

public class Address {

    private int buildingNumber;
    private String buildingName;
    private String street;
    private String city;
    private String state;

    private int pinCode;

    Address(int number, String name, String street, String city, String state) {
        this.buildingNumber = number;
        this.buildingName = name;
        this.street = street;
        this.state = state;
    }

    public String getAddress() {
        return Integer.toString(buildingNumber) + ", " + buildingName + "\n" + street + "\n" + city + "\n" + state;
    }
}

