
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 4311 - Week 3 Assignment
 * Winter 2015
 * 01/23/2015
 *
 * AbstractDynamicProgram.java
 */

package cs4311.abstractdynamicprogram;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDynamicProgram {
    
    public static void main(String[] args) {
        try {
            run(args[0]);                                                                           // 1
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Must provide an argument to the program.");
        }
        
    }
    
    public static void run(String programName) {
        try {
            Class programClass = Class.forName(programName);                                        // 1.1
            AbstractDynamicProgram program = (AbstractDynamicProgram)programClass.newInstance();    // 1.2
            program.start();                                                                        // 1.3
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Argument to program must be a valid class name.");
            
        } catch (InstantiationException ex) {
            Logger.getLogger(AbstractDynamicProgram.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbstractDynamicProgram.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public abstract void start();
}

class ConcreteProgram1 extends AbstractDynamicProgram {

    @Override
    public void start() {
        System.out.println("This is the start of ConcreteProgram1");
    }
    
}

class ConcreteProgram2 extends AbstractDynamicProgram {

    @Override
    public void start() {
        System.out.println("This is the start of ConcreteProgram2");
    }
    
}
