
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 3340 - Week 4 Assignment
 * Winter 2015
 * 02/01/2015
 *
 * Card.java
 * Candy Land Game Card implementation
 */

public class Card {
    private String color;
    private int squaresQty;
    
    public Card(String _color, int _squaresQty) {
        color = _color;
        squaresQty = _squaresQty;
    }
    
    public String getColor() {
        return color;
    }
    
    public int getSquaresQty() {
        return squaresQty;
    }
}
