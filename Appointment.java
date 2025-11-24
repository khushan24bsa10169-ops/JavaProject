import java.io.Serializable;

public class Appointment implements Serializable {
    private String appointmentID;
    private String date;
    private String time;
    private Patient patient;

    public Appointment(String date, String time, Patient patient) {
        this.date = date;
        this.time = time;

        this.appointmentID = date + "_" + time;
        this.patient = patient;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID +
            " | Date: " + date +
            " | Time: " + time +
            " | Patient: " + patient.getName() + " (" + patient.getId() + ")";
    }
}