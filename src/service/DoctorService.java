package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import model.Appointment;
import model.Doctor;
import model.MedicalRecord;
import model.Prescription;
import model.Roster;
import utils.FileHandler;


public class DoctorService {
    public ArrayList<Doctor> loadAllDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFile("users.txt");

        for (String line : lines) {
            try {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",", -1);
                if (parts.length != 7) {
                    throw new InvalidRecordFormatException("Invalid users.txt row: " + line);
                }
                if ("Doctor".equalsIgnoreCase(parts[3])) {
                    doctors.add(new Doctor(parts[0], parts[1], parts[2], parts[3],
                            parts[4], parts[5], parts[6]));
                }
            } catch (InvalidRecordFormatException ex) {
                System.out.println("Skipped malformed user row: " + ex.getMessage());
            }
        }
        return doctors;
    }

    public Doctor findDoctorById(String doctorId) {
        for (Doctor doctor : loadAllDoctors()) {
            if (doctor.getUserId().equals(doctorId)) {
                return doctor;
            }
        }
        return null;
    }

    public ArrayList<Appointment> getAppointmentsForDoctor(String doctorId) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFile("appointments.txt");

        for (String line : lines) {
            try {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Appointment appointment = Appointment.fromFileString(line);
                LocalDate.parse(appointment.getDate());
                if (appointment.getDoctorId().equals(doctorId)) {
                    appointments.add(appointment);
                }
            } catch (Exception ex) {
                System.out.println("Skipped malformed appointment row: " + line);
            }
        }
        return appointments;
    }

    public ArrayList<Roster> getRosterForDoctor(String doctorId) {
        ArrayList<Roster> rosters = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFile("rosters.txt");

        for (String line : lines) {
            try {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Roster roster = Roster.fromFileString(line);
                LocalDate.parse(roster.getDate());
                if (roster.getDoctor1Id().equals(doctorId)
                        || roster.getDoctor2Id().equals(doctorId)) {
                    rosters.add(roster);
                }
            } catch (Exception ex) {
                System.out.println("Skipped malformed roster row: " + line);
            }
        }
        return rosters;
    }

    public ArrayList<MedicalRecord> getMedicalRecordsForPatient(String patientId) {
        ArrayList<MedicalRecord> records = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFile("medical_records.txt");

        for (String line : lines) {
            try {
                if (line.trim().isEmpty()) {
                    continue;
                }
                MedicalRecord record = MedicalRecord.fromFileString(line);
                LocalDate.parse(record.getDate());
                if (record.getPatientId().equals(patientId)) {
                    records.add(record);
                }
            } catch (Exception ex) {
                System.out.println("Skipped malformed medical record row: " + line);
            }
        }
        return records;
    }

    public ArrayList<MedicalRecord> getMedicalRecordsForDoctor(String doctorId) {
        ArrayList<MedicalRecord> records = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFile("medical_records.txt");

        for (String line : lines) {
            try {
                if (line.trim().isEmpty()) {
                    continue;
                }
                MedicalRecord record = MedicalRecord.fromFileString(line);
                LocalDate.parse(record.getDate());
                if (record.getDoctorId().equals(doctorId)) {
                    records.add(record);
                }
            } catch (Exception ex) {
                System.out.println("Skipped malformed medical record row: " + line);
            }
        }
        return records;
    }

    public void addMedicalRecord(MedicalRecord record) {
        FileHandler.appendToFile("medical_records.txt", record.toFileString());
    }

    public void updateMedicalRecord(MedicalRecord updatedRecord) {
        ArrayList<String> lines = FileHandler.readFile("medical_records.txt");
        ArrayList<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            try {
                MedicalRecord current = MedicalRecord.fromFileString(line);
                if (current.getRecordId().equals(updatedRecord.getRecordId())) {
                    updatedLines.add(updatedRecord.toFileString());
                } else {
                    updatedLines.add(line);
                }
            } catch (Exception ex) {
                updatedLines.add(line);
                System.out.println("Kept malformed medical record row unchanged: " + line);
            }
        }
        FileHandler.writeFile("medical_records.txt", updatedLines);
    }

    public void addPrescription(Prescription prescription) {
        FileHandler.appendToFile("prescriptions.txt", prescription.toFileString());
    }

    public ArrayList<Prescription> getPrescriptionsForDoctor(String doctorId) {
        ArrayList<Prescription> prescriptions = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFile("prescriptions.txt");

        for (String line : lines) {
            try {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Prescription prescription = Prescription.fromFileString(line);
                LocalDate.parse(prescription.getDate());
                if (prescription.getDoctorId().equals(doctorId)) {
                    prescriptions.add(prescription);
                }
            } catch (Exception ex) {
                System.out.println("Skipped malformed prescription row: " + line);
            }
        }
        return prescriptions;
    }

    public String generateMedicalRecordId() {
        Random random = new Random();
        return "MR" + (100 + random.nextInt(900));
    }

    public String generatePrescriptionId() {
        Random random = new Random();
        return "RX" + (100 + random.nextInt(900));
    }
}
