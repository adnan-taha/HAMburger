package main;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class ShoppingCartForm extends JFrame {
    private JTable table;
    private Map<String, Integer> orderMap;
    private ArrayList<Meal> meals;

    public ShoppingCartForm(Map<String, Integer> orderMap, ArrayList<Meal> meals, OrderType type, float tip) {
        this.orderMap = orderMap;
        this.meals = meals;

        // Table model for displaying order items
        OrderTableModel tableModel = new OrderTableModel(orderMap, meals);
        table = new JTable(tableModel);

        // Set font for table
        Font font = new Font("Tahoma", Font.PLAIN, 24);
        table.setFont(font);
        table.setRowHeight(30); // Adjust row height to accommodate larger font

        // Set font for table header
        JTableHeader header = table.getTableHeader();
        header.setFont(font);

        // Add a label to display the total price
        JLabel totalLabel = new JLabel("Total Price: $" + tableModel.calculateTotalPrice(tip));
        totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));

        // Add Confirm Order and Cancel Order buttons
        JPanel buttonPanel = new JPanel();
        JButton confirmButton = new JButton("Confirm Order");
        confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        confirmButton.addActionListener(e -> {
            if (orderMap.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ya batta ordark is fady");

            } else {
                Data.loginCustomer.MakeOrder(type, orderMap, tip);
                JOptionPane.showMessageDialog(this, "Order Confirmed!");
            }
            new CustomerForm(Data.loginCustomer.Name, Data.loginCustomer.PersonalOrder).setVisible(true);
            dispose();
        });
        JButton cancelButton = new JButton("Cancel Order");
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cancelButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Order Canceled!");
            new CustomerForm(Data.loginCustomer.Name, Data.loginCustomer.PersonalOrder).setVisible(true);
            dispose();
        });

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Set up the frame
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(totalLabel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.NORTH);
    }

    private class OrderTableModel extends AbstractTableModel {
        private String[] columnNames = {"Name", "Price", "Quantity", "Final Price"};
        private Map<String, Integer> orderMap;
        private ArrayList<Meal> meals;

        public OrderTableModel(Map<String, Integer> orderMap, ArrayList<Meal> meals) {
            this.orderMap = orderMap;
            this.meals = meals;
        }

        @Override
        public int getRowCount() {
            return orderMap.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            String mealName = (String) orderMap.keySet().toArray()[rowIndex];
            Meal meal = findMealByName(mealName);
            int quantity = orderMap.get(mealName);
            switch (columnIndex) {
                case 0:
                    return mealName;
                case 1:
                    return meal.getPrice();
                case 2:
                    return quantity;
                case 3:
                    return meal.getPrice() * quantity;
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
            if (columnIndex == 1 || columnIndex == 3) {
                return Float.class;
            } else if (columnIndex == 2) {
                return Integer.class;
            } else {
                return String.class;
            }
        }

        private Meal findMealByName(String name) {
            for (Meal meal : meals) {
                if (meal.getName().equals(name)) {
                    return meal;
                }
            }
            return null;
        }

        public float calculateTotalPrice(float tip) {
            float total = 0;
            for (int i = 0; i < getRowCount(); i++) {
                total += (float) getValueAt(i, 3);
            }
            return total + total * tip;
        }
    }

    public static void main(String[] args) {
        // Sample data
        ArrayList<Meal> meals = new ArrayList<>();
//        meals.add(new Meal(12.99f, "Burger", "A delicious cheeseburger", "path/to/burger.jpg"));
//        meals.add(new Meal(8.99f, "Salad", "A healthy green salad", "path/to/salad.jpg"));

        Map<String, Integer> orderMap = Map.of(
                "Burger", 2,
                "Salad", 1
        );

        // Show the form
        new ShoppingCartForm(orderMap, meals, OrderType.Delivery, 10).setVisible(true);
    }
}
