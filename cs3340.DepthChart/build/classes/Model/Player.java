/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW #5
 * 02/16/2015
 * 
 * Player.java
 * Implementation code representation of an atheletic team member
 */

package Model;

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
    
    /* Get the Player's #
     */
    public int getNumber() {
        return number;
    }
    
    /* Get the Player's first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /* Get the Player's last name
     */
    public String getLastName() {
        return lastName;
    }
}