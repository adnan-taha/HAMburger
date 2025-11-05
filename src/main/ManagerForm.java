package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerForm extends JFrame {
    public ManagerForm(String headerText) {
        // Set up the frame
        setTitle("Manager Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Add header label
        JLabel headerLabel = new JLabel(headerText, JLabel.CENTER);
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        // Add Manager or Worker button
        JButton addManagerOrWorkerButton = new JButton("Add Manager or Worker");
        addManagerOrWorkerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addManagerOrWorkerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to add manager or worker
                new ManagerRegisterForm();
                dispose();
            }
        });
        buttonPanel.add(addManagerOrWorkerButton);

        // Reports button
        JButton reportsButton = new JButton("Reports");
        reportsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to view reports
                new ReportsForm().setVisible(true);
            }
        });
        buttonPanel.add(reportsButton);

        // Edit Menu button
        JButton editMenuButton = new JButton("Edit Menu");
        editMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        editMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to edit menu
                dispose();
                new MenuEditForm(Data.Menu).setVisible(true);
            }
        });
        buttonPanel.add(editMenuButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManagerForm("Management").setVisible(true);
        });
    }
}
