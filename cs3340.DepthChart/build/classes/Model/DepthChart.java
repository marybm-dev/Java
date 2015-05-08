/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW #5
 * 02/16/2015
 * 
 * DepthChart.java
 * Implementation code related to depth chart for a basketball team
 */

package Model;

import java.util.ArrayList;
import java.util.List;
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
    
    private final List<Position> chart = new ArrayList<>();
    public Position playersPG = new Position("PG");
    public Position playersSG = new Position("SG");
    public Position playersSF = new Position("SF");
    public Position playersPF = new Position("PF");
    public Position playersC = new Position("C");

   /* Constructor
    * Adds basketball positions to chart ArrayList
    */
    public DepthChart() {
        chart.add(playersPG);
        chart.add(playersSG);
        chart.add(playersSF);
        chart.add(playersPF);
        chart.add(playersC);
        
        add("PG", new Player("mary", "martinez", 12));
        add("PG", new Player("puka", "baptista", 1));
        add("SG", new Player("monique", "kahili", 44));
        add("SG", new Player("katrina", "belda", 67));
        add("SF", new Player("bob", "smith", 23));
        add("SF", new Player("kirk", "jones", 89));
        add("PF", new Player("joe", "stephenson", 30));
        add("PF", new Player("david", "davis", 15));
        add("C", new Player("becky", "doe", 65));
        add("C", new Player("ana", "clark", 17));
    }
    
   /* Add a player to the depth chart at a specific position
    * @param position		= add to this position
    * @param player		= whom to add
    */ 
    public void add(String position, Player player) {
        Position p = getPosition(position);
        p.add(player);
    }
    
   /* Remove a player from the depth chart at a specific position
    * @param position		= remove from this position
    * @param player		= whom to remove
    */
    public void remove(Position position, Player player) {
        position.remove(player);
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
    
    /* Get all of the Players in this Position
     */
    public List<String> getPositionPlayers(String position) {
        List<String> players = new ArrayList();
        Position pos = getPosition(position);
        
        // get all of the Players at this position
        for (ListIterator posItr = pos.players.listIterator(); posItr.hasNext(); ) {
            Player player = (Player) posItr.next();
            players.add(player.toString());
        }
        return players;
    }
    
    /* Get a Player in a Position
     */
    public Player getPlayer(String position, int index) {
        Position p = getPosition(position);
        List<Player> players = p.players;
        
        ListIterator playerItr = players.listIterator(index);
        Player player = (Player) playerItr.next();
        return player;
    }
    
    /* Get a Position
     */
    public Position getPosition(String position) {
        int positionIndex = 0;
        
        // get the index of this position so we know where to insert
        if (position.equals("PG")) {
            positionIndex = PG;
        }
        else if (position.equals("SG")) {
            positionIndex = SG;
        }
        else if (position.equals("SF")) {
            positionIndex = SF;
        }
        else if (position.equals("PF")) {
            positionIndex = PF;
        }
        else if (position.equals("C")) {
            positionIndex = C;
        }
        
        // insert player at position index
        ListIterator chartItr = chart.listIterator(positionIndex);
        return (Position) chartItr.next();
    }
}
