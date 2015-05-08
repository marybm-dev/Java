/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW #7
 * 03/09/2015
 * 
 * NewPlayerController.java
 * Handles creation of new Player - called from GUI class
 */

package Controller;

import Model.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class NewPlayerController {
    
   /* Instance variables
    */
    private DepthChart model;
    private String position;
    private String first;
    private String last;
    int num;

   /* MODIFIED Constructor
    * Now adding the player to the array used in file storage
    *
    * @param DepthChart
    * @param ComboBox
    * @param TextField * 3 : (firstname, lastname, number)
    *
    * Adds a new player to their position in the DepthChart model class
    */ 
    public NewPlayerController(DepthChart _chart, ComboBox _position, TextField _first, TextField _last, TextField _num) {
        model = _chart;
        position = (String) _position.getSelectionModel().getSelectedItem().toString();
        first = _first.getText();
        last = _last.getText();
        num = Integer.parseInt(_num.getText());
        
        Player player = new Player(first, last, num);
        player.setPosition(position);
        
        model.playersArray.add(player);
        model.add(position, player);
    }
}
