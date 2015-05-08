
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 4311 - Week 4 Assignment
 * Winter 2015
 * 01/28/2015
 *
 * GUI.java
 */
 
package cs4311.abstractfactory;

/* GUI.class
 * 
 * Intereface representation class
 * @param args - command line input for Factory #
 */

public class GUI {
    
    public static void main(String[] args) {
        // get the user input to select which Factory to use
        int code = 1;//Integer.parseInt(args[0]);
        
        // get the factory
        AbstractFactory f = AbstractFactory.getFactory(code);
        
        // create the menu for this factory
        Menu m = f.createMenu();
        
        // create the button for this factory
        Button b = f.createButton();
        
        // select the menu
        m.select();
        
        // push the button
        b.push();
    }
}
