package model;


public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String nurseId;
    private String date;
    private String time;
    private String type;
    private String status;

    public Appointment(String appointmentId, String patientId, String doctorId,
            String nurseId, String date, String time, String type,
            String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.nurseId = nurseId;
        this.date = date;
        this.time = time;
        this.type = type;
        this.status = status;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
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

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileString() {
        return appointmentId + "," + patientId + "," + doctorId + ","
                + nurseId + "," + date + "," + time + "," + type + ","
                + status;
    }

    public static Appointment fromFileString(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 8) {
            throw new IllegalArgumentException("Invalid appointment format");
        }
        return new Appointment(parts[0], parts[1], parts[2], parts[3],
                parts[4], parts[5], parts[6], parts[7]);
    }
}
