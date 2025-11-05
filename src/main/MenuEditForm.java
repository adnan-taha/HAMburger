package main;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MenuEditForm extends JFrame {
    private ArrayList<Meal> meals;
    private JTable table;
    private MealTableModel tableModel;

    public MenuEditForm(ArrayList<Meal> meals) {
        this.meals = meals;
        tableModel = new MealTableModel(meals);
        table = new JTable(tableModel);

        // Set up the frame
        setTitle("Edit Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Add table to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Center align and set font for table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        Font tableFont = new Font("Tahoma", Font.PLAIN, 24);
        table.setFont(tableFont);
        table.setRowHeight(30);

        // Set font for table header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 24));

        // Add toolbar with buttons
        JToolBar toolBar = new JToolBar();
        JButton HomeButton = new JButton("Home");
        JButton addButton = new JButton("Add Meal");
        JButton editButton = new JButton("Edit Meal");
        JButton deleteButton = new JButton("Delete Meal");

        HomeButton.addActionListener(e -> {Home();});
        addButton.addActionListener(e -> addMeal());
        editButton.addActionListener(e -> editMeal());
        deleteButton.addActionListener(e -> deleteMeal());

        toolBar.add(HomeButton);
        toolBar.add(addButton);
        toolBar.add(editButton);
        toolBar.add(deleteButton);
        add(toolBar, BorderLayout.NORTH);
    }

private void Home() {
        if (Data.loginWorker != null){
            new WorkerForm(Data.loginWorker.Name).setVisible(true);
        }else {
            new ManagerForm(Data.loginManger.Name).setVisible(true);
        }
    dispose();
}
    private void addMeal() {
        Meal meal = getMealFromUser(null);
        if (meal != null) {
            Data.Menu.add(meal);
            dispose();
            new MenuEditForm(Data.Menu).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "you didn't enter a meal");
        }
    }

    private void editMeal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Meal meal = Data.Menu.get(selectedRow);
            Meal updatedMeal = getMealFromUser(meal);
            if (updatedMeal != null) {
                Data.Menu.set(selectedRow, updatedMeal);
                dispose();
                new MenuEditForm(Data.Menu).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a meal to edit.");
        }
    }

    private void deleteMeal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Data.Menu.remove(selectedRow);
            dispose();
            new MenuEditForm(Data.Menu).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a meal to delete.");
        }
    }

    private Meal getMealFromUser(Meal meal) {
        JTextField nameField = new JTextField();
        JTextField descField = new JTextField();

        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0); // Minimum value
        formatter.setAllowsInvalid(false); // Disallow invalid input
        formatter.setCommitsOnValidEdit(true);

        JFormattedTextField priceField = new JFormattedTextField(formatter);

        if (meal != null) {
            nameField.setText(meal.getName());
            descField.setText(meal.getDesc());
            priceField.setValue((int) meal.getPrice());
        }

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Description:"));
        panel.add(descField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Enter Meal Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String desc = descField.getText();
            int price = ((Number) priceField.getValue()).intValue();
            return new Meal(price, name, desc); // Adjust to include image path if needed
        }

        return null;
    }

    private class MealTableModel extends AbstractTableModel {
        private String[] columnNames = {"Name", "Description", "Price"};
        private ArrayList<Meal> meals;

        public MealTableModel(ArrayList<Meal> meals) {
            this.meals = meals;
        }

        @Override
        public int getRowCount() {
            return meals.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Meal meal = meals.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return meal.getName();
                case 1:
                    return meal.getDesc();
                case 2:
                    return meal.getPrice();
                default:
                    return null;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false; // Disable direct editing in table
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 2) {
                return Integer.class; // Ensure price is treated as an integer
            }
            return String.class;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Sample data
//            ArrayList<Meal> meals = new ArrayList<>();
//            meals.add(new Meal(12, "Burger", "A delicious cheeseburger", "path/to/burger.jpg"));
//            meals.add(new Meal(9, "Salad", "A healthy green salad", "path/to/salad.jpg"));

            // Show the form
            Data.ReadData();
            new MenuEditForm(Data.Menu).setVisible(true);
        });
    }
}
