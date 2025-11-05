package main;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import java.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuForm extends JFrame {
    private ArrayList<Meal> meals;
    private JTable table;
    private JRadioButton takeawayButton;
    private JRadioButton deliveryButton;
    private JRadioButton dineinButton;
    private ButtonGroup orderTypeGroup;
    private JComboBox<String> tipComboBox;

    public MenuForm(ArrayList<Meal> meals) {
        this. meals = meals;

        // Table model for Meal objects
        MealTableModel tableModel = new MealTableModel(meals);
        table = new JTable(tableModel);

        // Set font for table
        Font font = new Font("Tahoma", Font.PLAIN, 24);
        table.setFont(font);
        table.setRowHeight(30); // Adjust row height to accommodate larger font

        // Set font for table header
        JTableHeader header = table.getTableHeader();
        header.setFont(font);

        // Custom cell editor and renderer for the input fields
        table.getColumnModel().getColumn(3).setCellEditor(new InputCellEditor());
        table.getColumnModel().getColumn(3).setCellRenderer(new InputCellRenderer());

        // Add toolbar with shopping cart button and Add To Order button

        JToolBar toolBar = createToolBar();
        toolBar.setPreferredSize(new Dimension(800, 30)); // Increase toolbar size
        add(toolBar, BorderLayout.NORTH);

        // Add radio buttons for order type
        JPanel orderTypePanel = new JPanel();
        orderTypePanel.setLayout(new FlowLayout());

        takeawayButton = new JRadioButton("Takeaway");
        deliveryButton = new JRadioButton("Delivery");
        dineinButton = new JRadioButton("Dine-in");

        takeawayButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        deliveryButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        dineinButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

        orderTypeGroup = new ButtonGroup();
        orderTypeGroup.add(takeawayButton);
        orderTypeGroup.add(deliveryButton);
        orderTypeGroup.add(dineinButton);

        orderTypePanel.add(takeawayButton);
        orderTypePanel.add(deliveryButton);
        orderTypePanel.add(dineinButton);

        // Add tip combo box
        JLabel tipLabel = new JLabel("Tip: ");
        tipLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        String[] tipOptions = {"5%", "10%", "20%", "30%", "50%", "75%", "100%"};
        tipComboBox = new JComboBox<>(tipOptions);
        tipComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));

        orderTypePanel.add(tipLabel);
        orderTypePanel.add(tipComboBox);

        add(orderTypePanel, BorderLayout.SOUTH);

        // Set up the frame
        setTitle("Menu Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();

        // Add To Order button
        JButton homeButton = new JButton("Home");
        homeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        homeButton.addActionListener(e -> {
            // Navigate back to the main menu or another specified form
            new CustomerForm(Data.loginCustomer.Name, Data.loginCustomer.PersonalOrder).setVisible(true); // Change to your main menu form
            dispose(); // Close the current form
        });

        JButton addToOrderButton = new JButton("Add To Order");
        addToOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addToOrderButton.setPreferredSize(new Dimension(150, 30)); // Set preferred size
        addToOrderButton.addActionListener(e -> {
            // Ensure the table stops editing before creating the order map
            if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }
            Map<String, Integer> orderMap = createOrderMap();

            // Check selected order type
            OrderType orderType = null;
            if (takeawayButton.isSelected()) {
                orderType = OrderType.TakeAway;
            } else if (deliveryButton.isSelected()) {
                orderType = OrderType.Delivery;
            } else if (dineinButton.isSelected()) {
                orderType = OrderType.DineIn;
            }

            // Get selected tip percentage
            String selectedTip = (String) tipComboBox.getSelectedItem();
            float tipPercentage = Integer.parseInt(selectedTip.replace("%", "")) / 100f;

            // Open the ShoppingCartForm with the order map, order type, and tip amount
            new ShoppingCartForm(orderMap, meals, orderType, tipPercentage).setVisible(true);
            dispose();
        });
        toolBar.add(homeButton);
        toolBar.add(addToOrderButton);

        return toolBar;
    }

    private Map<String, Integer> createOrderMap() {
        Map<String, Integer> orderMap = new HashMap<>();
        MealTableModel model = (MealTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String mealName = model.getValueAt(i, 0).toString();
            Integer quantity = (Integer) model.getValueAt(i, 3);
            if (quantity != null && quantity > 0) {
                orderMap.put(mealName, quantity);
            }
        }
        return orderMap;
    }

    // Rest of the code remains the same...

    private class MealTableModel extends AbstractTableModel {
        private String[] columnNames = {"Name", "Description", "Price", "Quantity"};
        private ArrayList<Meal> meals;
        private Integer[] quantities;

        public MealTableModel(ArrayList<Meal> meals) {
            this.meals = meals;
            this.quantities = new Integer[meals.size()];
            // Initialize quantities to 0
            for (int i = 0; i < quantities.length; i++) {
                quantities[i] = 0;
            }
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
                case 0: return meal.getName();
                case 1: return meal.getDesc();
                case 2: return meal.getPrice();
                case 3: return quantities[rowIndex];
                default: return null;
            }
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            if (columnIndex == 3) {
                quantities[rowIndex] = (Integer) value;
                fireTableCellUpdated(rowIndex, columnIndex);
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 3; // Make quantity column editable
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 3) {
                return Integer.class;
            }
            return String.class;
        }
    }

    private class InputCellRenderer extends JTextField implements TableCellRenderer {
        public InputCellRenderer() {
            super();
            setFont(new Font("Tahoma", Font.PLAIN, 24));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            if (value != null) {
                setText(value.toString());
            } else {
                setText("0"); // Set placeholder to 0
            }
            return this;
        }
    }

    private class InputCellEditor extends AbstractCellEditor implements TableCellEditor {
        private JFormattedTextField textField;

        public InputCellEditor() {
            NumberFormat format = NumberFormat.getIntegerInstance();
            NumberFormatter formatter = new NumberFormatter(format);
            formatter.setValueClass(Integer.class);
            formatter.setMinimum(0); // Minimum value
            formatter.setAllowsInvalid(false); // Disallow invalid input
            formatter.setCommitsOnValidEdit(true);

            textField = new JFormattedTextField(formatter);
            textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
        }

        @Override
        public Object getCellEditorValue() {
            return textField.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value != null) {
                textField.setValue(value);
            } else {
                textField.setValue(0); // Set default value to 0
            }
            return textField;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Sample data
            ArrayList<Meal> meals = new ArrayList<>();
//            meals.add(new Meal(12.99f, "Burger", "A delicious cheeseburger", "path/to/burger.jpg"));
//            meals.add(new Meal(8.99f, "Salad", "A healthy green salad", "path/to/salad.jpg"));

            // Show the form
            Data.ReadData();
            new MenuForm(Data.Menu).setVisible(true);
        });
    }
}
