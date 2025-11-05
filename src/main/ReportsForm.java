package main;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportsForm extends JFrame {
    private JTextField dateField;

    public ReportsForm() {
        // Set up the frame
        setTitle("Reports Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 300); // Increase the size
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Title
        JLabel titleLabel = new JLabel("Generate Report");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 28)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Date input field
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;
        JLabel dateLabel = new JLabel("Enter Date (yyyy-MM-dd):");
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 22)); // Increase font size
        add(dateLabel, gbc);

        dateField = new JTextField(10);
        dateField.setFont(new Font("Tahoma", Font.PLAIN, 22)); // Increase font size
        gbc.gridx = 1;
        add(dateField, gbc);

        // Button for showing report
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton showReportButton = new JButton("Show Report");
        showReportButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); // Increase font size
        showReportButton.addActionListener(e -> {
            Date date = handleShowReport();
            Amanger.MakeReport(date);
            new ReportForm(Amanger.report).setVisible(true);
        });
        add(showReportButton, gbc);
    }

    private Date handleShowReport() {
        String dateText = dateField.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Enable strict date validation

        try {
            return dateFormat.parse(dateText);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please enter a date in the format yyyy-MM-dd.");
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ReportsForm().setVisible(true);
        });
    }
}
