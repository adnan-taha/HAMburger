package main;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import javax.swing.table.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class OrdersDisplayForm extends JFrame {
    private JTable table;
    private List<Order> orders;
    private JFormattedTextField readyOrderField;

    public OrdersDisplayForm(List<Order> orders) {
        this.orders = orders;

        // Table model for displaying orders
        OrdersTableModel tableModel = new OrdersTableModel(orders);
        table = new JTable(tableModel);

        // Set font for table
        Font font = new Font("Tahoma", Font.PLAIN, 24);
        table.setFont(font);
        table.setRowHeight(30); // Adjust row height to accommodate larger font

        // Set font for table header
        JTableHeader header = table.getTableHeader();
        header.setFont(font);

        // Center the content of all cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add toolbar with Home button, Ready Order button, Cancel Order button, and two text fields
        JToolBar toolBar = new JToolBar();

        JButton homeButton = new JButton("Home");
        homeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        homeButton.addActionListener(e -> {
            // Navigate back to the main menu
            new WorkerForm(Data.loginWorker.Name).setVisible(true);
            dispose();
        });
        toolBar.add(homeButton);

        JButton readyOrderButton = new JButton("Ready Order");
        readyOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        readyOrderButton.addActionListener(e -> {
            int readyOrderIndex = Integer.parseInt(readyOrderField.getText());
            if (readyOrderIndex > Aworker.AllDayOrders.size()) {
                JOptionPane.showMessageDialog(this, "you hemar giving me an number that does not exisit you homar?? ");
            } else {

                if (Aworker.AllDayOrders.get(readyOrderIndex - 1).Status == status.Pending) {
                    Aworker.AllDayOrders.get(readyOrderIndex - 1).Status = status.Done;
                } else {
                    JOptionPane.showMessageDialog(this, "you can't accept a canceled order");
                }
                dispose();
                new OrdersDisplayForm(Aworker.AllDayOrders).setVisible(true);

            }
        });
        toolBar.add(readyOrderButton);


        JButton cancelOrderButton = new JButton("Cancel Order");
        cancelOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cancelOrderButton.addActionListener(e -> {
            int cancelOrderIndex = Integer.parseInt(readyOrderField.getText());
            if (cancelOrderIndex > Aworker.AllDayOrders.size()) {
                JOptionPane.showMessageDialog(this, "you hemar giving me an number that does not exisit you homar?? ");
            } else {
                if (Aworker.AllDayOrders.get(cancelOrderIndex - 1).Status == status.Pending) {
                    Aworker.AllDayOrders.get(cancelOrderIndex - 1).Status = status.Cancelled;
                } else {
                    JOptionPane.showMessageDialog(this, "you can't cancel a done order");
                }
            }
            dispose();
            new OrdersDisplayForm(Aworker.AllDayOrders).setVisible(true);
        });
        toolBar.add(cancelOrderButton);

        // Text field for Ready Order count (integer input only)
        readyOrderField = createIntegerField();
        toolBar.add(readyOrderField);

        // Set up the frame
        setTitle("Orders Display");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600); // Adjust size to accommodate more columns
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH); // Add toolbar at the top
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private JFormattedTextField createIntegerField() {
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0); // Minimum value
        formatter.setAllowsInvalid(false); // Disallow invalid input
        formatter.setCommitsOnValidEdit(true);

        JFormattedTextField field = new JFormattedTextField(formatter);
        field.setColumns(5);
        field.setFont(new Font("Tahoma", Font.PLAIN, 18));
        return field;
    }

    private class OrdersTableModel extends AbstractTableModel {
        private String[] columnNames = {"Order Index", "Customer Name", "Order Status"};
        private List<Order> orders;

        public OrdersTableModel(List<Order> orders) {
            this.orders = orders;
        }

        @Override
        public int getRowCount() {
            return orders.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Order order = orders.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return rowIndex + 1;
                case 1:
                    return order.CustomerName;
                case 2:
                    return order.Status;
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return Integer.class;
                default:
                    return String.class;
            }
        }
    }

    public static void main(String[] args) {
        // Sample data
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order("Alice", "Pending"));
//        orders.add(new Order("Bob", "Completed"));

        // Show the form
        new OrdersDisplayForm(Data.loginCustomer.PersonalOrder).setVisible(true);
    }
}
