package main;

import java.io.Serializable;
import java.util.*;

public class Aworker extends Account implements Serializable {
    protected static ArrayList<Order> AllDayOrders = new ArrayList<>();

    public Aworker(String name, String passWord, String userName, AccountType type) {
        super(name, passWord, userName, type);
    }
}
