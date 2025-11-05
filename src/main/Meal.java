package main;

import java.io.Serializable;

public class Meal implements Serializable {
    protected float Price;
    protected String Name;
    protected String Desc;
    protected int Quantity;

    @Override
    public String toString() {
        return Price + "," + Name +","  + Desc + ","  + Quantity+"," ;
    }

    public float getPrice() {
        return Price;
    }

    public String getName() {
        return Name;
    }

    public String getDesc() {
        return Desc;
    }

    public Meal(float price, String name, String desc, int quantity) {
        Price = price;
        Name = name;
        Desc = desc;
        Quantity = quantity;
    }
    public Meal(float price, String name, String desc) {
        Price = price;
        Name = name;
        Desc = desc;
    }
}
