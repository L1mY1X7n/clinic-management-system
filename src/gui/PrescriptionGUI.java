package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Doctor;
import model.Prescription;
import service.DoctorService;


public class PrescriptionGUI extends JFrame {
    private Doctor loggedInDoctor;
    private DoctorService doctorService;
    private JTable jTable1;
    private JScrollPane jScrollPane1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField txtPatientId;
    private JTextField txtMedicineName;
    private JTextField txtDosage;
    private JTextField txtInstructions;
    private JButton btnSave;

    public PrescriptionGUI(Doctor loggedInDoctor) {
        this.loggedInDoctor = loggedInDoctor;
        this.doctorService = new DoctorService();
        initComponents();
        loadPrescriptionTable();
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jLabel1 = new JLabel("Patient ID");
        jLabel2 = new JLabel("Medicine");
        jLabel3 = new JLabel("Dosage");
        jLabel4 = new JLabel("Instructions");
        txtPatientId = new JTextField();
        txtMedicineName = new JTextField();
        txtDosage = new JTextField();
        txtInstructions = new JTextField();
        btnSave = new JButton("Save Prescription");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Prescriptions");
        setSize(850, 540);
        setLocationRelativeTo(null);
        setLayout(null);

        jScrollPane1.setBounds(20, 20, 790, 220);
        jScrollPane1.setViewportView(jTable1);
        add(jScrollPane1);

        jLabel1.setBounds(30, 270, 110, 25);
        add(jLabel1);
        txtPatientId.setBounds(150, 270, 240, 25);
        add(txtPatientId);

        jLabel2.setBounds(30, 305, 110, 25);
        add(jLabel2);
        txtMedicineName.setBounds(150, 305, 240, 25);
        add(txtMedicineName);

        jLabel3.setBounds(30, 340, 110, 25);
        add(jLabel3);
        txtDosage.setBounds(150, 340, 240, 25);
        add(txtDosage);

        jLabel4.setBounds(30, 375, 110, 25);
        add(jLabel4);
        txtInstructions.setBounds(150, 375, 240, 25);
        add(txtInstructions);

        btnSave.setBounds(460, 320, 180, 30);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePrescription();
            }
        });
        add(btnSave);
    }

    private void loadPrescriptionTable() {
        String[] columnNames = {"Prescription ID", "Patient ID",
            "Doctor ID", "Date", "Medicine", "Dosage", "Instructions"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        ArrayList<Prescription> prescriptions =
                doctorService.getPrescriptionsForDoctor(loggedInDoctor.getUserId());

        for (Prescription prescription : prescriptions) {
            model.addRow(new Object[]{prescription.getPrescriptionId(),
                prescription.getPatientId(), prescription.getDoctorId(), prescription.getDate(),
                prescription.getMedicineName(), prescription.getDosage(),
                prescription.getInstructions()});
        }
        jTable1.setModel(model);
    }

    private void savePrescription() {
        if (txtPatientId.getText().trim().isEmpty()
                || txtMedicineName.getText().trim().isEmpty()
                || txtDosage.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Patient ID, medicine, and dosage are required.");
            return;
        }
        Prescription prescription = new Prescription(
                doctorService.generatePrescriptionId(),
                txtPatientId.getText().trim(), loggedInDoctor.getUserId(),
                LocalDate.now().toString(),
                txtMedicineName.getText().trim(), txtDosage.getText().trim(),
                txtInstructions.getText().trim());
        doctorService.addPrescription(prescription);
        JOptionPane.showMessageDialog(this, "Prescription saved.");
        clearAllTextFields();
        loadPrescriptionTable();
    }

    private void clearAllTextFields() {
        txtPatientId.setText("");
        txtMedicineName.setText("");
        txtDosage.setText("");
        txtInstructions.setText("");
    }
}
