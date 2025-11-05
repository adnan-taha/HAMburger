package main;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Data {
    public static Date Today = new Date();
    public static Acustomer loginCustomer;
    public static Amanger loginManger;
    public static Aworker loginWorker;
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public static ArrayList<Aworker> WorkerAccounts = new ArrayList<>();
    public static ArrayList<Amanger> MangerAccounts = new ArrayList<>();
    public static ArrayList<Acustomer> CustomerAccounts = new ArrayList<>();
    public static ArrayList <Order> AllTimeOrder = new ArrayList<>();
    public static ArrayList<Meal> Menu = new ArrayList<>();


    public static boolean CreateAccount(String name, String password, String username, AccountType type) {
        if (type == AccountType.Customer) {
            for (Acustomer customerAccount : CustomerAccounts) {
                if (Objects.equals(customerAccount.UserName, username)) {
                    return false;
                }
            }
            CustomerAccounts.add(new Acustomer(name, password, username, type));
            return true;
        } else if (type == AccountType.Manger) {
            for (Amanger manger : MangerAccounts) {
                if (Objects.equals(manger.UserName, username)) {
                    return false;
                }
            }
            MangerAccounts.add(new Amanger(name, password, username, type));
            return true;
        } else {
            for (Aworker worker : WorkerAccounts) {
                if (Objects.equals(worker.UserName, username)) {
                    return false;
                }
            }
            WorkerAccounts.add(new Aworker(name, password, username, type));
            return true;
        }
    }

    public static void ReadData() {
        File MangersData = new File("Mangers.txt");
        try (ObjectInputStream DataIn = new ObjectInputStream(new FileInputStream(MangersData))) {
            Object obj;
            while ((obj = DataIn.readObject()) != null) {

                MangerAccounts.add((Amanger) obj);
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        File WorkerData = new File("Workers.txt");
        try (ObjectInputStream DataIn = new ObjectInputStream(new FileInputStream(WorkerData))) {
            Object obj;
            while ((obj = DataIn.readObject()) != null) {
                WorkerAccounts.add((Aworker) obj);
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        File CustomerData = new File("Customer.txt");
        try (ObjectInputStream DataIn = new ObjectInputStream(new FileInputStream(CustomerData))) {
            Object obj;
            while ((obj = DataIn.readObject()) != null) {

                CustomerAccounts.add((Acustomer) obj);
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        File MenuData = new File("Menu.txt");
        try (ObjectInputStream DataIn = new ObjectInputStream(new FileInputStream(MenuData))) {
            Object obj;
            while ((obj = DataIn.readObject()) != null) {
                Menu.add((Meal) obj);
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Acustomer acustomer : CustomerAccounts){
            AllTimeOrder.addAll(acustomer.PersonalOrder);
        }
        for (Order order : AllTimeOrder){
            if (Time.isSameDay(order.DateOfOrder,Today)){
                Aworker.AllDayOrders.add(order);
            }
        }
    }

    public static void WriteData() {
        File MangerData = new File("Mangers.txt");
        try {
            ObjectOutputStream DataOut = new ObjectOutputStream(new FileOutputStream(MangerData));
            for (Amanger M : MangerAccounts) {
                DataOut.writeObject(M);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File WorkersData = new File("Workers.txt");
        try {
            ObjectOutputStream DataOut = new ObjectOutputStream(new FileOutputStream(WorkersData));
            for (Aworker W : WorkerAccounts) {
                DataOut.writeObject(W);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File CustomerData = new File("Customer.txt");
        try {
            ObjectOutputStream DataOut = new ObjectOutputStream(new FileOutputStream(CustomerData));
            for (Acustomer C : CustomerAccounts) {
                DataOut.writeObject(C);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File MenuData = new File("Menu.txt");
        try {
            ObjectOutputStream DataOut = new ObjectOutputStream(new FileOutputStream(MenuData));
            for (Meal meal : Menu) {
                DataOut.writeObject(meal);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
