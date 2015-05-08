/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW Assignment #2
 * 01/17/2015
 * 
 * Menu.java
 * Implementation code related to menu of choices for a program
 */

package hw1;

import java.util.Scanner;

public class Menu {

   /* Instance varialbe
    */
    protected String[] choices;

    /* Default Constructor
    * @param choices = array of descriptions of choices
    */ 
    public Menu (String[] _choices) {
        choices = new String[_choices.length];
        System.arraycopy(_choices, 0, choices, 0, _choices.length);
    }

   /* Copy constructor
    * @param source = menu to copy
    */
    public Menu (final Menu source) {
        choices = new String[source.choices.length];
        System.arraycopy(source.choices, 0, choices, 0, source.choices.length);
    }

   /* Gets next choice made by user, does not handle error checking
    * @return integer code for next command
    */ 
    public int get() {	
        int choice = -1;
        System.out.println("Please choose one of the following: ");

        for (int i = 0; i < choices.length ; i++)
            System.out.println(i + ". " + choices[i]);

        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        return choice;
    }
}



