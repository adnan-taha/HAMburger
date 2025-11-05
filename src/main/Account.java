package main;

import javax.swing.*;
import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    protected String Name;
    protected String PassWord;
    protected String UserName;
    protected AccountType Type;

    public Account() {
    }

    public Account(String name, String passWord, String userName, AccountType type) {
        Name = name;
        PassWord = passWord;
        UserName = userName;
        Type = type;
    }

    @Override
    public String toString() {
        return "account{" +
                "Name='" + Name + '\'' +
                ", PassWord='" + PassWord + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Type=" + Type +
                '}';
    }
    public static void Login(String userName, String passWord, AccountType type){
        switch (type){
            case Manger : {
                boolean found = false;
                for (Amanger manger : Data.MangerAccounts) {
                    if (Objects.equals(userName, manger.UserName) && Objects.equals(passWord, manger.PassWord)) {
                        Data.loginManger = manger; // Pointer to the logged-in customer
                        // Show MangerForm
                        new ManagerForm(Data.loginManger.Name).setVisible(true);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // Message for wrong password or username
                    JOptionPane.showMessageDialog(null, "Username or password is incorrect.");
                    new LoginForm();
                }
                break;
            }
            case Worker:{
                boolean found = false;
                for (Aworker worker : Data.WorkerAccounts) {
                    if (Objects.equals(userName, worker.UserName) && Objects.equals(passWord, worker.PassWord)) {
                        Data.loginWorker = worker; // Pointer to the logged-in customer
                        // Show WorkerForm
                        new WorkerForm(Data.loginWorker.Name).setVisible(true);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // Message for wrong password or username
                    JOptionPane.showMessageDialog(null, "Username or password is incorrect.");
                    new LoginForm();
                }
                break;
            }
            case Customer: {
                boolean found = false;
                for (Acustomer customer : Data.CustomerAccounts) {
                    if (Objects.equals(userName, customer.UserName) && Objects.equals(passWord, customer.PassWord)) {
                        Data.loginCustomer = customer; // Pointer to the logged-in customer
                        //new OrdersDisplayForm(Data.loginCustomer.PersonalOrder).setVisible(true);
                        new CustomerForm(Data.loginCustomer.Name, Data.loginCustomer.PersonalOrder).setVisible(true); // Show CustomerForm
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // Message for wrong password or username
                    JOptionPane.showMessageDialog(null, "Username or password is incorrect.");
                    new LoginForm();
                }
                break;
            }

        }
    }
}
