package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//edit

public class ManagerRegisterForm extends JFrame {
    private JTextField name;
    private JTextField username;
    private JPasswordField password;
    private JPasswordField confirmPass;
    private JButton registerButton;
    private JButton backButton;
    private JComboBox<String> roleComboBox; // Add a JComboBox

    public ManagerRegisterForm() {
        setTitle("Register Form");

        JLabel nameLabel = new JLabel("Name:");
        name = new JTextField(20);

        JLabel usernameLabel = new JLabel("Username:");
        username = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        password = new JPasswordField(20);

        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        confirmPass = new JPasswordField(20);

        JLabel roleLabel = new JLabel("Role:"); // Add a label for the JComboBox
        String[] roles = { "Worker", "Manager"};
        roleComboBox = new JComboBox<>(roles); // Initialize the JComboBox

        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        JPanel panel = new JPanel();
        panel.add(nameLabel);
        panel.add(name);
        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(confirmPassLabel);
        panel.add(confirmPass);
        panel.add(registerButton);
        panel.add(backButton);
        panel.add(roleLabel); // Add the role label to the panel
        panel.add(roleComboBox); // Add the role combo box to the panel

        add(panel);

        registerButton.addActionListener(e -> {
            String selectedRole = roleComboBox.getSelectedItem().toString();
            AccountType type = null;
            switch (selectedRole.charAt(0)){
                case 'M':
                    type = AccountType.Manger;
                    break;
                case 'W':
                    type = AccountType.Worker;
                    break;
            }
            if (name.getText().isEmpty() || username.getText().isEmpty() || password.getPassword().length == 0 || confirmPass.getPassword().length == 0) {
                JOptionPane.showMessageDialog(null, "All fields are required.");
                System.out.print(password.getPassword());
            } else if (containsComma(password) || containsComma(confirmPass) || containsComma(name) || containsComma(username) ) {
                JOptionPane.showMessageDialog(null, "Error: No commas allowed in any field.");
            }
            else {
                String password2 = new String(password.getPassword());
                String confirmPass2 = new String(confirmPass.getPassword());

                if (password2.equals(confirmPass2)) {
                    if (Data.CreateAccount(name.getText(),password.getText(),username.getText(),type)){
                        JOptionPane.showMessageDialog(null, "Registration Successful!");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Username already taken");
                    }
                    dispose();
                    new ManagerRegisterForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match. Try again.");
                }
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new ManagerForm(Data.loginManger.Name).setVisible(true);
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register");
        setResizable(false);
        setSize(new Dimension(350, 250));

        name.setFont(new Font("Tahoma", 0, 20)); // NOI18N
        name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        nameLabel.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setText("Name:");

        username.setFont(new Font("Tahoma", 0, 20)); // NOI18N
        username.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        usernameLabel.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setText("Username:");

        password.setFont(new Font("Tahoma", 0, 20)); // NOI18N
        password.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });

        passwordLabel.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setText("Password:");

        confirmPass.setFont(new Font("Tahoma", 0, 20)); // NOI18N
        confirmPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                confirmPassActionPerformed(evt);
            }
        });

        confirmPassLabel.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        confirmPassLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmPassLabel.setText("Confirm Pword:");

        registerButton.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        registerButton.setText("Register");

        backButton.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        backButton.setText("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(passwordLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(password, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(usernameLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(username, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                                                .addComponent(nameLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(name, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(confirmPassLabel)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(confirmPass, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(roleLabel)
                                                                .addGap(97, 97, 97)
                                                                .addComponent(roleComboBox, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(name, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(username, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(confirmPassLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(confirmPass, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(roleComboBox, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(roleLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(56, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
        setVisible(true);
    }

    private void nameActionPerformed(ActionEvent evt) {}

    private void usernameActionPerformed(ActionEvent evt) {}

    private void passwordActionPerformed(ActionEvent evt) {}

    private void confirmPassActionPerformed(ActionEvent evt) {}

    private void backButtonActionPerformed(ActionEvent evt) {}

    private boolean containsComma(JTextField textField) {
        return textField.getText().contains(",");
    }
}
