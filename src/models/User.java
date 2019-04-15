package models;

public class User {

    private String firstName;
    private String middleName;
    private String lastName;
	private String password;

    private int userID; // GENERATE userID while inserting using UserQueries
    private String contactNumber;

    public User() {}

    public User(String firstName, String middleName, String lastName, String contactNumber, String password) {

        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setPassword(password);

        setUserID(userID);
        setContactNumber(contactNumber);
    }

    public void setFirstName(String firstName) {this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setUserID(int userID) { this.userID =  userID; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
	public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return  firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }

    public int getUserID() { return userID; }
    public String getContactNumber() { return contactNumber; }
	public String getPassword() { return password; }
}
