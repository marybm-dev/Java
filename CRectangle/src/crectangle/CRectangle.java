/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crectangle;

/**
 *
 * @author mlmartinez85
 */
public class CRectangle {

    int width, height;
    
    CRectangle() {
        width = 5;
        height = 5;
    }
    
    CRectangle(int a, int b) {
        width = a;
        height = b;
    }
    
    public int area() {
        return (width * height);
    }
    
    public static void main(String[] args) {
        
        CRectangle rect = new CRectangle(3, 4);
        CRectangle rectb = new CRectangle();
        CRectangle[] rectArray = { rect, rectb };
        
        for (int i = 0; i < 2 ; i++) {
            System.out.println("rect " + i + " area: " + rectArray[i].area());
        }
    }
    
}
