package model;

public class MedicalRecord {
    private String recordId;
    private String patientId;
    private String doctorId;
    private String date;
    private String diagnosis;
    private String treatment;
    private String notes;

    public MedicalRecord(String recordId, String patientId, String doctorId,
            String date, String diagnosis, String treatment) {
        this(recordId, patientId, doctorId, date, diagnosis, treatment, "");
    }

    public MedicalRecord(String recordId, String patientId, String doctorId,
            String date, String diagnosis, String treatment, String notes) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String toFileString() {
        return recordId + "," + patientId + "," + doctorId + "," + date + ","
                + diagnosis + "," + treatment + "," + notes;
    }

    public static MedicalRecord fromFileString(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid medical record format");
        }
        return new MedicalRecord(parts[0], parts[1], parts[2], parts[3],
                parts[4], parts[5], parts[6]);
    }

    @Override
    public String toString() {
        return recordId + " | Patient: " + patientId + " | " + diagnosis;
    }
}

