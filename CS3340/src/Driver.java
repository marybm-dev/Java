
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Driver {

    public static void main(String[] args) {
        LineItem item1, item2, item3, item4, item5, item6, item7;
        Order order1 = new Order(), order2 = new Order(),
                order3 = new Order(), order4 = new Order();
        
        item1 = new LineItem("Shredded cheddar cheese, 8 oz", 2, 249);
        item2 = new LineItem("Macaroni, 16 oz", 1, 139);
        order1.addItem(item1);
        order1.addItem(item2);
        int cost = order1.total();
        System.out.println("Total cost = $" + cost / 100 + "." + cost % 100);
        
        item3 = new LineItem("Banana", 3, 19);
        item4 = new LineItem("Tangerines, 3 lb", 1, 599);
        item5 = new LineItem("Large eggs, 1 dozen", 1, 199);
        item6 = new LineItem("Bagel", 2, 99);
        item7 = new LineItem("Mints", 1, 57);
        order2.addItem(item3);
        order3.addItem(item4);
        order3.addItem(item5);
        order3.addItem(item6);
        order4.addItem(item7);
        
        List<Order> myOrders = new ArrayList<>();
        myOrders.add(order1);
        myOrders.add(order2);
        myOrders.add(order3);
        myOrders.add(order4);
        
        Collections.sort(myOrders);
        
        compareOrders(order1, order2);
        compareOrders(order1, order3);
        compareOrders(order2, order4);
    }
    
    private static void compareOrders(Order order1, Order order2) {
        int cmp = order1.compareTo(order2);
        if (cmp < 0) {
            System.out.println("Order" + order1.getOrderNumber() + " costs less than " + "Order" + order2.getOrderNumber());
        }
        else if (cmp > 0) {
            System.out.println("Order" + order1.getOrderNumber() + " costs more than " + "Order" + order2.getOrderNumber());
        }
        else {
            System.out.println("Order" + order1.getOrderNumber() + " costs the same as " + "Order" + order2.getOrderNumber());            
        }
    }
}