package main;

import java.io.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Acustomer a1 = new Acustomer("adnan", "1234", "ad", AccountType.Customer);
//        Aworker a2 = new Aworker("shaar", "12345", "sh", AccountType.Worker);
//        Amanger a3 = new Amanger("hashem", "12346", "ha", AccountType.Manger);
//        Data.CustomerAccounts.add(a1);
//        Data.WorkerAccounts.add(a2);
//        Data.MangerAccounts.add(a3);
//        Meal m1 = new Meal(10,"pizza","delicious pizza steve");
//        Meal m2 = new Meal(15,"burger","burger chikcion");
//        Meal m3 = new Meal(20,"pasta alfredo","delicious pasta with parmigiano orgiano cheeesz with grilld chikiano");
//        Data.Menu.add(m1);
//        Data.Menu.add(m2);
//        Data.Menu.add(m3);
//        Data.WriteData();
        Data.ReadData();
        new LoginForm();
        for (Amanger M : Data.MangerAccounts) {
            System.out.print(M.toString());
        }
        for (Aworker M : Data.WorkerAccounts) {
            System.out.print(M.toString());
        }
        for (Acustomer M : Data.CustomerAccounts) {
            System.out.print(M.toString());
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Data.WriteData();
            }});
    }
}