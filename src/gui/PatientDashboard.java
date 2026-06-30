package gui;

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import service.AppointmentService;

public class PatientDashboard extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(PatientDashboard.class.getName());
    private String patientId;
    private String patientName;

    public PatientDashboard() {
        this("PT001", "Patient");
    }

    public PatientDashboard(String patientId, String patientName) {
        this.patientId = patientId;
        this.patientName = patientName;
        initComponents();
        lblWelcome.setText("Welcome, " + patientName);
    }
    
    //View Appointments
    private void viewAppointments() {
        String[] columnNames = {"Appointment ID", "Patient ID", "Doctor ID",
            "Nurse ID", "Date", "Time", "Type", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        File file = new File("data/appointments.txt");

        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "appointments.txt file not found.");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (data.length == 8 && data[1].equalsIgnoreCase(patientId)) {
                    model.addRow(new Object[]{
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6],
                        data[7]
                    });
                }
            }

            br.close();

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No appointments found.");
                return;
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new java.awt.Dimension(750, 300));

            JOptionPane.showMessageDialog(this,scrollPane,"View Appointments",JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading appointments.txt.");
        }
    }

    //View Medical Records
    private void viewMedicalRecords() {
        String[] columnNames = {"Record ID","Patient ID","Doctor ID","Date","Diagnosis","Treatment","Notes"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        File file = new File("data/medical_records.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "medical_records.txt file not found.");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length == 7 && data[1].equalsIgnoreCase(patientId)) {
                    model.addRow(new Object[]{
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6]
                    });
                }
            }
            br.close();
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No medical records found.");
                return;
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new java.awt.Dimension(800, 300));
            JOptionPane.showMessageDialog(this,scrollPane,"Medical Records",JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading medical_records.txt.");
        }
    }

    //View Prescriptions
    private void viewPrescriptions() {
        String[] columnNames = {"Prescription ID","Patient ID","Doctor ID","Date","Medicine","Dosage","Instructions"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        File file = new File("data/prescriptions.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "prescriptions.txt file not found.");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length == 7 && data[1].equalsIgnoreCase(patientId)) {
                    model.addRow(new Object[]{
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6]
                    });
                }
            }
            br.close();
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No prescriptions found.");
                return;
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new java.awt.Dimension(850, 300));

            JOptionPane.showMessageDialog(this,scrollPane,"Prescriptions",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading prescriptions.txt.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnMedicalRecords = new javax.swing.JButton();
        btnBookAppointment = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnViewAppointments = new javax.swing.JButton();
        btnPrescriptions = new javax.swing.JButton();
        lblWelcome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnMedicalRecords.setText("Medical Records");
        btnMedicalRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicalRecordsActionPerformed(evt);
            }
        });

        btnBookAppointment.setText("Book Appointment");
        btnBookAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookAppointmentActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnViewAppointments.setText("View Appointments");
        btnViewAppointments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAppointmentsActionPerformed(evt);
            }
        });

        btnPrescriptions.setText("Prescriptions");
        btnPrescriptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrescriptionsActionPerformed(evt);
            }
        });

        lblWelcome.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBookAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnViewAppointments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnMedicalRecords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnPrescriptions, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(lblWelcome)
                .addGap(32, 32, 32)
                .addComponent(btnBookAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewAppointments)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMedicalRecords)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPrescriptions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogout)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void btnBookAppointmentActionPerformed(java.awt.event.ActionEvent evt) {
        AppointmentService AS = new AppointmentService(patientId);
        AS.setVisible(true);
    }

    private void btnViewAppointmentsActionPerformed(java.awt.event.ActionEvent evt) {
        viewAppointments();
    }

    private void btnMedicalRecordsActionPerformed(java.awt.event.ActionEvent evt) {
        viewMedicalRecords();
    }

    private void btnPrescriptionsActionPerformed(java.awt.event.ActionEvent evt) {
        viewPrescriptions();
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new LoginPage().setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnBookAppointment;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMedicalRecords;
    private javax.swing.JButton btnPrescriptions;
    private javax.swing.JButton btnViewAppointments;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblWelcome;
    // End of variables declaration
}
