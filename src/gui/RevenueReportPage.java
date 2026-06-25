package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RevenueReportPage extends JFrame {

    private JLabel titleLabel;
    private JLabel reportTypeLabel;
    private JLabel inputValueLabel;
    private JLabel inputHelpLabel;
    private JLabel totalRevenueLabel;

    private JComboBox<String> reportTypeComboBox;
    private JTextField inputValueTextField;
    private JButton generateButton;
    private JButton backButton;
    private JTextArea reportTextArea;
    private JScrollPane reportScrollPane;

    private final String PAYMENT_FILE = "data/payments.txt";

    public RevenueReportPage() {
        setupFrame();
        setupComponents();
    }

    private void setupFrame() {
        setTitle("Revenue Report");
        setSize(700, 560);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setupComponents() {
        titleLabel = new JLabel("Revenue Report");
        titleLabel.setBounds(290, 20, 150, 25);
        add(titleLabel);

        reportTypeLabel = new JLabel("Report Type:");
        reportTypeLabel.setBounds(60, 70, 120, 25);
        add(reportTypeLabel);

        reportTypeComboBox = new JComboBox<String>();
        reportTypeComboBox.setBounds(180, 70, 160, 25);
        reportTypeComboBox.addItem("Daily");
        reportTypeComboBox.addItem("Monthly");
        reportTypeComboBox.addItem("Quarterly");
        reportTypeComboBox.addItem("Yearly");
        add(reportTypeComboBox);

        inputValueLabel = new JLabel("Input Value:");
        inputValueLabel.setBounds(60, 115, 120, 25);
        add(inputValueLabel);

        inputValueTextField = new JTextField();
        inputValueTextField.setBounds(180, 115, 160, 25);
        add(inputValueTextField);

        inputHelpLabel = new JLabel("Daily: YYYY-MM-DD | Monthly: YYYY-MM | Quarterly: YYYY-Q1 | Yearly: YYYY");
        inputHelpLabel.setBounds(60, 150, 560, 25);
        add(inputHelpLabel);

        generateButton = new JButton("Generate Report");
        generateButton.setBounds(180, 195, 160, 35);
        add(generateButton);

        totalRevenueLabel = new JLabel("Total Revenue: RM 0.00");
        totalRevenueLabel.setBounds(60, 255, 250, 25);
        add(totalRevenueLabel);

        reportTextArea = new JTextArea();
        reportTextArea.setEditable(false);
        reportScrollPane = new JScrollPane(reportTextArea);
        reportScrollPane.setBounds(60, 300, 560, 150);
        add(reportScrollPane);

        backButton = new JButton("Back");
        backButton.setBounds(280, 470, 120, 35);
        add(backButton);

        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed();
            }
        });

        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed();
            }
        });
    }

    private void generateButtonActionPerformed() {
        String reportType = reportTypeComboBox.getSelectedItem().toString();
        String inputValue = inputValueTextField.getText().trim();

        if (inputValue.equals("")) {
            JOptionPane.showMessageDialog(this, "Input value cannot be empty.");
            return;
        }

        if (!isInputFormatValid(reportType, inputValue)) {
            JOptionPane.showMessageDialog(this, "Invalid format for " + reportType + " report.");
            return;
        }

        ArrayList<String> paymentLines = readPaymentLines();

        if (paymentLines.size() == 0) {
            totalRevenueLabel.setText("Total Revenue: RM 0.00");
            reportTextArea.setText("No payment records available");
            JOptionPane.showMessageDialog(this, "No payment records available");
            return;
        }

        generateReport(reportType, inputValue, paymentLines);
    }

    private ArrayList<String> readPaymentLines() {
        ArrayList<String> lines = new ArrayList<String>();

        try {
            FileReader fileReader = new FileReader(PAYMENT_FILE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            while (line != null) {
                if (!line.trim().equals("")) {
                    lines.add(line);
                }

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot read payments.txt.");
        }

        return lines;
    }

    private void generateReport(String reportType, String inputValue, ArrayList<String> paymentLines) {
        double totalRevenue = 0.0;
        String output = "";

        output = output + "Matching Payment Records\n";
        output = output + "------------------------------------------------------------\n";
        output = output + "Payment ID\tAppointment ID\tPatient ID\tDate\tAmount\tMethod\n";

        for (int i = 0; i < paymentLines.size(); i++) {
            String line = paymentLines.get(i);
            String[] parts = line.split(",", -1);

            if (parts.length >= 6) {
                String paymentDate = parts[3];

                if (isPaymentMatching(reportType, inputValue, paymentDate)) {
                    try {
                        double amount = Double.parseDouble(parts[4]);
                        totalRevenue = totalRevenue + amount;

                        output = output + parts[0] + "\t"
                                + parts[1] + "\t"
                                + parts[2] + "\t"
                                + parts[3] + "\t"
                                + parts[4] + "\t"
                                + parts[5] + "\n";
                    } catch (Exception e) {
                        // Skip records with invalid amount values.
                    }
                }
            }
        }

        totalRevenueLabel.setText("Total Revenue: RM " + formatMoney(totalRevenue));

        if (totalRevenue == 0.0) {
            output = output + "No matching payment records found.\n";
        }

        reportTextArea.setText(output);
    }

    private boolean isPaymentMatching(String reportType, String inputValue, String paymentDate) {
        if (reportType.equals("Daily")) {
            if (paymentDate.equals(inputValue)) {
                return true;
            }
        } else if (reportType.equals("Monthly")) {
            if (paymentDate.length() >= 7 && paymentDate.substring(0, 7).equals(inputValue)) {
                return true;
            }
        } else if (reportType.equals("Quarterly")) {
            if (isQuarterMatching(paymentDate, inputValue)) {
                return true;
            }
        } else if (reportType.equals("Yearly")) {
            if (paymentDate.length() >= 4 && paymentDate.substring(0, 4).equals(inputValue)) {
                return true;
            }
        }

        return false;
    }

    private boolean isQuarterMatching(String paymentDate, String inputValue) {
        if (paymentDate.length() < 7 || inputValue.length() != 7) {
            return false;
        }

        String paymentYear = paymentDate.substring(0, 4);
        String inputYear = inputValue.substring(0, 4);

        if (!paymentYear.equals(inputYear)) {
            return false;
        }

        int month = 0;

        try {
            month = Integer.parseInt(paymentDate.substring(5, 7));
        } catch (Exception e) {
            return false;
        }

        char quarter = inputValue.charAt(6);

        if (quarter == '1' && month >= 1 && month <= 3) {
            return true;
        } else if (quarter == '2' && month >= 4 && month <= 6) {
            return true;
        } else if (quarter == '3' && month >= 7 && month <= 9) {
            return true;
        } else if (quarter == '4' && month >= 10 && month <= 12) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isInputFormatValid(String reportType, String inputValue) {
        if (reportType.equals("Daily")) {
            return isDailyFormat(inputValue);
        } else if (reportType.equals("Monthly")) {
            return isMonthlyFormat(inputValue);
        } else if (reportType.equals("Quarterly")) {
            return isQuarterlyFormat(inputValue);
        } else if (reportType.equals("Yearly")) {
            return isYearlyFormat(inputValue);
        } else {
            return false;
        }
    }

    private boolean isDailyFormat(String value) {
        if (value.length() != 10) {
            return false;
        } else if (value.charAt(4) != '-' || value.charAt(7) != '-') {
            return false;
        } else if (!areDigits(value, 0, 4)) {
            return false;
        } else if (!areDigits(value, 5, 7)) {
            return false;
        } else if (!areDigits(value, 8, 10)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isMonthlyFormat(String value) {
        if (value.length() != 7) {
            return false;
        } else if (value.charAt(4) != '-') {
            return false;
        } else if (!areDigits(value, 0, 4)) {
            return false;
        } else if (!areDigits(value, 5, 7)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isQuarterlyFormat(String value) {
        if (value.length() != 7) {
            return false;
        } else if (value.charAt(4) != '-') {
            return false;
        } else if (!areDigits(value, 0, 4)) {
            return false;
        } else if (value.charAt(5) != 'Q') {
            return false;
        } else if (value.charAt(6) < '1' || value.charAt(6) > '4') {
            return false;
        } else {
            return true;
        }
    }

    private boolean isYearlyFormat(String value) {
        if (value.length() != 4) {
            return false;
        } else if (!areDigits(value, 0, 4)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean areDigits(String value, int start, int end) {
        for (int i = start; i < end; i++) {
            char letter = value.charAt(i);

            if (letter < '0' || letter > '9') {
                return false;
            }
        }

        return true;
    }

    private String formatMoney(double amount) {
        int cents = (int) (amount * 100 + 0.5);
        int ringgit = cents / 100;
        int sen = cents % 100;
        String senText = "" + sen;

        if (sen < 10) {
            senText = "0" + sen;
        }

        return ringgit + "." + senText;
    }

    private void backButtonActionPerformed() {
        AdminDashboard adminDashboard = new AdminDashboard();
        adminDashboard.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        RevenueReportPage revenueReportPage = new RevenueReportPage();
        revenueReportPage.setVisible(true);
    }
}
