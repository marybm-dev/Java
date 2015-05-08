/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW Assignment #2
 * 01/17/2015
 * 
 * Main.java
 * Crude depth chart management
 * In particular, it is "append-only", so a player can only
 * be added, and cannot be added in front of another player
 * at a position.
 * There is minimal error handling.
 */


package hw1;
import java.util.Scanner;

public class Main {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        DepthChart chart = new DepthChart();
        String[] commands = {"Quit program", "Add to position", "Display depth chart"};
        final int QUIT = 0, // 0 is code to quit the program
                  ADD = 1,
                  DISPLAY = 2,
                  NUM_COMMANDS = 3;
        Menu choices = new Menu(commands);

        // temporary variables to hold input values
        int choice = -1;
        String playerFirstName, playerLastName, position;
        int playerNumber;

        System.out.println("Welcome to Depth Chart Manager 0.01");
        Scanner input = new Scanner(System.in);
	
        // try catch block added to catch any excpetions, in particular to stop
        // the program when user inputs characters or a string to prevent
        // a system crash due to incompatibility 'choice' variable's integer type
	try{
            while ( (choice = choices.get()) != QUIT ) {
                // process the choice
                switch(choice) {
                case ADD:
                    System.out.print("Please enter the player's first name: ");
                    playerFirstName = input.next();
                    System.out.print("Please enter the player's last name: ");
                    playerLastName = input.next();
                    System.out.print("Please enter the player's uniform number: ");
                    playerNumber = input.nextInt();
                    System.out.print("Please enter the player's position.");
                    System.out.print("Valid positions are PG, SG, SF, PF, C : ");
                    position = input.next();
                    Player p = new Player(playerFirstName, playerLastName, playerNumber);
                    chart.add(position, p);
                    break;
                case DISPLAY:
                    System.out.print(chart.toString());
                    break;
                default:
                    System.err.print("Invalid choice: " + choice + "\nPlease try again\n");
                    break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("System Error: That input was not recognized.");			
	}
    }
}
