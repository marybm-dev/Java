/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplequiz;

/**
 *
 * @author mlmartinez85
 */
public class LineItem {
    
    private String productName;
    private int quantity;
    private double pricePerUnit;
    
    public LineItem(String pname, int qty, double ppu) {
        productName = pname;
        quantity = qty;
        pricePerUnit = ppu;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getPricePerUnit() {
        return pricePerUnit;
    }
}
