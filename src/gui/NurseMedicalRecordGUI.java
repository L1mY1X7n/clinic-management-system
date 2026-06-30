package gui;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.MedicalRecord;
import utils.FileHandler;

public class NurseMedicalRecordGUI extends JFrame {

    private JFrame previousScreen;
    private String nurseId;
    private JTextField recordIdField;
    private JTextField patientIdField;
    private JTextField doctorIdField;
    private JTextField dateField;
    private JTextField temperatureField;
    private JTextField bloodPressureField;
    private JTextField heartRateField;
    private JTextArea notesArea;

    public NurseMedicalRecordGUI(JFrame previousScreen, String nurseId) {
        this.previousScreen = previousScreen;
        this.nurseId = nurseId;
        setupFrame();
        setupComponents();
    }

    private void setupFrame() {
        setTitle("Record Patient Vitals");
        setSize(580, 520);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupComponents() {
        recordIdField = addTextField("Record ID:", 30);
        patientIdField = addTextField("Patient ID:", 70);
        doctorIdField = addTextField("Doctor ID:", 110);
        dateField = addTextField("Date:", 150);
        temperatureField = addTextField("Temperature (C):", 190);
        bloodPressureField = addTextField("Blood Pressure:", 230);
        heartRateField = addTextField("Heart Rate:", 270);

        JLabel notesLabel = new JLabel("Notes:");
        notesLabel.setBounds(30, 310, 130, 25);
        add(notesLabel);

        notesArea = new JTextArea();
        JScrollPane notesPane = new JScrollPane(notesArea);
        notesPane.setBounds(170, 310, 340, 80);
        add(notesPane);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(170, 420, 100, 30);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMedicalRecord();
            }
        });
        add(saveButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(290, 420, 100, 30);
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFields();
            }
        });
        add(clearButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(410, 420, 100, 30);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
                if (previousScreen != null) {
                    previousScreen.setVisible(true);
                }
            }
        });
        add(backButton);
    }

    private JTextField addTextField(String labelText, int yPosition) {
        JLabel label = new JLabel(labelText);
        label.setBounds(30, yPosition, 130, 25);
        add(label);

        JTextField textField = new JTextField();
        textField.setBounds(170, yPosition, 340, 25);
        add(textField);
        return textField;
    }

    private void saveMedicalRecord() {
        String recordId = recordIdField.getText().trim();
        String patientId = patientIdField.getText().trim();
        String doctorId = doctorIdField.getText().trim();
        String date = dateField.getText().trim();
        String temperature = temperatureField.getText().trim();
        String bloodPressure = bloodPressureField.getText().trim();
        String heartRate = heartRateField.getText().trim();
        String notes = notesArea.getText().trim();

        if (recordId.equals("") || patientId.equals("") || doctorId.equals("")
                || date.equals("") || temperature.equals("")
                || bloodPressure.equals("") || heartRate.equals("")) {
            JOptionPane.showMessageDialog(this, "Complete all required fields.");
            return;
        }

        if (containsComma(recordId) || containsComma(patientId)
                || containsComma(doctorId) || containsComma(date)
                || containsComma(notes)) {
            JOptionPane.showMessageDialog(this, "Commas are not allowed in the fields.");
            return;
        }

        try {
            Double.parseDouble(temperature);
            Integer.parseInt(heartRate);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Temperature and heart rate must be numbers.");
            return;
        }

        if (!bloodPressure.matches("\\d{2,3}/\\d{2,3}")) {
            JOptionPane.showMessageDialog(this,
                    "Blood pressure must look like 120/80.");
            return;
        }

        if (recordIdExists(recordId)) {
            JOptionPane.showMessageDialog(this, "Record ID already exists.");
            return;
        }

        String diagnosis = "Vitals " + temperature + "C " + bloodPressure
                + " " + heartRate + "bpm";
        String treatment = "Recorded by nurse " + nurseId;
        MedicalRecord record = new MedicalRecord(recordId, patientId, doctorId,
                date, diagnosis, treatment, notes);
        FileHandler.appendToFile("medical_records.txt", record.toFileString());
        JOptionPane.showMessageDialog(this, "Medical record saved successfully.");
        clearFields();
    }

    private boolean containsComma(String value) {
        return value.indexOf(',') >= 0;
    }

    private boolean recordIdExists(String recordId) {
        ArrayList<String> lines = FileHandler.readFile("medical_records.txt");
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",", -1);
            if (parts.length == 7 && parts[0].equalsIgnoreCase(recordId)) {
                return true;
            }
        }
        return false;
    }

    private void clearFields() {
        recordIdField.setText("");
        patientIdField.setText("");
        doctorIdField.setText("");
        dateField.setText("");
        temperatureField.setText("");
        bloodPressureField.setText("");
        heartRateField.setText("");
        notesArea.setText("");
    }
}
