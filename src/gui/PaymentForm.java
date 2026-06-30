package gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Payment;
import utils.FileHandler;

public class PaymentForm extends JFrame {

    private JTextField paymentIdField;
    private JTextField appointmentIdField;
    private JTextField patientIdField;
    private JTextField dateField;
    private JTextField amountField;
    private JComboBox<String> paymentMethodComboBox;

    public PaymentForm() {
        setTitle("Make Payment");
        setSize(430, 390);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        paymentIdField = addField(panel, "Payment ID:");
        appointmentIdField = addField(panel, "Appointment ID:");
        patientIdField = addField(panel, "Patient ID:");
        dateField = addField(panel, "Date:");
        amountField = addField(panel, "Amount:");

        panel.add(new JLabel("Payment Method:"));
        paymentMethodComboBox = new JComboBox<String>(new String[]{"Cash",
            "Card", "Online"});
        panel.add(paymentMethodComboBox);

        JButton btnPay = new JButton("Confirm Payment");
        panel.add(btnPay);
        panel.add(new JLabel(""));

        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePayment();
            }
        });

        add(panel);
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void savePayment() {
        String paymentId = paymentIdField.getText().trim();
        String appointmentId = appointmentIdField.getText().trim();
        String patientId = patientIdField.getText().trim();
        String date = dateField.getText().trim();
        String amountText = amountField.getText().trim();
        String paymentMethod = paymentMethodComboBox.getSelectedItem().toString();

        if (paymentId.equals("") || appointmentId.equals("")
                || patientId.equals("") || date.equals("")
                || amountText.equals("")) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        if (containsComma(paymentId) || containsComma(appointmentId)
                || containsComma(patientId) || containsComma(date)) {
            JOptionPane.showMessageDialog(this, "Commas are not allowed.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Amount must be a positive number.");
            return;
        }

        if (paymentIdExists(paymentId)) {
            JOptionPane.showMessageDialog(this, "Payment ID already exists.");
            return;
        }

        Payment payment = new Payment(paymentId, appointmentId, patientId,
                date, amount, paymentMethod);
        FileHandler.appendToFile("payments.txt", payment.toFileString());
        JOptionPane.showMessageDialog(this, "Payment saved successfully.");
        dispose();
    }

    private boolean containsComma(String value) {
        return value.indexOf(',') >= 0;
    }

    private boolean paymentIdExists(String paymentId) {
        ArrayList<String> lines = FileHandler.readFile("payments.txt");
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",", -1);
            if (parts.length == 6 && parts[0].equalsIgnoreCase(paymentId)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentForm().setVisible(true);
            }
        });
    }
}
