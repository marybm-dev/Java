/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW Assignment #2
 * 01/17/2015
 * 
 * Player.java
 * Implementation code representation of an atheletic team member
 */

package hw1;

public class Player extends Object {
    
   /* Instance variables
    */
    protected String firstName, lastName;   // name of player
    int number;                             // uniform number of player

   /* Default constructor
    */ 
    public Player(String _firstName, String _lastName, int _number) {
        firstName = _firstName;
        lastName = _lastName;
        number = _number;
    }
   
   /* Output a player by sending the player's name to the output stream
    * @return string description of player
    */
    @Override
    public final String toString() {
        return firstName + " " + lastName + ", #" + number;
    }
}