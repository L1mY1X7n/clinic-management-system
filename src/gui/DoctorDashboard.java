package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import model.Doctor;


public class DoctorDashboard extends JFrame {
    private Doctor loggedInDoctor;
    private JLabel jLabel1;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;

    public DoctorDashboard(Doctor loggedInDoctor) {
        this.loggedInDoctor = loggedInDoctor;
        initComponents();
        jLabel1.setText("Welcome, Dr. " + loggedInDoctor.getFullName());
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Doctor Dashboard");
        setSize(420, 320);
        setLocationRelativeTo(null);
        setLayout(null);

        jLabel1.setBounds(30, 20, 340, 30);
        add(jLabel1);

        jButton1.setText("View Roster");
        jButton1.setBounds(110, 70, 180, 30);
        jButton1.addActionListener(evt -> new ViewRoster(loggedInDoctor).setVisible(true));
        add(jButton1);

        jButton2.setText("View Appointments");
        jButton2.setBounds(110, 110, 180, 30);
        jButton2.addActionListener(evt -> new ViewAppointments(loggedInDoctor).setVisible(true));
        add(jButton2);

        jButton3.setText("View Medical Records");
        jButton3.setBounds(110, 150, 180, 30);
        jButton3.addActionListener(evt -> new MedicalRecord(loggedInDoctor).setVisible(true));
        add(jButton3);

        jButton4.setText("Prescribe Medication");
        jButton4.setBounds(110, 190, 180, 30);
        jButton4.addActionListener(evt -> new PrescriptionGUI(loggedInDoctor).setVisible(true));
        add(jButton4);

        jButton5.setText("Logout");
        jButton5.setBounds(110, 230, 180, 30);
        jButton5.addActionListener(evt -> dispose());
        add(jButton5);
    }
}
