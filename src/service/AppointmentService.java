package service;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class AppointmentService extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AppointmentService.class.getName());

    public AppointmentService() {
        initComponents();
        txtPatientId.setEditable(false);
        txtPatientId.setText(generatePatientId());
    }
    
    private boolean patientIdExists(String patientId) {
        return patientIdExistsInUsers(patientId) || patientIdExistsInAppointments(patientId);
    }
    
    private boolean patientIdExistsInAppointments(String patientId) {
        try {
            File file = new File("src/data/appointments.txt");
            if (!file.exists()) {
                return false;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length >= 2 && data[1].equals(patientId)) {
                    br.close();
                    return true;
                }
            }

            br.close();
        } catch (IOException e){
            JOptionPane.showMessageDialog(this, "Error checking appointment files.");
        }

        return false;
    }
    
    private String generatePatientId() {
        int number = 1;
        String patientId;

        do {
            patientId = String.format("PT%03d", number);
            number++;
        } while (patientIdExists(patientId));
        
        return patientId;
    }
    
    private String generateAppointmentId() {
        int count = 1;
        try {
            File file = new File("src/data/appointments.txt");
            if (!file.exists()){
                return "A001";
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (br.readLine() != null){
                count++;
            }
            br.close();
        }catch (IOException e){
            return "A001";
        }
        return String.format("A%03d", count);
    }
    
    private boolean patientIdExistsInUsers(String patientId) {
        try {
            File file = new File("src/data/users.txt");
            if (!file.exists()) {
                return false;
            }
        
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length >= 1 && data[0].equals(patientId)) {
                    br.close();
                    return true;
                }
            }
            
            br.close();
        } catch (IOException e){
            JOptionPane.showMessageDialog(this, "Error checking users file.");
        }
        return false;
    }
    
        private final String appointmentFile = "src/data/appointments.txt";

    public ArrayList<String[]> getAppointmentsByPatientId(String patientId) {
        ArrayList<String[]> appointmentList = new ArrayList<>();
        try {
            File file = new File(appointmentFile);
            if (!file.exists()) {
                return appointmentList;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length == 6 && data[1].equals(patientId)) {
                    appointmentList.add(new String[]{
                        data[0], // appointmentId
                        data[2], // doctorId
                        data[3], // date
                        data[4], // time
                        data[5]  // reason
                    });
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading appointments file.");
        }
        return appointmentList;
    }

    private void clearAppointmentForm() {
        txtPatientId.setText("");
        txtFullName.setText("");
        txtAppointmentDate.setText("");
        txtAppointmentTime.setText("");
        txtReason.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPatientId = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtFullName = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAppointmentTime = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAppointmentDate = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtReason = new javax.swing.JTextPane();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Patient ID");

        txtPatientId.setEditable(false);
        jScrollPane1.setViewportView(txtPatientId);

        jLabel2.setText("Full Name");

        jScrollPane2.setViewportView(txtFullName);

        jLabel3.setText("Appointment Date");

        jScrollPane3.setViewportView(txtAppointmentTime);

        jLabel4.setText("Appointment Time");

        jScrollPane4.setViewportView(txtAppointmentDate);

        jLabel5.setText("Reason");

        jScrollPane5.setViewportView(txtReason);

        btnSave.setText("Book Appointment");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(this::btnCancelActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {                                        
        String patientId = txtPatientId.getText().trim();
        String fullName = txtFullName.getText().trim();
        String appointmentDate = txtAppointmentDate.getText().trim();
        String appointmentTime = txtAppointmentTime.getText().trim();
        String reason = txtReason.getText().trim();

        if (patientId.isEmpty() || fullName.isEmpty() || appointmentDate.isEmpty() || appointmentTime.isEmpty() || reason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        if (patientId.contains(",") || fullName.contains(",") || appointmentDate.contains(",") || appointmentTime.contains(",") || reason.contains(",")) {
            JOptionPane.showMessageDialog(this, "Comma is not allowed.");
            return;
        }

        if (!appointmentDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Date must be in YYYY-MM-DD format.");
            return;
        }

        if (!appointmentTime.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Time must be in HH:MM format.");
           return;
        }
        
        String appointmentId = generateAppointmentId();

        String line = appointmentId + "," + patientId + "," + fullName + "," + appointmentDate + "," + appointmentTime + "," + reason + ",Pending";

        try {
            File file = new File("src/data/appointments.txt");
            File folder = file.getParentFile();

            if (folder != null && !folder.exists()) {
                folder.mkdirs();
            }

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(line);
            bw.newLine();
            bw.close();

            JOptionPane.showMessageDialog(this, "Appointment booked successfully.");

            clearAppointmentForm();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving appointment.");
        }
    }                                       

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
        clearAppointmentForm();
        dispose();
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
        java.awt.EventQueue.invokeLater(() -> new AppointmentService().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextPane txtAppointmentDate;
    private javax.swing.JTextPane txtAppointmentTime;
    private javax.swing.JTextPane txtFullName;
    private javax.swing.JTextPane txtPatientId;
    private javax.swing.JTextPane txtReason;
    // End of variables declaration                   
}
