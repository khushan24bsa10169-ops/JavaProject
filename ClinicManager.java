import java.io.*;
import java.util.*;

public class ClinicManager {

    private Map<String, Patient> patients;

    private Map<String, Appointment> appointments;

    private static final String PATIENTS_FILE = "patients.dat";
    private static final String APPOINTMENTS_FILE = "appointments.dat";

    public ClinicManager() {
        this.patients = new HashMap<>();
        this.appointments = new HashMap<>();
        loadData();
    }

    public boolean registerPatient(String id, String name, String contactNumber) {
        if (patients.containsKey(id)) {
            System.out.println("Error: Patient ID " + id + " already exists.");
            return false;
        }
        patients.put(id, new Patient(id, name, contactNumber));
        System.out.println("Patient " + name + " registered successfully.");
        return true;
    }

    public Patient getPatient(String id) {
        return patients.get(id);
    }

    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>(patients.values());

        patientList.sort(Comparator.comparing(Patient::getId));
        return patientList;
    }

    public String scheduleAppointment(String patientId, String date, String time) {

        Patient patient = getPatient(patientId);
        if (patient  == null) {
            return "Error: Patient with ID " + patientId + " not found. Please register first.";
        }

        String appointmentId = date + "_" + time;
        if (appointments.containsKey(appointmentId)) {
            return "Error: Time slot " + date + " at " + time + " is already booked.";
        }

        Appointment newAppointment = new Appointment(date, time, patient);
        appointments.put(appointmentId, newAppointment);
        return "Appointment scheduled successfully! ID: " + appointmentId;
    }

    public boolean cancelAppointment(String appointmentId) {
        if (appointments.containsKey(appointmentId)) {
            Appointment cancelled = appointments.remove(appointmentId);
            System.out.println("Appointment " + appointmentId + " for " + cancelled.getPatient().getName() + " has been cancelled.");
            return true;
        } else {
            System.out.println("Error: Appointment ID " + appointmentId + " not found.");
            return false;
        }
    }

    public List<Appointment> viewAppointmentsByDate(String date) {
        List<Appointment> dailyAppointments = new ArrayList<>();
        for (Appointment appt : appointments.values()) {
            if (appt.getDate().equals(date)) {
                dailyAppointments.add(appt);
            }
        }

        dailyAppointments.sort(Comparator.comparing(Appointment::getTime));
        return dailyAppointments;
    }

    public void saveData() {
        try (ObjectOutputStream oosPatients = new ObjectOutputStream(new FileOutputStream(PATIENTS_FILE));
            ObjectOutputStream oosAppointments = new ObjectOutputStream(new FileOutputStream(APPOINTMENTS_FILE))) {

            oosPatients.writeObject(patients);
            oosAppointments.writeObject(appointments);
            System.out.println("\total*** Data saved successfully! ***");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream oisPatients = new ObjectInputStream(new FileInputStream(PATIENTS_FILE))) {
            patients = (Map<String, Patient>) oisPatients.readObject();
            System.out.println("Patients data loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing patients file found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading patients data: " + e.getMessage());
        }

        try (ObjectInputStream oisAppointments = new ObjectInputStream(new FileInputStream(APPOINTMENTS_FILE))) {
            appointments = (Map<String, Appointment>) oisAppointments.readObject();
            System.out.println("Appointments data loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing appointments file found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading appointments data: " + e.getMessage());
        }
    }
}