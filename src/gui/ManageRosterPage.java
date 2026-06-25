package gui;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.User;
import utils.FileHandler;

public class ManageRosterPage extends JFrame {

    private JLabel titleLabel;
    private JLabel rosterIdLabel;
    private JLabel dateLabel;
    private JLabel doctor1Label;
    private JLabel doctor2Label;
    private JLabel nurse1Label;
    private JLabel nurse2Label;
    private JLabel nurse3Label;

    private JTextField rosterIdTextField;
    private JTextField dateTextField;
    private JComboBox<String> doctor1ComboBox;
    private JComboBox<String> doctor2ComboBox;
    private JComboBox<String> nurse1ComboBox;
    private JComboBox<String> nurse2ComboBox;
    private JComboBox<String> nurse3ComboBox;

    private JButton addButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton backButton;

    private JTable rosterTable;
    private JScrollPane rosterScrollPane;
    private DefaultTableModel rosterTableModel;

    private final String ROSTER_FILE = "rosters.txt";
    private final String USER_FILE = "users.txt";

    public ManageRosterPage() {
        setupFrame();
        setupComponents();
        loadStaffComboBoxes();
        setNextRosterId();
        displayRosters();
    }

    private void setupFrame() {
        setTitle("Manage Staff Roster");
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setupComponents() {
        titleLabel = new JLabel("Manage Staff Roster");
        titleLabel.setBounds(315, 20, 170, 25);
        add(titleLabel);

        rosterIdLabel = new JLabel("Roster ID:");
        rosterIdLabel.setBounds(40, 65, 100, 25);
        add(rosterIdLabel);

        rosterIdTextField = new JTextField();
        rosterIdTextField.setBounds(150, 65, 180, 25);
        rosterIdTextField.setEditable(false);
        add(rosterIdTextField);

        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(410, 65, 100, 25);
        add(dateLabel);

        dateTextField = new JTextField();
        dateTextField.setBounds(520, 65, 180, 25);
        add(dateTextField);

        doctor1Label = new JLabel("Doctor 1:");
        doctor1Label.setBounds(40, 115, 100, 25);
        add(doctor1Label);

        doctor1ComboBox = new JComboBox<String>();
        doctor1ComboBox.setBounds(150, 115, 180, 25);
        add(doctor1ComboBox);

        doctor2Label = new JLabel("Doctor 2:");
        doctor2Label.setBounds(410, 115, 100, 25);
        add(doctor2Label);

        doctor2ComboBox = new JComboBox<String>();
        doctor2ComboBox.setBounds(520, 115, 180, 25);
        add(doctor2ComboBox);

        nurse1Label = new JLabel("Nurse 1:");
        nurse1Label.setBounds(40, 165, 100, 25);
        add(nurse1Label);

        nurse1ComboBox = new JComboBox<String>();
        nurse1ComboBox.setBounds(150, 165, 180, 25);
        add(nurse1ComboBox);

        nurse2Label = new JLabel("Nurse 2:");
        nurse2Label.setBounds(410, 165, 100, 25);
        add(nurse2Label);

        nurse2ComboBox = new JComboBox<String>();
        nurse2ComboBox.setBounds(520, 165, 180, 25);
        add(nurse2ComboBox);

        nurse3Label = new JLabel("Nurse 3:");
        nurse3Label.setBounds(40, 215, 100, 25);
        add(nurse3Label);

        nurse3ComboBox = new JComboBox<String>();
        nurse3ComboBox.setBounds(150, 215, 180, 25);
        add(nurse3ComboBox);

        addButton = new JButton("Add Roster");
        addButton.setBounds(40, 270, 130, 35);
        add(addButton);

        deleteButton = new JButton("Delete Roster");
        deleteButton.setBounds(190, 270, 130, 35);
        add(deleteButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(340, 270, 110, 35);
        add(clearButton);

        backButton = new JButton("Back");
        backButton.setBounds(470, 270, 110, 35);
        add(backButton);

        String[] columns = {"Roster ID", "Date", "Doctor 1", "Doctor 2", "Nurse 1", "Nurse 2", "Nurse 3"};
        rosterTableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        rosterTable = new JTable(rosterTableModel);
        rosterTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        rosterScrollPane = new JScrollPane(rosterTable);
        rosterScrollPane.setBounds(40, 330, 700, 180);
        add(rosterScrollPane);

        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed();
            }
        });

        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed();
            }
        });

        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed();
            }
        });

        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed();
            }
        });

        rosterTable.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                tableRowSelected();
            }
        });
    }

    private void addButtonActionPerformed() {
        if (!isRosterInputValid()) {
            return;
        }

        String rosterLine = rosterIdTextField.getText().trim() + ","
                + dateTextField.getText().trim() + ","
                + getSelectedUserId(doctor1ComboBox) + ","
                + getSelectedUserId(doctor2ComboBox) + ","
                + getSelectedUserId(nurse1ComboBox) + ","
                + getSelectedUserId(nurse2ComboBox) + ","
                + getSelectedUserId(nurse3ComboBox);

        FileHandler.appendToFile(ROSTER_FILE, rosterLine);
        JOptionPane.showMessageDialog(this, "Roster added successfully.");
        clearFields();
        displayRosters();
    }

    private void deleteButtonActionPerformed() {
        if (rosterTable.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Select a roster from the table before deleting.");
            return;
        }

        int selectedRow = rosterTable.getSelectedRow();
        String rosterId = rosterTableModel.getValueAt(selectedRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this roster?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        ArrayList<String> lines = FileHandler.readFile(ROSTER_FILE);
        ArrayList<String> updatedLines = new ArrayList<String>();
        boolean found = false;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",", -1);

            if (parts.length >= 1 && parts[0].equals(rosterId)) {
                found = true;
            } else {
                updatedLines.add(line);
            }
        }

        if (found) {
            FileHandler.writeFile(ROSTER_FILE, updatedLines);
            JOptionPane.showMessageDialog(this, "Roster deleted successfully.");
            clearFields();
            displayRosters();
        } else {
            JOptionPane.showMessageDialog(this, "No roster found with that ID.");
        }
    }

    private void clearButtonActionPerformed() {
        clearFields();
    }

    private void backButtonActionPerformed() {
        AdminDashboard adminDashboard = new AdminDashboard();
        adminDashboard.setVisible(true);
        dispose();
    }

    private void tableRowSelected() {
        if (rosterTable.getSelectedRow() < 0) {
            return;
        }

        int selectedRow = rosterTable.getSelectedRow();

        rosterIdTextField.setText(rosterTableModel.getValueAt(selectedRow, 0).toString());
        dateTextField.setText(rosterTableModel.getValueAt(selectedRow, 1).toString());
        doctor1ComboBox.setSelectedItem(rosterTableModel.getValueAt(selectedRow, 2).toString());
        doctor2ComboBox.setSelectedItem(rosterTableModel.getValueAt(selectedRow, 3).toString());
        nurse1ComboBox.setSelectedItem(rosterTableModel.getValueAt(selectedRow, 4).toString());
        nurse2ComboBox.setSelectedItem(rosterTableModel.getValueAt(selectedRow, 5).toString());
        nurse3ComboBox.setSelectedItem(rosterTableModel.getValueAt(selectedRow, 6).toString());
    }

    private void loadStaffComboBoxes() {
        doctor1ComboBox.removeAllItems();
        doctor2ComboBox.removeAllItems();
        nurse1ComboBox.removeAllItems();
        nurse2ComboBox.removeAllItems();
        nurse3ComboBox.removeAllItems();

        ArrayList<String> lines = FileHandler.readFile(USER_FILE);

        for (int i = 0; i < lines.size(); i++) {
            User user = User.fromFileString(lines.get(i));

            if (user != null) {
                String item = user.getUserId() + " - " + user.getFullName();

                if (user.getRole().equalsIgnoreCase("Doctor")) {
                    doctor1ComboBox.addItem(item);
                    doctor2ComboBox.addItem(item);
                } else if (user.getRole().equalsIgnoreCase("Nurse")) {
                    nurse1ComboBox.addItem(item);
                    nurse2ComboBox.addItem(item);
                    nurse3ComboBox.addItem(item);
                }
            }
        }
    }

    private boolean isRosterInputValid() {
        String date = dateTextField.getText().trim();

        if (date.equals("")) {
            JOptionPane.showMessageDialog(this, "Date is required.");
            return false;
        }

        if (doctor1ComboBox.getItemCount() < 2 || doctor2ComboBox.getItemCount() < 2) {
            JOptionPane.showMessageDialog(this, "At least 2 doctors are required.");
            return false;
        }

        if (nurse1ComboBox.getItemCount() < 3
                || nurse2ComboBox.getItemCount() < 3
                || nurse3ComboBox.getItemCount() < 3) {
            JOptionPane.showMessageDialog(this, "At least 3 nurses are required.");
            return false;
        }

        String doctor1Id = getSelectedUserId(doctor1ComboBox);
        String doctor2Id = getSelectedUserId(doctor2ComboBox);
        String nurse1Id = getSelectedUserId(nurse1ComboBox);
        String nurse2Id = getSelectedUserId(nurse2ComboBox);
        String nurse3Id = getSelectedUserId(nurse3ComboBox);

        if (doctor1Id.equals(doctor2Id)) {
            JOptionPane.showMessageDialog(this, "Please select 2 different doctors.");
            return false;
        }

        if (nurse1Id.equals(nurse2Id)
                || nurse1Id.equals(nurse3Id)
                || nurse2Id.equals(nurse3Id)) {
            JOptionPane.showMessageDialog(this, "Please select 3 different nurses.");
            return false;
        }

        if (isDateAlreadyUsed(date)) {
            JOptionPane.showMessageDialog(this, "A roster already exists for this date.");
            return false;
        }

        return true;
    }

    private boolean isDateAlreadyUsed(String date) {
        ArrayList<String> lines = FileHandler.readFile(ROSTER_FILE);

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",", -1);

            if (parts.length >= 2 && parts[1].equals(date)) {
                return true;
            }
        }

        return false;
    }

    private String getSelectedUserId(JComboBox<String> comboBox) {
        Object selectedItem = comboBox.getSelectedItem();

        if (selectedItem == null) {
            return "";
        }

        String item = selectedItem.toString();
        int dashPosition = item.indexOf(" - ");

        if (dashPosition > 0) {
            return item.substring(0, dashPosition);
        } else {
            return item;
        }
    }

    private String getUserDisplayNameById(String userId) {
        ArrayList<String> lines = FileHandler.readFile(USER_FILE);

        for (int i = 0; i < lines.size(); i++) {
            User user = User.fromFileString(lines.get(i));

            if (user != null && user.getUserId().equals(userId)) {
                return user.getUserId() + " - " + user.getFullName();
            }
        }

        return userId;
    }

    private void displayRosters() {
        ArrayList<String> lines = FileHandler.readFile(ROSTER_FILE);
        rosterTableModel.setRowCount(0);

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",", -1);

            if (parts.length >= 7) {
                Object[] row = {
                    parts[0],
                    parts[1],
                    getUserDisplayNameById(parts[2]),
                    getUserDisplayNameById(parts[3]),
                    getUserDisplayNameById(parts[4]),
                    getUserDisplayNameById(parts[5]),
                    getUserDisplayNameById(parts[6])
                };

                rosterTableModel.addRow(row);
            }
        }
    }

    private void clearFields() {
        dateTextField.setText("");
        rosterTable.clearSelection();

        if (doctor1ComboBox.getItemCount() > 0) {
            doctor1ComboBox.setSelectedIndex(0);
        }

        if (doctor2ComboBox.getItemCount() > 0) {
            doctor2ComboBox.setSelectedIndex(0);
        }

        if (nurse1ComboBox.getItemCount() > 0) {
            nurse1ComboBox.setSelectedIndex(0);
        }

        if (nurse2ComboBox.getItemCount() > 0) {
            nurse2ComboBox.setSelectedIndex(0);
        }

        if (nurse3ComboBox.getItemCount() > 0) {
            nurse3ComboBox.setSelectedIndex(0);
        }

        setNextRosterId();
    }

    private void setNextRosterId() {
        rosterIdTextField.setText(getNextRosterId());
    }

    private String getNextRosterId() {
        ArrayList<String> lines = FileHandler.readFile(ROSTER_FILE);
        int biggestId = 0;

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",", -1);

            if (parts.length >= 1) {
                try {
                    int currentId = Integer.parseInt(parts[0]);

                    if (currentId > biggestId) {
                        biggestId = currentId;
                    }
                } catch (Exception e) {
                    // Ignore roster IDs that are not simple numbers.
                }
            }
        }

        return "" + (biggestId + 1);
    }

    public static void main(String[] args) {
        ManageRosterPage manageRosterPage = new ManageRosterPage();
        manageRosterPage.setVisible(true);
    }
}
