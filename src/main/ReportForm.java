package main;

import javax.swing.*;
import java.awt.*;

public class ReportForm extends JFrame {
    private JLabel numberOfDailyOrdersLabel;
    private JLabel mostOrderedMealLabel;
    private JLabel dailyRevenueLabel;
    private JLabel customerNameLabel;

    public ReportForm(Amanger.reports report) {
        // Set up the frame
        setTitle("Report Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Title
        JLabel titleLabel = new JLabel("Daily Report");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Number of Daily Orders
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;
        JLabel numberOfDailyOrdersText = new JLabel("Number of Daily Orders:");
        numberOfDailyOrdersText.setFont(new Font("Tahoma", Font.PLAIN, 22));
        add(numberOfDailyOrdersText, gbc);

        numberOfDailyOrdersLabel = new JLabel(String.valueOf(report.getNumberOfDailyOrders()));
        numberOfDailyOrdersLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        gbc.gridx = 1;
        add(numberOfDailyOrdersLabel, gbc);

        // Most Ordered Meal
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel mostOrderedMealText = new JLabel("Most Ordered Meal:");
        mostOrderedMealText.setFont(new Font("Tahoma", Font.PLAIN, 22));
        add(mostOrderedMealText, gbc);

        mostOrderedMealLabel = new JLabel(report.getMostOrderedMeal());
        mostOrderedMealLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        gbc.gridx = 1;
        add(mostOrderedMealLabel, gbc);

        // Daily Revenue
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel dailyRevenueText = new JLabel("Daily Revenue:");
        dailyRevenueText.setFont(new Font("Tahoma", Font.PLAIN, 22));
        add(dailyRevenueText, gbc);

        dailyRevenueLabel = new JLabel(String.valueOf(report.getDailyRevenue()));
        dailyRevenueLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        gbc.gridx = 1;
        add(dailyRevenueLabel, gbc);

        // Customer Name
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel customerNameText = new JLabel("Most Loyal Customer:");
        customerNameText.setFont(new Font("Tahoma", Font.PLAIN, 22));
        add(customerNameText, gbc);

        customerNameLabel = new JLabel(report.getMostLoyalCustomer().Name);
        customerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        gbc.gridx = 1;
        add(customerNameLabel, gbc);
    }

    public static void main(String[] args) {
        //Acustomer customer = new Acustomer("John Doe");
        //Report report = new Report(123, "Pizza", 4500, customer);

        SwingUtilities.invokeLater(() -> {
            new ReportForm(Amanger.report).setVisible(true);
        });
    }
}
