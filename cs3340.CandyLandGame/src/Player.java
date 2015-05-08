
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 3340 - Week 4 Assignment
 * Winter 2015
 * 02/01/2015
 *
 * Player.java
 * Candy Land Game Player implementation
 */

public class Player {
    private String name;
    private Boolean currentTurn = false;
    private int currentPosition = 0;
    
    public Player(String _name) {
        name = _name;
    }
    
    public String getName() {
        return name;
    }
    
    public Boolean isCurrentTurn() {
        return currentTurn;
    }
    
    public int getCurrentPosition() {
        return currentPosition;
    }
    
    public void setCurrentPosition(int indexOfCard) {
        currentPosition = indexOfCard;
    }
    
    public void toggleTurn() {
        currentTurn = !currentTurn;
    }
}
