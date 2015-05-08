/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW #5
 * 02/16/2015
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

   /* Constructor
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
        
        model.add(position, new Player(first, last, num));
    }
}
