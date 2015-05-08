
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 4311 - Week 4 Assignment
 * Winter 2015
 * 01/28/2015
 *
 * Button.java
 */

package cs4311.abstractfactory;

public class Button {
    public void push() {
        
    }
}

class SunButton extends Button {
    @Override
    public void push() {
        System.out.println("Sun Button Push");
    }
}

class MSButton extends Button {
    @Override
    public void push() {
        System.out.println("MS Button Push");
    }
}