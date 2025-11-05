package main;

import java.io.*;
import java.util.*;

public class Acustomer extends Account implements Serializable{
    protected ArrayList <Order> PersonalOrder = new ArrayList<>();
    // wallet;

    public Acustomer(String name, String passWord, String userName, AccountType type) {
        super(name, passWord, userName, type);
    }

    public Acustomer() {
        super();
    }

    protected void MakeOrder(OrderType type, Map<String, Integer> orderMap, float tip){
        float totalprice = 0;
        ArrayList <Meal> orderArray = new ArrayList<>();
        for (Meal m : Data.Menu){
            for (String key : orderMap.keySet()){
                if (m.Name == key){
                    totalprice += m.getPrice()*orderMap.get(key);
                    orderArray.add(m);
                }
            }
        }
        Date today = new Date();
        Order o1 = new Order(Data.loginCustomer.Name,type,orderArray,orderMap,new Date(),totalprice,tip);
        o1.Status = status.Pending;
        PersonalOrder.add(o1);
        Data.AllTimeOrder.add(o1);
        System.out.println(totalprice);

    }
}
