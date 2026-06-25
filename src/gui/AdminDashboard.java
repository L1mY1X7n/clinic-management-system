/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminDashboard extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton manageUsersButton;
    private JButton manageRosterButton;
    private JButton revenueReportButton;
    private JButton logoutButton;

    public AdminDashboard() {
        setupFrame();
        setupComponents();
    }

    private void setupFrame() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setupComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 400, 300);
        add(mainPanel);

        titleLabel = new JLabel("Admin Dashboard");
        titleLabel.setBounds(140, 25, 150, 25);
        mainPanel.add(titleLabel);

        manageUsersButton = new JButton("Manage Users");
        manageUsersButton.setBounds(110, 70, 170, 35);
        mainPanel.add(manageUsersButton);

        manageRosterButton = new JButton("Manage Roster");
        manageRosterButton.setBounds(110, 115, 170, 35);
        mainPanel.add(manageRosterButton);

        revenueReportButton = new JButton("Revenue Reports");
        revenueReportButton.setBounds(110, 160, 170, 35);
        mainPanel.add(revenueReportButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(110, 205, 170, 35);
        mainPanel.add(logoutButton);

        manageUsersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUsersButtonActionPerformed();
            }
        });

        manageRosterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageRosterButtonActionPerformed();
            }
        });

        revenueReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revenueReportButtonActionPerformed();
            }
        });

        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed();
            }
        });
    }

    private void manageUsersButtonActionPerformed() {
        ManageUserPage manageUserPage = new ManageUserPage();
        manageUserPage.setVisible(true);
        dispose();
    }

    private void manageRosterButtonActionPerformed() {
        ManageRosterPage manageRosterPage = new ManageRosterPage();
        manageRosterPage.setVisible(true);
        dispose();
    }

    private void revenueReportButtonActionPerformed() {
        RevenueReportPage revenueReportPage = new RevenueReportPage();
        revenueReportPage.setVisible(true);
        dispose();
    }

    private void logoutButtonActionPerformed() {
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        AdminDashboard adminDashboard = new AdminDashboard();
        adminDashboard.setVisible(true);
    }
}
