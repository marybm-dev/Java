
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 3340 - Week 4 Assignment
 * Winter 2015
 * 02/01/2015
 *
 * Position.java
 * Candy Land Game Position implementation
 */

public class Position {
    private String color;
    private Boolean hasBridge;
    private Boolean endOfGame;
    private Position otherSideOfBridge;
    private Integer index;
    
    public Position(String _color, Boolean _hasBridge, Boolean _endOfGame, Position _otherSide, Integer _index) {
        color = _color;
        hasBridge = _hasBridge;
        endOfGame = _endOfGame;
        otherSideOfBridge = _otherSide;
        index = _index;
    }
    
    public String getColor() {
        return color;
    }
    
    public Boolean hasBridge() {
        return hasBridge;
    }
    
    public Boolean isEndOfGame() {
        return endOfGame;
    }
    
    public Position getOtherSideOfBridge() {
        return otherSideOfBridge;
    }
    
    public Integer getIndex() {
        return index;
    }
}
