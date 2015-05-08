/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW #5
 * 02/16/2015
 * 
 * DeletePlayerController.java
 * Handles removing a player from a position - called by GUI.java
 */

package Controller;

import Model.DepthChart;
import Model.Player;
import Model.Position;

public class DeletePlayerController {
   /* Instance varibales
    */  
    private String positionName;
    private DepthChart chart;
    private Position position;
    private Player player;
    private String playerName;
    
   /* MODIFIED Constructor
    * Now removing player from the array used in data storage
    *
    * @param DepthChart
    * @param Position
    * @param Player
    *
    * Removes player from their position in the DepthChart model class
    */ 
    public DeletePlayerController(DepthChart _chart, Position _position, Player _player) {
        chart = _chart;
        position = _position;
        player = _player;
        chart.remove(position, player);
        chart.playersArray.remove(player);
    }
}
