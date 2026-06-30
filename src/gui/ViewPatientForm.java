package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import utils.FileHandler;

public class ViewPatientForm extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public ViewPatientForm() {
        setTitle("View Patients");
        setSize(700, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"ID", "User", "Pass", "Role", "Name", "Phone", "Email"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
        panel.add(btnClose);
        add(panel, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        ArrayList<String> lines = FileHandler.readFile("users.txt");
        for (int i = 0; i < lines.size(); i++) {
            String[] row = lines.get(i).split(",", -1);
            if (row.length == 7 && row[3].equalsIgnoreCase("Patient")) {
                model.addRow(row);
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPatientForm().setVisible(true);
            }
        });
    }
}
