/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW Assignment #2
 * 01/17/2015
 * 
 * Position.java
 * Implementation code to bridge between DepthChart and Player
 */
package hw1;

import java.util.ArrayList;
import java.util.ListIterator;

public class Position {
    
   /* Instance variables
    */ 
    public String positionName;
    private final ArrayList<Player> players = new ArrayList<>();
    
   /* Default Constructor
    * @param name   = string description of position name for displaying
    */ 
    public Position(String name) {
        positionName = name;
    }
    
   /* Add a player to this position instance
    * @param player = whom to add
    */ 
    public void add(Player player) {
        players.add(player);
    }
   
   /* Output a player by sending the player's name to the output stream
    * @return positionPlayers = string description of players in this position
    */ 
    @Override
    public final String toString() {
        
        // use StringBuilder here to determine when new line break needed
        StringBuilder positionPlayers = new StringBuilder();
        
        for (ListIterator itr = players.listIterator(); itr.hasNext();) {
            Player p = (Player) itr.next();
            // don't add line break if positionPlayers empty
            if (positionPlayers.length() != 0) {
                positionPlayers.append("\n");
            }
            positionPlayers.append(p.toString());
        }
        
        return positionPlayers.toString();
    }
}
