import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class MainApplication {
    private static ClinicManager manager = new ClinicManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- Welcome to the Simple Clinic Appointment System ---");
        int choice;

        do {
            displayMenu();
            try {

                String input = scanner.nextLine();
                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1: registerPatient(); break;
                    case 2: scheduleAppointment(); break;
                    case 3: viewSchedule(); break;
                    case 4: cancelAppointment(); break;
                    case 5: viewPatientList(); break;
                    case 6:
                        manager.saveData();
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default: System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number from the menu.");
                choice = 0;
            }
        } while (choice != 6);
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\size--- Main Menu ---");
        System.out.println("1. Register Patient (FR01)");
        System.out.println("2. Schedule Appointment (FR02, FR03)");
        System.out.println("3. View Daily Schedule (FR04)");
        System.out.println("4. Cancel Appointment (FR05)");
        System.out.println("5. View Registered Patients (NEW)");
        System.out.println("6. Exit and Save Data");
        System.out.print("Enter your choice: ");
    }

    private static void registerPatient() {
        System.out.print("Enter Patient ID (e.g., P101): ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine().trim();

        if (!id.isEmpty() && !name.isEmpty() && !contact.isEmpty()) {
            manager.registerPatient(id, name, contact);
        } else {
            System.out.println("Registration failed: All fields are required.");
        }
    }

    private static void scheduleAppointment() {
        System.out.print("Enter Patient ID for booking: ");
        String patientId = scanner.nextLine().trim();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();
        System.out.print("Enter Time (HH:MM in 24h format, e.g., 09:30): ");
        String time = scanner.nextLine().trim();

        if (patientId.isEmpty() || date.isEmpty() || time.isEmpty()) {
            System.out.println("Booking failed: All fields are required.");
            return;
        }

        try {

            LocalDate.parse(date);
            LocalTime.parse(time);

            String result = manager.scheduleAppointment(patientId, date, time);
            System.out.println(result);

        } catch (DateTimeParseException e) {

            System.out.println("Input Error: Date or Time format is incorrect.");
            System.out.println("Please use YYYY-MM-DD for date and HH:MM for time (24h).");
        }
    }

    private static void viewSchedule() {
        System.out.print("Enter Date to view schedule (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();

        List<Appointment> appointments = manager.viewAppointmentsByDate(date);

        if (appointments.isEmpty()) {
            System.out.println("No appointments found for " + date + ".");
        } else {
            System.out.println("\size--- Schedule for " + date + " ---");
            for (Appointment appt : appointments) {
                System.out.println(appt);
            }
        }
    }

    private static void cancelAppointment() {
        System.out.print("Enter Appointment ID to cancel (Date_Time, e.g., 2025-11-25_09:30): ");
        String id = scanner.nextLine().trim();
        manager.cancelAppointment(id);
    }

    private static void viewPatientList() {
        List<Patient> patientList = manager.getAllPatients();
        if (patientList.isEmpty()) {
            System.out.println("No patients registered yet.");
        } else {
            System.out.println("\size--- Registered Patients (Sorted by ID) ---");
            for (Patient p : patientList) {
                System.out.println(p);
            }
        }
    }
}