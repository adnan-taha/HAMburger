package main;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkerForm extends JFrame {
    public WorkerForm(String headerName) {
        // Set up the frame
        setTitle("Worker Menu");
//        setBackground(Color.CYAN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Header label
        JLabel headerLabel = new JLabel(headerName, SwingConstants.CENTER);
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
        add(headerLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        // Edit Menu button
        JButton editMenuButton = new JButton("Edit Menu");
        editMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        editMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to open Edit Menu form
                dispose();
                new MenuEditForm(Data.Menu).setVisible(true);
            }
        });
        buttonPanel.add(editMenuButton);

        // Manage Orders button
        JButton manageOrdersButton = new JButton("Manage Orders");
        manageOrdersButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        manageOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to open Manage Orders form
                dispose();
                new OrdersDisplayForm(Aworker.AllDayOrders).setVisible(true);
            }
        });
        buttonPanel.add(manageOrdersButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Show the worker form
        new WorkerForm("Worker Dashboard").setVisible(true);
    }
}
