/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW #7
 * 03/09/2015
 * 
 * MODIFIED DepthChart.java
 * Now has loadPlayers() and savePlayers() methods to read and write to disk
 *
 * Implementation code related to depth chart for a basketball team
 */

package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

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
    public ArrayList<Player> playersArray = null;
    public Position playersPG = new Position("PG");
    public Position playersSG = new Position("SG");
    public Position playersSF = new Position("SF");
    public Position playersPF = new Position("PF");
    public Position playersC = new Position("C");
    
    public boolean fileHasChanges = true;
    private final String FIELD_SEP = "\t";
    private final String fileString = "players.txt";
    private File file = null;
    private Path path = null;
    
   /* Constructor
    * Adds basketball positions to chart ArrayList
    */
    public DepthChart() {
        chart.add(playersPG);
        chart.add(playersSG);
        chart.add(playersSF);
        chart.add(playersPF);
        chart.add(playersC);
        
        playersArray = new ArrayList<>();
        loadPlayers();
    }
    
   /* loadPlayers()
    * Ensures file is created if it doesn't exist
    * Reads data that is stored (if any) and loads it into array
    * The array is used in GUI.java to load ListViews
    */
    public void loadPlayers() {
        path = Paths.get(fileString);
        file = path.toFile();
        try {
            if(!Files.exists(path))
                Files.createFile(path);
            
            BufferedReader in = new BufferedReader(new FileReader(file));
            
            if(fileHasChanges)
                playersArray.clear();
            
            String line = in.readLine();
            while(line != null) {
                StringTokenizer t = new StringTokenizer(line, FIELD_SEP);
                
                String position = t.nextToken();
                String firstName = t.nextToken();
                String lastName = t.nextToken();
                String number = t.nextToken();
                
                Player p = new Player(firstName, lastName, Integer.parseInt(number));
                p.setPosition(position);
                playersArray.add(p);
                add(position, p);
                line = in.readLine();
            }
            in.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    
   /* savePlayers()
    * Writes data that is in array into the disk
    */
    public void savePlayers() {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            
            Player p = null;
            for (int i = 0 ; i < playersArray.size(); i++) {
                p = playersArray.get(i);
                out.println(p.getPosition() + FIELD_SEP + 
                            p.getFirstName() + FIELD_SEP + 
                            p.getLastName() + FIELD_SEP + 
                            p.getNumber());
            }
            out.close();
        }
        catch(IOException e) {
            System.out.println(e);
        }
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
