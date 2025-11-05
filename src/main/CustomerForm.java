package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerForm extends JFrame {
    public CustomerForm(String headerName, ArrayList <Order> Orders) {
        // Set up the frame
        setTitle("Customer Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Header label
        JLabel headerLabel = new JLabel(headerName, SwingConstants.CENTER);
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        // Make an Order button
        JButton makeOrderButton = new JButton("Make an Order");
        makeOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        makeOrderButton.addActionListener(e -> {
            new MenuForm(Data.Menu).setVisible(true);
            dispose(); // Close the customer menu
        });
        buttonPanel.add(makeOrderButton);

        // Previous Orders button
        JButton previousOrdersButton = new JButton("Previous Orders");
        previousOrdersButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        previousOrdersButton.addActionListener(e -> {
            // Open a new form to display previous orders (to be implemented)
            List <Float> tips = new ArrayList<>();
            List <Float> totalPrices = new ArrayList<>();
            List<Map<String, Integer>> ordersMap = new ArrayList<>();
            List<status> statuses = new ArrayList<>();
            for (Order o : Orders){
                tips.add(o.Tip);
                totalPrices.add(o.TotalPrice);
                ordersMap.add(o.MealsMap);
                statuses.add(o.Status);
            }
            new PreviousOrdersForm(ordersMap, tips, totalPrices, statuses).setVisible(true);
            dispose();
        });
        buttonPanel.add(previousOrdersButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

//    private ArrayList<Meal> getMeals() {
//        // Sample data
//        ArrayList<Meal> meals = new ArrayList<>();
//        meals.add(new Meal(12.99f, "Burger", "A delicious cheeseburger", "path/to/burger.jpg"));
//        meals.add(new Meal(8.99f, "Salad", "A healthy green salad", "path/to/salad.jpg"));
//        return meals;
//    }

    public static void main(String[] args) {
        // Show the customer menu form
        Data.ReadData();
        //new CustomerForm("Welcome to Our Restaurant").setVisible(true);
    }
}

