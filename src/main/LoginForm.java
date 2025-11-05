package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {
    private JTextField username;
    private JPasswordField password;
    private JComboBox<String> roleComboBox; // Add a JComboBox
    private JButton loginButton;
    private JButton registerButton;

    public LoginForm() {
        setTitle("Login Form");

        JLabel usernameLabel = new JLabel("Username:");
        username = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        password = new JPasswordField(20);

        JLabel roleLabel = new JLabel("Role:"); // Add a label for the JComboBox
        String[] roles = { "Worker", "Manager", "Customer" };
        roleComboBox = new JComboBox<>(roles); // Initialize the JComboBox

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        JPanel panel = new JPanel();
        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(roleLabel); // Add the role label to the panel
        panel.add(roleComboBox); // Add the role combo box to the panel
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);

        registerButton.addActionListener(e -> {
            dispose();
            new RegisterForm();
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(880, 566));
        setResizable(false);
        setSize(new Dimension(350, 250));

        username.setFont(new Font("Tahoma", 0, 20)); // NOI18N
        username.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        usernameLabel.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setText("Username:");

        password.setFont(new Font("Tahoma", 0, 20)); // NOI18N
        password.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        passwordLabel.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setText("Password:");

        roleLabel.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roleLabel.setText("Role:");

        registerButton.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        registerButton.setText("Register");

        loginButton.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        loginButton.setText("Log In");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                                                .addComponent(usernameLabel)
                                                .addGap(66, 66, 66)
                                                .addComponent(username, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(roleLabel)
                                                .addGap(97, 97, 97)
                                                .addComponent(roleComboBox, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(username, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(roleComboBox, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(roleLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(55, Short.MAX_VALUE))
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

        setVisible(true);
        pack();
    }

    private void jTextField2ActionPerformed(ActionEvent evt) {}

    private void jTextField3ActionPerformed(ActionEvent evt) {}

    private void jButton2ActionPerformed(ActionEvent evt) {
        String selectedRole = roleComboBox.getSelectedItem().toString();
        AccountType type = null;
        switch (selectedRole.charAt(0)){
            case 'M':
                type = AccountType.Manger;
                break;
            case 'W':
                type = AccountType.Worker;
                break;
            case 'C':
                type = AccountType.Customer;
                break;
        }
        Account.Login(username.getText(), password.getText(), type);
        dispose();
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
