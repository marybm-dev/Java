
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 3340 - Week 4 Assignment
 * Winter 2015
 * 02/01/2015
 *
 * Main.java
 * Candy Land Game implementation
 * 
 * Changes in Design:
 * Only one change was made. Position.java has a member variable and method to return
 * if this Position is the end of game. It seeemed much more straight forward to check
 * the index of the Position in the game to determine if the Player is on the last Position.
 * The member variable and method are there and should work, however, I found that I liked
 * the simpler, more straight forward approach of checking the index.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    // we do not define first element of colors array because modulo is used to determine
    // which position gets which color and we do not want to use index 0 (0 % n == 0)=>(always =0)
    public final static String[] colors = {"", "red", "orange", "yellow", "green", "blue", "purple"};
    
    // Position array is global so we can access contents in methods
    public static Position[] positions;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Main member variables used to play the game
        Player player1, player2, currentPlayer = null;
        Position currentPosition = null;
        Card currentCard = null;
        String currentColor = "";
        Integer squaresQty = 0;
        Integer oldPositionIndex = 0;
        Integer newPositionIndex = 0;
        Integer moves = 0;
        Integer counter = 0;                // counter keeps track of each game iteration
        Boolean gameOver = false;
        Boolean tryAgainDrawCard = false;
        Boolean tryAgainColor = false;
        Boolean tryAgainQty = false;
        Boolean colorMatch = false;
        Boolean qtyMatch = false;
        Boolean drawCard = false;
        
        // Initialize the Positions, Cards and shuffle the cards
        positions = initializePositions();
        List cards = initializeCards();
        Collections.shuffle(cards);
        
        // Get input to create Players
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Candy Land Game!");
        System.out.print("To get started please enter Player 1's name: ");
        player1 = new Player(input.next());
        System.out.println("Thanks, " + player1.getName());
        System.out.print("Now plese enter Player 2's name: ");
        player2 = new Player(input.next());
        System.out.println("Thanks, " + player2.getName());
        System.out.println("\nLet's Play!");
        
        // Player one begins the game, set this player's turn to True
        player1.toggleTurn();
        
        while (!gameOver) {
            
            // we only do this if it's the next player's turn
            // e.g. if the player selected the wrong square, we do not repeat this
            if (!tryAgainColor && !tryAgainQty) {
                
                // Determine which player is the current one
                if (player1.isCurrentTurn())
                    currentPlayer = player1;
                else
                    currentPlayer = player2;
            
                // Begin the game
                System.out.println("\n" + currentPlayer.getName() + "'s turn");
                
                do {
                    try {
                        tryAgainDrawCard = false;
                        System.out.print("Press 1 to draw a card from the stack: ");
                        drawCard = (input.nextInt() == 1);
                    }
                    catch (Exception e) {
                        System.out.print("Error: Invalid Entry, try again.");
                        tryAgainDrawCard = true;
                    }
                } while (!tryAgainDrawCard);
                
                if (drawCard) {
                    // Player draws a card
                    currentCard = (Card) cards.get(counter);
                    System.out.println("   Card color: " + currentCard.getColor() + "\n   Card Sqaures: " + currentCard.getSquaresQty());

                    // Player selects the color and quantity to move
                    System.out.print("Enter the color on your card: ");
                    currentColor = input.next();
                    colorMatch = currentColor.equals(currentCard.getColor());
                }
            }
            
            // Let the player try again after entering the wrong color
            if (tryAgainColor) {
                System.out.println("   Card color: " + currentCard.getColor() + "\n   Card Sqaures: " + currentCard.getSquaresQty());

                // Player selects the color and quantity to move
                System.out.print("Enter the color on your card: ");
                currentColor = input.next();
                colorMatch = currentColor.equals(currentCard.getColor());
            }
            
            // Let the player try again after entering the wrong quantity of squares
            if (colorMatch || tryAgainQty) {
                if (tryAgainQty)
                    System.out.println("   Card color: " + currentCard.getColor() + "\n   Card Sqaures: " + currentCard.getSquaresQty());
                System.out.print("Enter the quantity of squares on your card: ");
                squaresQty = input.nextInt();
                qtyMatch = (squaresQty == currentCard.getSquaresQty());
            }
            
            // update the flags to see if user selected the correct/incorrect color or qty
            if (!colorMatch)
                tryAgainColor = true;
            else
                tryAgainColor = false;
            if (!qtyMatch)
                tryAgainQty = true;
            else
                tryAgainQty = false;
            
            // Let player continue if they matched the color and qty of squares
            if (colorMatch && qtyMatch) {
                
                // get their old position, now find their new position
                oldPositionIndex = currentPlayer.getCurrentPosition();
                currentPosition = getNewPosition(currentColor, squaresQty, oldPositionIndex);
                
                // if new position has a bridge, move to the other side
                if (currentPosition.hasBridge()) {
                    System.out.println("\n" + currentPlayer.getName() + ", you found a bridge!");
                    Position otherSide = currentPosition.getOtherSideOfBridge();
                    newPositionIndex = otherSide.getIndex();
                }
                else
                    newPositionIndex = currentPosition.getIndex();
                
                // inform the player they moved n positions
                moves = newPositionIndex - oldPositionIndex;
                currentPlayer.setCurrentPosition(newPositionIndex);
                System.out.println("Great Job, " + currentPlayer.getName() + "! You moved " + moves + " position(s)");
                
                // check if this player wins the game
                if (newPositionIndex == 125) {
                    System.out.println("\nCongratulations, " + currentPlayer.getName() + " Wins!");
                    System.out.println("*** GAME OVER ***");
                    gameOver = true;
                    break;
                }
            }
            else {
                // Player input the wrong entry - offset the Player's turn toggle
                // Toggle players so the player has another chance to input the right entry
                System.out.println("Sorry! Wrong entry, try again. ");
                player1.toggleTurn();
                player2.toggleTurn();
                counter--;
            }
            
            // Toggle which player goes next - happens each iteration of the game
            // This can be offset if the user simply input the wrong color or square to move to
            player1.toggleTurn();
            player2.toggleTurn();
            counter++;
            
            // Display current score board
            if (counter%2 == 0 && (!tryAgainColor && !tryAgainQty)) {
                if (player1.getCurrentPosition() == player2.getCurrentPosition()) 
                    System.out.println("This game is neck to neck to right now!");
                else if (player1.getCurrentPosition() < player2.getCurrentPosition())
                    System.out.println("\n" + player2.getName() + " is in the lead!");
                else
                    System.out.println("\n" + player1.getName() + " is in the lead!");
                System.out.println(player1.getName() + "'s position: " + player1.getCurrentPosition());
                System.out.println(player2.getName() + "'s position: " + player2.getCurrentPosition());
            }
        }
    }
    
    /**
     * InitializeCards Method
     * Creates card array used to draw from in game
     * @return List of cards
     */
    public static List initializeCards() {
        String color;
        int qty;
        List<Card> cardsArray = new ArrayList<Card>();
        
        for (int i = 0; i < 60 ; i++) {
            
            // temp variable used for % (modulo) to work in color selection
            // we do not want (0 % n == 0)=>(always =0)
            int temp = i+1;
            
            if (temp % 6 == 0)
                color = colors[6];
            else if (temp % 5 == 0)
                color = colors[5];
            else if (temp % 4 == 0)
                color = colors[4];
            else if (temp % 3 == 0)
                color = colors[3];
            else if (temp % 2 == 0)
                color = colors[2];
            else
                color = colors[1];
            
            // arbitrary 3 here, just to determine which cards get 2 squares
            if (i % 3 == 0)
                qty = 2;
            else
                qty = 1;

            cardsArray.add(new Card(color,qty));
        }
        return cardsArray;
    }
    
     /**
     * InitializePositions Method
     * Creates position array used as positions in game
     * @return Position array
     */
    public static Position[] initializePositions() {
        String color;
        Position[] positionsArray = new Position[126];
        for (int i = 0; i < 126 ; i++) {
            
            // temp variable used for % (modulo) to work in color selection
            // we do not want (0 % n == 0)=>(always = 0)
            int temp = i+1;
            
            if (temp % 6 == 0)
                color = colors[6];
            else if (temp % 5 == 0)
                color = colors[5];
            else if (temp % 4 == 0)
                color = colors[4];
            else if (temp % 3 == 0)
                color = colors[3];
            else if (temp % 2 == 0)
                color = colors[2];
            else
                color = colors[1];

            // set the last position's end flag to true, otherwise it's false
            if (i == 125)
                positionsArray[i] = new Position(color, false, true, null, i);
            else
                positionsArray[i] = new Position(color, false, false, null, i);
        }
        
        // update the positions that have bridges - these are one way to prevent
        // player from going backwards in game, set neighbor bridge to null
        // 5 to 61, 38 to 47
        positionsArray[5] = new Position(positionsArray[5].getColor(), true, false, positionsArray[61], 5);
        positionsArray[38] = new Position(positionsArray[38].getColor(), true, false, positionsArray[47], 38);
        positionsArray[47] = new Position(positionsArray[47].getColor(), false, false, null, 47);
        positionsArray[61] = new Position(positionsArray[61].getColor(), false, false, null, 61);
        
        return positionsArray;
    }
    
     /**
     * MatchPosition method
     * Finds the next Player's position
     * @param color on the card the player drew
     * @param squares the quantity of squares on the card the player drew
     * @param currentPosition the current player's position - this determines where we begin to search for the next position
     * @return Position the new position for the player
     */
    public static Position getNewPosition(String color, int squares, int currentPosition) {
        Position position = null;
        int counter = 0;
       
        // we begin looking for the next position at the neighboor position (currentPosition+1)
        // because we do not want to land on the same square if player selects card with same color
        for (int i = currentPosition+1 ; i < positions.length; i++) {
            // we reached the ending position of the game and this is the winner
            // we check this first so we don't continue to search if the game is really over
            if (i == 125) {
                position = positions[i];
                break;
            }
            
            // if the color matches the one we need then increment the counter
            if (positions[i].getColor().equals(color))
                counter++;
            
            // break out of loop once we have the conditions we need to return the new position
            if ( (counter == 1 && squares == 1) || (counter == 2 && squares == 2) ) {
                position = positions[i];
                break;
            }
        }
        return position;
    }
}
