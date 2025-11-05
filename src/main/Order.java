package main;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Order implements Serializable {
    String CustomerName;
    protected OrderType Type;
    protected ArrayList <Meal> Meals = new ArrayList<>();
    protected Map<String, Integer> MealsMap;
    protected Date DateOfOrder;
    protected float TotalPrice;
    protected status Status;
    protected float Tip;

    public Order(String customerName, OrderType type, ArrayList<Meal> meals, Map<String, Integer> mealsMap, Date dateOfOrder, float totalPrice, float tip) {
        CustomerName = customerName;
        Type = type;
        Meals = meals;
        MealsMap = mealsMap;
        DateOfOrder = dateOfOrder;
        TotalPrice = totalPrice;
        Tip = tip;
    }

    @Override
    public String toString() {
        return CustomerName + ","+Type + ","+ DateOfOrder + "," + TotalPrice + "," + Status + ","  + Tip;
    }


//    public void MakeMEal(float price, String name, String img, String desc, String notes, int quantity){
//        for (Meal m : Meals){
//            if (m.Name == name){
//                m.Quantity++;
//            }else {
//                Meals.add(new Meal(price, name, img, desc, notes, quantity));
//            }
//        }
//
//
//    }
}
