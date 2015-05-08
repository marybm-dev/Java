
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 4311 - Week 4 Assignment
 * Winter 2015
 * 01/28/2015
 *
 * Menu.java
 */

package cs4311.abstractfactory;

public class Menu {
    
    public void select(){
        
    }
}

class SunMenu extends Menu {
    @Override
    public void select() {
        System.out.println("Sun Menu Select");
    }
}

class MSMenu extends Menu {
    @Override
    public void select() {
        System.out.println("MS Menu Select");
    }
}

