/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplequiz;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author mlmartinez85
 */
public class Order {
    
    private ArrayList<LineItem> items;
    
    public Order() {
        items = new ArrayList<>();
    }
    
    public void addItem(LineItem itm) {
        items.add(itm);
    }
    
    public double orderCost(){
        double total = 0;
        double cost = 0;
        for (ListIterator itr = items.listIterator(); itr.hasNext(); ) {
            LineItem current = (LineItem) itr.next();
            cost = current.getPricePerUnit() * current.getQuantity();
            System.out.println(current.getProductName() + " $" + cost);
            total = total + cost;
        }
        return total;
    }
    
}
