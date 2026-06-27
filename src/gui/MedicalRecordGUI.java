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
import model.MedicalRecord;
import service.DoctorService;


public class MedicalRecordGUI extends JFrame {
    private Doctor loggedInDoctor;
    private DoctorService doctorService;
    private JTable jTable1;
    private JScrollPane jScrollPane1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField txtPatientId;
    private JTextField txtDiagnosis;
    private JTextField txtTreatment;
    private JTextField txtNotes;
    private JButton btnSave;
    private JButton btnUpdate;

    public MedicalRecordGUI(Doctor loggedInDoctor) {
        this.loggedInDoctor = loggedInDoctor;
        this.doctorService = new DoctorService();
        initComponents();
        loadMedicalRecordTable();
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jLabel1 = new JLabel("Patient ID");
        jLabel2 = new JLabel("Diagnosis");
        jLabel3 = new JLabel("Treatment");
        jLabel4 = new JLabel("Notes");
        txtPatientId = new JTextField();
        txtDiagnosis = new JTextField();
        txtTreatment = new JTextField();
        txtNotes = new JTextField();
        btnSave = new JButton("Save");
        btnUpdate = new JButton("Update Selected");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Medical Records");
        setSize(820, 520);
        setLocationRelativeTo(null);
        setLayout(null);

        jScrollPane1.setBounds(20, 20, 760, 220);
        jScrollPane1.setViewportView(jTable1);
        add(jScrollPane1);

        jLabel1.setBounds(30, 270, 100, 25);
        add(jLabel1);
        txtPatientId.setBounds(140, 270, 220, 25);
        add(txtPatientId);

        jLabel2.setBounds(30, 305, 100, 25);
        add(jLabel2);
        txtDiagnosis.setBounds(140, 305, 220, 25);
        add(txtDiagnosis);

        jLabel3.setBounds(30, 340, 100, 25);
        add(jLabel3);
        txtTreatment.setBounds(140, 340, 220, 25);
        add(txtTreatment);

        jLabel4.setBounds(30, 375, 100, 25);
        add(jLabel4);
        txtNotes.setBounds(140, 375, 220, 25);
        add(txtNotes);

        btnSave.setBounds(420, 285, 150, 30);
        btnSave.addActionListener(evt -> saveMedicalRecord());
        add(btnSave);

        btnUpdate.setBounds(420, 330, 150, 30);
        btnUpdate.addActionListener(evt -> updateSelectedMedicalRecord());
        add(btnUpdate);
    }

    private void loadMedicalRecordTable() {
        String[] columnNames = {"Record ID", "Patient ID", "Doctor ID", "Date",
            "Diagnosis", "Treatment", "Notes"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        ArrayList<MedicalRecord> records =
                doctorService.getMedicalRecordsForDoctor(loggedInDoctor.getUserId());

        for (MedicalRecord record : records) {
            model.addRow(new Object[]{record.getRecordId(), record.getPatientId(),
                record.getDoctorId(), record.getDate(), record.getDiagnosis(),
                record.getTreatment(), record.getNotes()});
        }
        jTable1.setModel(model);
    }

    private void saveMedicalRecord() {
        if (!validateForm()) {
            return;
        }
        MedicalRecord record = new MedicalRecord(doctorService.generateMedicalRecordId(),
                txtPatientId.getText().trim(), loggedInDoctor.getUserId(),
                LocalDate.now().toString(), txtDiagnosis.getText().trim(),
                txtTreatment.getText().trim(), txtNotes.getText().trim());
        doctorService.addMedicalRecord(record);
        JOptionPane.showMessageDialog(this, "Medical record saved.");
        clearAllTextFields();
        loadMedicalRecordTable();
    }

    private void updateSelectedMedicalRecord() {
        int row = jTable1.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a record to update.");
            return;
        }
        if (!validateForm()) {
            return;
        }
        String recordId = jTable1.getValueAt(row, 0).toString();
        MedicalRecord record = new MedicalRecord(recordId, txtPatientId.getText().trim(),
                loggedInDoctor.getUserId(), LocalDate.now().toString(),
                txtDiagnosis.getText().trim(), txtTreatment.getText().trim(),
                txtNotes.getText().trim());
        doctorService.updateMedicalRecord(record);
        JOptionPane.showMessageDialog(this, "Medical record updated.");
        clearAllTextFields();
        loadMedicalRecordTable();
    }

    private boolean validateForm() {
        if (txtPatientId.getText().trim().isEmpty()
                || txtDiagnosis.getText().trim().isEmpty()
                || txtTreatment.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Patient ID, diagnosis, and treatment are required.");
            return false;
        }
        return true;
    }

    private void clearAllTextFields() {
        txtPatientId.setText("");
        txtDiagnosis.setText("");
        txtTreatment.setText("");
        txtNotes.setText("");
    }
}
