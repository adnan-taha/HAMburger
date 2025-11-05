package main;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PreviousOrdersForm extends JFrame {
    private JTable table;
    private List<Map<String, Integer>> orders;
    private List<Float> tips;
    private List<Float> totalPrices;

    public PreviousOrdersForm(List<Map<String, Integer>> orders, List<Float> tips, List<Float> totalPrices, List<status> statuses) {
        this.orders = orders;
        this.tips = tips;
        this.totalPrices = totalPrices;

        // Table model for displaying order items
        PreviousOrdersTableModel tableModel = new PreviousOrdersTableModel(orders, tips, totalPrices, statuses);
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

        // Add toolbar with Home button
        JToolBar toolBar = new JToolBar();
        JButton homeButton = new JButton("Home");
        homeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        homeButton.addActionListener(e -> {
            // Navigate back to the main menu or another specified form
            new CustomerForm(Data.loginCustomer.Name, Data.loginCustomer.PersonalOrder).setVisible(true); // Change to your main menu form
            dispose(); // Close the current form
        });
        toolBar.add(homeButton);

        // Set up the frame
        setTitle("Previous Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH); // Add toolbar at the top
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private class PreviousOrdersTableModel extends AbstractTableModel {
        private String[] columnNames = {"Order", "Name", "Quantity", "Status"};
        private List<Map<String, Integer>> orders;
        private List<Float> tips;
        private List<Float> totalPrices;
        private List<status> statuses;

        public PreviousOrdersTableModel(List<Map<String, Integer>> orders, List<Float> tips, List<Float> totalPrices, List<status> statuses) {
            this.orders = orders;
            this.tips = tips;
            this.totalPrices = totalPrices;
            this.statuses = statuses;
        }

        @Override
        public int getRowCount() {
            int rowCount = 0;
            for (Map<String, Integer> order : orders) {
                rowCount += order.size() + 1; // +1 for the total price and tip row
            }
            return rowCount;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            int orderIndex = 0;
            int rowOffset = 0;
            for (Map<String, Integer> order : orders) {
                int orderSize = order.size();
                if (rowIndex < rowOffset + orderSize) {
                    String mealName = (String) order.keySet().toArray()[rowIndex - rowOffset];
                    int quantity = order.get(mealName);
                    switch (columnIndex) {
                        case 0: return orderIndex + 1;
                        case 1: return mealName;
                        case 2: return quantity;
                        case 3: return "";
                        default: return null;
                    }
                } else if (rowIndex == rowOffset + orderSize) {
                    switch (columnIndex) {
                        case 0: return "Total & Tip:";
                        case 1: return totalPrices.get(orderIndex);
                        case 2: return tips.get(orderIndex);
                        case 3: return statuses.get(orderIndex);
                        default: return null;
                    }
                }
                rowOffset += orderSize + 1;
                orderIndex++;
            }
            return null;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0: return String.class; // Updated from Integer to String to avoid formatting issues
                case 1: return String.class;
                case 2: return Integer.class;
                case 3: return Float.class;
                case 4: return Float.class;
                default: return String.class;
            }
        }
    }

    public static void main(String[] args) {
        // Sample data
        Map<String, Integer> order1 = Map.of(
                "Burger", 2,
                "Salad", 1
        );
        Map<String, Integer> order2 = Map.of(
                "Pizza", 3,
                "Pasta", 2
        );
        List<Map<String, Integer>> orders = List.of(order1, order2);
        List<Float> tips = List.of(5.0f, 3.0f);
        List<Float> totalPrices = List.of(30.0f, 25.0f);
        List<status> statuses = List.of(status.Done , status.Pending);

        // Show the form
        new PreviousOrdersForm(orders, tips, totalPrices, statuses).setVisible(true);
    }
}
