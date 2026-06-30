/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wwndr
 */
public class Payment {

    private String paymentId;
    private String appointmentId;
    private String patientId;
    private String date;
    private double amount;
    private String paymentMethod;

    public Payment(String paymentId, String appointmentId, String patientId,
            String date, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.date = date;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String toFileString() {
        return paymentId + "," + appointmentId + "," + patientId + ","
                + date + "," + String.format("%.2f", amount) + ","
                + paymentMethod;
    }

    public static Payment fromFileString(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid payment format");
        }
        return new Payment(parts[0], parts[1], parts[2], parts[3],
                Double.parseDouble(parts[4]), parts[5]);
    }
}
