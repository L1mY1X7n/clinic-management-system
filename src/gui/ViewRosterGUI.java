package gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Doctor;
import model.Roster;
import service.DoctorService;


public class ViewRosterGUI extends JFrame {
    private Doctor loggedInDoctor;
    private DoctorService doctorService;
    private JTable jTable1;
    private JScrollPane jScrollPane1;

    public ViewRosterGUI(Doctor loggedInDoctor) {
        this.loggedInDoctor = loggedInDoctor;
        this.doctorService = new DoctorService();
        initComponents();
        loadRosterTable();
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("My Roster");
        setSize(760, 360);
        setLocationRelativeTo(null);
        setLayout(null);

        jScrollPane1.setBounds(20, 20, 700, 280);
        jScrollPane1.setViewportView(jTable1);
        add(jScrollPane1);
    }

    private void loadRosterTable() {
        String[] columnNames = {"Roster ID", "Date", "Doctor 1", "Doctor 2",
            "Nurse 1", "Nurse 2", "Nurse 3"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        ArrayList<Roster> rosters = doctorService.getRosterForDoctor(loggedInDoctor.getUserId());

        for (Roster roster : rosters) {
            model.addRow(new Object[]{roster.getRosterId(), roster.getDate(),
                roster.getDoctor1Id(), roster.getDoctor2Id(), roster.getNurse1Id(),
                roster.getNurse2Id(), roster.getNurse3Id()});
        }
        jTable1.setModel(model);
    }
}
