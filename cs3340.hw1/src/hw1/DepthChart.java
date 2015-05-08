/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW Assignment #2
 * 01/17/2015
 * 
 * DepthChart.java
 * Implementation code related to depth chart for a basketball team
 */

package hw1;

import java.util.ArrayList;
import java.util.ListIterator;

public class DepthChart {

   /* Instance variables
    */
    public static final int PG = 0,		// point guard
                            SG = 1,		// shooting guard
                            SF = 2,		// small forward
                            PF = 3,		// power forward
                            C  = 4,		// center
                            NUM_POSITIONS = 5;  // for basketball
    
    private final ArrayList<Position> chart = new ArrayList<>();
    private final Position playersPG = new Position("PG");
    private final Position playersSG = new Position("SG");
    private final Position playersSF = new Position("SF");
    private final Position playersPF = new Position("PF");
    private final Position playersC = new Position("C");

   /* Constructor
    * Adds basketball positions to chart ArrayList
    */
    public DepthChart() {
        chart.add(playersPG);
        chart.add(playersSG);
        chart.add(playersSF);
        chart.add(playersPF);
        chart.add(playersC);
    }
    
    
   /* Add a player to the depth chart at a specific position
    * @param position		= add to this position
    * @param player		= whom to add
    */ 
    public void add(String position, Player player) {
        int index = 0;
        int next = 0;
        
        // get the index of this position so we know where to insert
        if (position.equals("PG")) {
            index = PG;
        }
        else if (position.equals("SG")) {
            index = SG;
        }
        else if (position.equals("SF")) {
            index = SF;
        }
        else if (position.equals("PF")) {
            index = PF;
        }
        else if (position.equals("C")) {
            index = C;
        }
        else
            System.out.println("Invalid Position");
        
        next = index + 1;
        
        // insert player at position index, exit after first iteration
        for (ListIterator itr = chart.listIterator(index); index < next; index++) {
            Position p = (Position) itr.next();
            p.add(player);
        }
    }
    
   /* Output the depth chart by sending to the output stream
    * @return positionDisplay = string description of position and it's players
    */
    @Override
    public final String toString() {
        String positionDisplay = "";
        
        // iterate through our chart to display the position and it's players
        // we only need to display the positionName once, not for each player
        // Position.toString() is a return delimited list of players at this position
        for (ListIterator itr = chart.listIterator(); itr.hasNext();) {
            Position pos = (Position) itr.next();
            positionDisplay = positionDisplay + pos.positionName + ": " + pos.toString() + "\n";
        }
        
        return positionDisplay;
    }
}
