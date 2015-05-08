
/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW #5
 * 02/16/2015
 * 
 * Main.java
 * Interface implementation of DepthChart
 * Users can add and remove player's to/from a specified position
 */
package View;

import java.util.Scanner;
import Model.*;
import View.*;
import Controller.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

   /* main()
    */ 
    public static void main(String[] args) {
        launch(args);
    }

   /* start() method
    * Creates and calls GUI object to use the stage for the interface
    */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Depth Chart");
        GUI gui = new GUI(stage);
        stage.show();
    }
}
