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
public class SampleQuiz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[] values = new int[3];
        values[0] = 12;
        values[1] = 0;
        values[2] = -5;
        if (hasZeroes(values))
            System.out.println("has at least one zero\n");
        
        Person monique = new Person("Monique", "mkb@gmail.com");
        Person mary = new Person("Mary", "mlm@gmail.com");
        Mail newMessage = new Mail(monique, "Hi, please feed me later!", mary);
        newMessage.displayMail();    
        
        List tmp = new List();
        tmp.insert(2);
        tmp.insert(9);
        for (int loc = 0; loc < tmp.getLength(); loc++) {
            if (2 == tmp.get(loc))
                System.out.println("\n2 was found at index " + loc + "\n");
        }
        
        LineItem coffee = new LineItem("Coffee", 2, 7.50);
        LineItem eggs = new LineItem("Eggs", 2, 3);
        LineItem milk = new LineItem("Milk", 1, 4);
        LineItem bread = new LineItem("Bread", 2, 3.75);
        Order myGroceries = new Order();
        myGroceries.addItem(coffee);
        myGroceries.addItem(eggs);
        myGroceries.addItem(milk);
        myGroceries.addItem(bread);
        System.out.println("Total Cost: $" + myGroceries.orderCost());
    }

    static boolean hasZeroes(int[] values) {
        for (int i = 0; i < values.length; i++) 
            if(values[i] == 0)
                return true;
        
        return false;
    }
   
}
