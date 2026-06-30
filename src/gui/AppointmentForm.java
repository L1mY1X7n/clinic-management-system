package gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Appointment;
import utils.FileHandler;

public class AppointmentForm extends JFrame {

    private JTextField txtApptId;
    private JTextField txtPatientId;
    private JTextField txtDocId;
    private JTextField txtNurseId;
    private JTextField txtDate;
    private JTextField txtTime;
    private JTextField txtType;
    private JComboBox<String> statusComboBox;

    public AppointmentForm() {
        setTitle("Book Appointment");
        setSize(440, 480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(9, 2, 10, 10));

        txtApptId = addField(panel, "Appointment ID:");
        txtPatientId = addField(panel, "Patient ID:");
        txtDocId = addField(panel, "Doctor ID:");
        txtNurseId = addField(panel, "Nurse ID:");
        txtDate = addField(panel, "Date:");
        txtTime = addField(panel, "Time (HH:MM):");
        txtType = addField(panel, "Appointment Type:");

        panel.add(new JLabel("Status:"));
        statusComboBox = new JComboBox<String>(new String[]{"Scheduled",
            "Ongoing", "Completed", "Cancelled"});
        panel.add(statusComboBox);

        JButton btnSave = new JButton("Save Appointment");
        panel.add(btnSave);
        panel.add(new JLabel(""));

        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAppointment();
            }
        });

        add(panel);
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void saveAppointment() {
        String appointmentId = txtApptId.getText().trim();
        String patientId = txtPatientId.getText().trim();
        String doctorId = txtDocId.getText().trim();
        String nurseId = txtNurseId.getText().trim();
        String date = txtDate.getText().trim();
        String time = txtTime.getText().trim();
        String type = txtType.getText().trim();
        String status = statusComboBox.getSelectedItem().toString();

        if (appointmentId.equals("") || patientId.equals("")
                || doctorId.equals("") || nurseId.equals("")
                || date.equals("") || time.equals("") || type.equals("")) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        if (containsComma(appointmentId) || containsComma(patientId)
                || containsComma(doctorId) || containsComma(nurseId)
                || containsComma(date) || containsComma(time)
                || containsComma(type)) {
            JOptionPane.showMessageDialog(this, "Commas are not allowed.");
            return;
        }

        if (!time.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Time must use HH:MM format.");
            return;
        }

        if (appointmentIdExists(appointmentId)) {
            JOptionPane.showMessageDialog(this, "Appointment ID already exists.");
            return;
        }

        Appointment appt = new Appointment(
                appointmentId, patientId, doctorId, nurseId, date, time, type,
                status);
        FileHandler.appendToFile("appointments.txt", appt.toFileString());
        JOptionPane.showMessageDialog(this, "Appointment booked successfully.");
        dispose();
    }

    private boolean containsComma(String value) {
        return value.indexOf(',') >= 0;
    }

    private boolean appointmentIdExists(String appointmentId) {
        ArrayList<String> lines = FileHandler.readFile("appointments.txt");
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",", -1);
            if (parts.length == 8 && parts[0].equalsIgnoreCase(appointmentId)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppointmentForm().setVisible(true);
            }
        });
    }
}
