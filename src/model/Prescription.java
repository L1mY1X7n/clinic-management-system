package model;


public class Prescription {
    private String prescriptionId;
    private String recordId;
    private String patientId;
    private String doctorId;
    private String date;
    private String medicineName;
    private String dosage;
    private String instructions;

    public Prescription(String prescriptionId, String recordId, String patientId,
            String doctorId, String date, String medicineName, String dosage) {
        this(prescriptionId, recordId, patientId, doctorId, date, medicineName,
                dosage, "");
    }

    public Prescription(String prescriptionId, String recordId, String patientId,
            String doctorId, String date, String medicineName, String dosage,
            String instructions) {
        this.prescriptionId = prescriptionId;
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String toFileString() {
        return prescriptionId + "," + recordId + "," + patientId + ","
                + doctorId + "," + date + "," + medicineName + "," + dosage
                + "," + instructions;
    }

    public static Prescription fromFileString(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 8) {
            throw new IllegalArgumentException("Invalid prescription format");
        }
        return new Prescription(parts[0], parts[1], parts[2], parts[3],
                parts[4], parts[5], parts[6], parts[7]);
    }

    @Override
    public String toString() {
        return prescriptionId + " | " + medicineName + " " + dosage;
    }
}
