package main;

import java.util.*;

public class Amanger extends Account {
    public static reports report = new reports();

    public Amanger(String name, String passWord, String userName, AccountType type) {
        super(name, passWord, userName, type);
    }

    protected static void MakeReport(Date day) {
        int NumberOfDailyOrders = 0;
        int MostOrderedMeal = 0;
        float DailyRevenue = 0;
        Map<String, Integer> HowManyTimeOrderedMeal = new HashMap<>();
        for (Meal meal : Data.Menu) {
            HowManyTimeOrderedMeal.put(meal.Name, 0);
        }
        for (Order order : Data.AllTimeOrder) {
            if (Time.isSameDay(order.DateOfOrder, day)){
                for (String key : order.MealsMap.keySet()) {
                    HowManyTimeOrderedMeal.put(key, order.MealsMap.get(key));
                }
            }
        }
        for (Order order : Data.AllTimeOrder) {
            if (Time.isSameDay(order.DateOfOrder, day)) {
                NumberOfDailyOrders++;
                DailyRevenue += order.TotalPrice;
                for (String key : HowManyTimeOrderedMeal.keySet()) {
                    MostOrderedMeal = Math.max(MostOrderedMeal, HowManyTimeOrderedMeal.get(key));
                }
                for (String key : HowManyTimeOrderedMeal.keySet()) {
                    if (HowManyTimeOrderedMeal.get(key) == MostOrderedMeal) {
                        report.MostOrderedMeal = key;
                        break;
                    }
                }
            }
            report.MostLoyalCustomer = Data.CustomerAccounts.get(0);
            for (Acustomer customer : Data.CustomerAccounts) {
                if(report.MostLoyalCustomer.PersonalOrder.size()<customer.PersonalOrder.size()){
                    report.MostLoyalCustomer = customer;
                }
            }
        }
        report.NumberOfDailyOrders = NumberOfDailyOrders;
        report.DailyRevenue = DailyRevenue;
    }


    public static class reports {
        protected int NumberOfDailyOrders;
        protected String MostOrderedMeal;
        protected float DailyRevenue;
        protected Acustomer MostLoyalCustomer;

        public int getNumberOfDailyOrders() {
            return NumberOfDailyOrders;
        }

        public String getMostOrderedMeal() {
            return MostOrderedMeal;
        }

        public float getDailyRevenue() {
            return DailyRevenue;
        }

        public Acustomer getMostLoyalCustomer() {
            return MostLoyalCustomer;
        }

        @Override
        public String toString() {
            return "reports{" +
                    "NumberOfDailyOrders=" + NumberOfDailyOrders +
                    ", MostOrderedMeal='" + MostOrderedMeal + '\'' +
                    ", DailyRevenue=" + DailyRevenue +
                    ", MostLoyalCustomer=" + MostLoyalCustomer +
                    '}';
        }
    }
}
