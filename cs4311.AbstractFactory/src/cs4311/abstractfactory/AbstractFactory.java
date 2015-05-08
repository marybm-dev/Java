
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 4311 - Week 4 Assignment
 * Winter 2015
 * 01/28/2015
 *
 * AbstractFactory.java
 */

package cs4311.abstractfactory;

public abstract class AbstractFactory {

    public static SunFactory sun = new SunFactory();
    public static MSFactory ms = new MSFactory();
    
    public static AbstractFactory getFactory(int code) {
        switch(code) {
        case 1 : 
            return sun;
        default :
            return ms;
        }
        
    }
    
    public abstract Menu createMenu();
    public abstract Button createButton();
}

class SunFactory extends AbstractFactory {
    @Override
    public Menu createMenu() {
        return new SunMenu();
    }
    
    @Override
    public Button createButton() {
        return new SunButton();
    }
}

class MSFactory extends AbstractFactory {
    @Override
    public Menu createMenu() {
        return new MSMenu();
    }
    
    @Override
    public Button createButton() {
        return new MSButton();
    }
}