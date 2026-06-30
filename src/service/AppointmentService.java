package service;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Appointment;
import utils.FileHandler;

public class AppointmentService extends JFrame {

    private JTextField patientIdField;
    private JTextField doctorIdField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField typeField;

    public AppointmentService() {
        this("PT001");
    }

    public AppointmentService(String patientId) {
        setupFrame();
        setupComponents();
        patientIdField.setText(patientId);
    }

    private void setupFrame() {
        setTitle("Book Appointment");
        setSize(440, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupComponents() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        patientIdField = addField(panel, "Patient ID:");
        patientIdField.setEditable(false);
        doctorIdField = addField(panel, "Doctor ID:");
        dateField = addField(panel, "Date (YYYY-MM-DD):");
        timeField = addField(panel, "Time (HH:MM):");
        typeField = addField(panel, "Appointment Type:");

        JButton saveButton = new JButton("Book Appointment");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAppointment();
            }
        });
        panel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
        panel.add(cancelButton);

        add(panel);
    }

    private JTextField addField(JPanel panel, String labelText) {
        panel.add(new JLabel(labelText));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void saveAppointment() {
        String patientId = patientIdField.getText().trim();
        String doctorId = doctorIdField.getText().trim();
        String date = dateField.getText().trim();
        String time = timeField.getText().trim();
        String type = typeField.getText().trim();

        if (patientId.equals("") || doctorId.equals("") || date.equals("")
                || time.equals("") || type.equals("")) {
            JOptionPane.showMessageDialog(this, "Complete all fields.");
            return;
        }

        if (containsComma(patientId) || containsComma(doctorId)
                || containsComma(date) || containsComma(time)
                || containsComma(type)) {
            JOptionPane.showMessageDialog(this, "Commas are not allowed.");
            return;
        }

        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this,
                    "Date must use YYYY-MM-DD format.");
            return;
        }

        if (!time.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Time must use HH:MM format.");
            return;
        }

        Appointment appointment = new Appointment(generateAppointmentId(),
                patientId, doctorId, "Unassigned", date, time, type,
                "Scheduled");
        FileHandler.appendToFile("appointments.txt", appointment.toFileString());
        JOptionPane.showMessageDialog(this, "Appointment booked successfully.");
        clearFields();
        dispose();
    }

    private boolean containsComma(String value) {
        return value.indexOf(',') >= 0;
    }

    private String generateAppointmentId() {
        ArrayList<String> lines = FileHandler.readFile("appointments.txt");
        int highestNumber = 0;

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",", -1);
            if (parts.length == 8) {
                String id = parts[0].trim();
                try {
                    int number;
                    if (id.toUpperCase().startsWith("A")) {
                        number = Integer.parseInt(id.substring(1));
                    } else {
                        number = Integer.parseInt(id);
                    }
                    if (number > highestNumber) {
                        highestNumber = number;
                    }
                } catch (NumberFormatException ex) {
                    // Ignore IDs that do not end with a number.
                }
            }
        }

        return String.format("A%03d", highestNumber + 1);
    }

    public ArrayList<String[]> getAppointmentsByPatientId(String patientId) {
        ArrayList<String[]> appointments = new ArrayList<String[]>();
        ArrayList<String> lines = FileHandler.readFile("appointments.txt");

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",", -1);
            if (parts.length == 8 && parts[1].equalsIgnoreCase(patientId)) {
                appointments.add(parts);
            }
        }
        return appointments;
    }

    private void clearFields() {
        doctorIdField.setText("");
        dateField.setText("");
        timeField.setText("");
        typeField.setText("");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppointmentService().setVisible(true);
            }
        });
    }
}
