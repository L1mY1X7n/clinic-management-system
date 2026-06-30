package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.awt.*;

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
        btnClose.addActionListener(e -> dispose());
        panel.add(btnClose);
        add(panel, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        File file = new File("data/users.txt"); 
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length >= 7) {
                    model.addRow(row);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Read failed");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new ViewPatientForm().setVisible(true));
    }
}
