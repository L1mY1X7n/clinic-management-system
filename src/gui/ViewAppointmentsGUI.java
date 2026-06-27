package gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Appointment;
import model.Doctor;
import service.DoctorService;

public class ViewAppointmentsGUI extends JFrame {
    private Doctor loggedInDoctor;
    private DoctorService doctorService;
    private JTable jTable1;
    private JScrollPane jScrollPane1;

    public ViewAppointmentsGUI(Doctor loggedInDoctor) {
        this.loggedInDoctor = loggedInDoctor;
        this.doctorService = new DoctorService();
        initComponents();
        loadAppointmentTable();
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("My Appointments");
        setSize(780, 360);
        setLocationRelativeTo(null);
        setLayout(null);

        jScrollPane1.setBounds(20, 20, 720, 280);
        jScrollPane1.setViewportView(jTable1);
        add(jScrollPane1);
    }

    private void loadAppointmentTable() {
        String[] columnNames = {"Appointment ID", "Patient ID", "Doctor ID",
            "Nurse ID", "Date", "Time", "Type", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        ArrayList<Appointment> appointments =
                doctorService.getAppointmentsForDoctor(loggedInDoctor.getUserId());

        for (Appointment appointment : appointments) {
            model.addRow(new Object[]{appointment.getAppointmentId(),
                appointment.getPatientId(), appointment.getDoctorId(),
                appointment.getNurseId(), appointment.getDate(),
                appointment.getTime(), appointment.getType(),
                appointment.getStatus()});
        }
        jTable1.setModel(model);
    }
}
