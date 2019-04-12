package models.transactions;

import java.util.Date;

public class Appointments implements Sendable {

    private int appointmentID;
    private int businessID;
    private int userID;

    private Date time;

    private String status;

    public Appointments(int appointmentID, int businessID, int userID, Date time, String status) {

        this.appointmentID = appointmentID;
        this.businessID = businessID;
        this.userID = userID;

        this.time = time;

        this.status = status;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public int getBusinessID() {
        return businessID;
    }

    public int getUserID() {
        return userID;
    }

    public Date getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

}
