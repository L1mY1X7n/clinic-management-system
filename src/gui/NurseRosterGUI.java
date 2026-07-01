package gui;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Roster;
import utils.FileHandler;

public class NurseRosterGUI extends JFrame {

    private JFrame previousScreen;
    private String nurseId;
    private JTable rosterTable;

    public NurseRosterGUI(JFrame previousScreen, String nurseId) {
        this.previousScreen = previousScreen;
        this.nurseId = nurseId;
        setupFrame();
        setupComponents();
        loadRosterData();
    }

    private void setupFrame() {
        setTitle("My Nurse Roster");
        setSize(760, 380);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupComponents() {
        rosterTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(rosterTable);
        scrollPane.setBounds(20, 20, 700, 250);
        add(scrollPane);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(430, 290, 120, 30);
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadRosterData();
            }
        });
        add(refreshButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(570, 290, 120, 30);
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

    private void loadRosterData() {
        String[] columns = {"Roster ID", "Date", "Doctor 1", "Doctor 2",
            "Nurse 1", "Nurse 2", "Nurse 3"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        ArrayList<String> lines = FileHandler.readFile("rosters.txt");

        for (int i = 0; i < lines.size(); i++) {
            try {
                Roster roster = Roster.fromFileString(lines.get(i));
                if (roster.getNurse1Id().equalsIgnoreCase(nurseId)
                        || roster.getNurse2Id().equalsIgnoreCase(nurseId)
                        || roster.getNurse3Id().equalsIgnoreCase(nurseId)) {
                    tableModel.addRow(new Object[]{roster.getRosterId(),
                        roster.getDate(), getStaffDisplayName(roster.getDoctor1Id()),
                        getStaffDisplayName(roster.getDoctor2Id()),
                        getStaffDisplayName(roster.getNurse1Id()),
                        getStaffDisplayName(roster.getNurse2Id()),
                        getStaffDisplayName(roster.getNurse3Id())});
                }
            } catch (Exception ex) {
                System.out.println("Skipped malformed roster row: " + lines.get(i));
            }
        }

        rosterTable.setModel(tableModel);
    }

    private String getStaffDisplayName(String staffId) {
        ArrayList<String> users = FileHandler.readFile("users.txt");

        for (int i = 0; i < users.size(); i++) {
            String[] parts = users.get(i).split(",", -1);
            if (parts.length == 7 && parts[0].equalsIgnoreCase(staffId)) {
                String fullName = parts[4].trim();
                if (!fullName.equals("")) {
                    return staffId + " - " + fullName;
                }
            }
        }

        return staffId;
    }
}
