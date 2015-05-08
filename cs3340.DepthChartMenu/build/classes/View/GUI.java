/*
 * Maria Martinez - mlmartinez85@gmail.com
 * CS3340 - HW #7
 * 03/09/2015
 * 
 * GUI.java
 * Implementation code for interface related objects
 */

package View;
import Model.*;
import Controller.*;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUI {
    
    /* Instance variables
    */
    private BorderPane root;
    private GridPane grid;
    private Scene scene;
    private MenuBar menuBar;
    private Menu fileMenu;
    private MenuItem save;
    private MenuItem exit;
    private ComboBox positionsBox;
    private TextField fnameTextField;
    private TextField lnameTextField;
    private TextField numTextField;
    public ObservableList<HBoxCell> pgPlayers;
    public ObservableList<HBoxCell> sgPlayers;
    public ObservableList<HBoxCell> sfPlayers;
    public ObservableList<HBoxCell> pfPlayers;
    public ObservableList<HBoxCell> cPlayers;
    private ListView<HBoxCell> pgListView;
    private ListView<HBoxCell> sgListView;
    private ListView<HBoxCell> sfListView;
    private ListView<HBoxCell> pfListView;
    private ListView<HBoxCell> cListView;
    private DepthChart chart;
    
   /* Constructor
    * @param primaryStage
    * Creates all GUI object
    */
    public GUI(Stage primaryStage) {
        chart = new DepthChart();
        initializeGrid(primaryStage);
        initializeLabelsFields();
        initializeButtons();
        initializePlayerListViews();
        scene = new Scene(root, 1100, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
   /* SubClass of HBox
    * Used as cells in ListView with a button to remove players
    */
    public static class HBoxCell extends HBox {
        public Label playerLabel = new Label();
        public Button removePlayerButton = new Button();
        
       /* HBoxCell Constructor
        * @param playerInfo - player's name to add to the cell
        */
        public HBoxCell(String playerInfo) {
            super();
            playerLabel.setText(playerInfo);
            playerLabel.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(playerLabel, Priority.ALWAYS);
            removePlayerButton.setText("x");
            removePlayerButton.setStyle("-fx-text-fill: red;");
            this.getChildren().addAll(playerLabel, removePlayerButton);
        }
        
       /* HBoxCell buttonListener() method
        * add setOnAction to the delete button
        */
        public void buttonListener(DepthChart _chart, Position _position, Player _player, ListView<HBoxCell> _players) {
            removePlayerButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    
                    // We force the user to select a row in order to delete a user
                    final int selectedIdx = _players.getSelectionModel().getSelectedIndex();
                    try {
                        DeletePlayerController dpc = new DeletePlayerController(_chart, _position, _player);
                        _players.getItems().remove(selectedIdx);
                    }
                    
                    // User did not select a row, so display a warning dialog
                    catch(ArrayIndexOutOfBoundsException ex) {
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        dialogStage.setTitle("Error");
                        Text message = new Text("Please select the Player's name in ListView first.");
                        Button okButton = new Button("OK");
                        okButton.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent ae) {
                                dialogStage.close();
                                System.out.println("close");
                            }
                        });
                        dialogStage.setScene(new Scene(VBoxBuilder.create().
                            children(message, okButton).
                            alignment(Pos.CENTER).padding(new Insets(5)).build()));
                        dialogStage.show();
                    }
                    _players.getSelectionModel().clearSelection();
                }
            });
        }
    }
    
   /* MODIFIED Initialize Grid
    *
    * Added MenuBar, Menu, MenuItem
    * Each MenuItem has an EventHandler
    * - Save writes to disk and gives user feedback of success
    * - Exit terminates the application
    *
   */
    public void initializeGrid(Stage _primaryStage) {
        root = new BorderPane();
        
        // Create menu items and set action listeners
        menuBar = new MenuBar();
        fileMenu = new Menu("File");
        
        // Save file
        save = new MenuItem("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                chart.savePlayers();
                chart.fileHasChanges = true;

                // User Feedback
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.setTitle("Success");
                Text message = new Text("File was updated and saved.");
                Button okButton = new Button("OK");
                okButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent ae) {
                        dialogStage.close();
                    }
                });
                dialogStage.setScene(new Scene(VBoxBuilder.create().
                                        children(message, okButton).
                                        alignment(Pos.CENTER).padding(new Insets(5)).build()));
                dialogStage.show();
            }
        });
        
        // Exit application
        exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Platform.exit();
            }
        });
        
        // add items to file menu
        fileMenu.getItems().add(save);
        fileMenu.getItems().add(exit);
        menuBar.setPrefWidth(_primaryStage.getWidth());
        menuBar.getMenus().addAll(fileMenu);
        root.setTop(menuBar);
        
        // set grid properties
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        root.setCenter(grid);
    }
    
    // Initialize labels and text fields
    public void initializeLabelsFields() {
        Text scenetitle = new Text("Depth Chart Players");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        // Combo box for new position player
        Label positionLabel = new Label("Position:");
        grid.add(positionLabel, 0, 1);
        ObservableList<String> positions = FXCollections.observableArrayList("PG", "SG", "SF", "PF", "C");
        positionsBox = new ComboBox(positions);
        grid.add(positionsBox, 1, 1);
        
        // New Player text fields and labels
        Label firstName = new Label("First Name:");
        grid.add(firstName, 0, 2);
        fnameTextField = new TextField();
        grid.add(fnameTextField, 1, 2);
        Label lastName = new Label("Last Name:");
        grid.add(lastName, 0, 3);
        lnameTextField = new TextField();
        grid.add(lnameTextField, 1, 3);
        Label playerNum = new Label("Player #:");
        grid.add(playerNum, 0, 4);
        numTextField = new TextField();
        grid.add(numTextField, 1, 4);
        
        // Position labels
        Label pgLabel = new Label(" PG");
        grid.add(pgLabel, 0, 7);
        Label sgLabel = new Label(" SG");
        grid.add(sgLabel, 1, 7);
        Label sfLabel = new Label(" SF");
        grid.add(sfLabel, 2, 7);
        Label pfLabel = new Label(" PF");
        grid.add(pfLabel, 3, 7);
        Label cLabel = new Label("  C");
        grid.add(cLabel, 4, 7);
        
    }
    
    // Initialize Add Player button
    public void initializeButtons() {
        Button addPlayerButton = new Button("Add Player");
        addPlayerButton.setStyle("-fx-text-fill: green;");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.TOP_RIGHT);
        hbBtn.getChildren().add(addPlayerButton);
        grid.add(hbBtn, 1, 5);

        // setOnAction for Add Player button
        addPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String tempFirst = fnameTextField.getText();
                String tempLast = lnameTextField.getText();
                int tempNum = Integer.parseInt(numTextField.getText());
                
                // call the controller class to handle creation of a new player
                NewPlayerController npc = new NewPlayerController(chart, positionsBox, fnameTextField, lnameTextField, numTextField);
                String position = (String) positionsBox.getSelectionModel().getSelectedItem().toString();
                Position pos = chart.getPosition(position);
                ListView<HBoxCell> myListView = getListView(position);
                ObservableList<HBoxCell> myList = getPlayerList(position);
                HBoxCell newCell = new HBoxCell(fnameTextField.getText() + " " + lnameTextField.getText() + ", #" + numTextField.getText());
                newCell.buttonListener(chart, pos, new Player(tempFirst, tempLast, tempNum), myListView);
                myList.add(newCell);

                // clear the text fields
                positionsBox.getSelectionModel().clearSelection();
                fnameTextField.setText("");
                lnameTextField.setText("");
                numTextField.setText("");
            }
        });
    }
    
    // Initialize ListViews with lists of Players in Positions
    public void initializePlayerListViews() {
        pgListView = new ListView<HBoxCell>();
        pgPlayers = FXCollections.observableArrayList (createCells("PG", pgListView));   
        setList(pgListView, pgPlayers);
        grid.add(pgListView, 0, 8);
        
        sgListView = new ListView<HBoxCell>();
        sgPlayers = FXCollections.observableArrayList (createCells("SG", sgListView));
        setList(sgListView, sgPlayers);
        grid.add(sgListView, 1, 8);
        
        sfListView = new ListView<HBoxCell>();
        sfPlayers = FXCollections.observableArrayList (createCells("SF", sfListView));
        setList(sfListView, sfPlayers);
        grid.add(sfListView, 2, 8);
        
        pfListView = new ListView<HBoxCell>();
        pfPlayers = FXCollections.observableArrayList (createCells("PF", pfListView));
        setList(pfListView, pfPlayers);
        grid.add(pfListView, 3, 8);
        
        cListView = new ListView<HBoxCell>();
        cPlayers = FXCollections.observableArrayList (createCells("C", cListView));
        setList(cListView, cPlayers);
        grid.add(cListView, 4, 8);
    }
    
   /* createCells() method
    * @param position name
    * @param ListView<HBoxCell>
    * @return List<HBoxCell>
    *
    * Iterates through string representation of Player and adds then to a List<HBoxCell>
    * Uses ListView<HBoxCell> to send as parameter to buttonListener() of HBoxCell class
    */
    public List<HBoxCell> createCells(String position, ListView<HBoxCell> listView) {
        List<String> players = chart.getPositionPlayers(position);
        List<HBoxCell> cells = new ArrayList();
        
        for (int i = 0 ; i < players.size(); i++) {
            String currentPlayer = players.get(i);
            HBoxCell hbcell = new HBoxCell(currentPlayer);
            Player player = chart.getPlayer(position, i);
            Position pos = chart.getPosition(position);
            hbcell.buttonListener(chart, pos, player, listView);
            cells.add(hbcell);
        }
        
        return cells;
    }
    
   /* setList() method
    * @param ListView<HBoxCell>
    * @param ObservableList<HBoxCell>
    *
    * Adds ObservableList of players to their ListView and sets view preferences
    */ 
    public void setList(ListView<HBoxCell> positionListView, ObservableList<HBoxCell> playersList) {
        positionListView.setItems(playersList);
        positionListView.setPrefWidth(200);
        positionListView.setPrefHeight(180);
    }
    
   /* getPlayerList() method
    * @param position name
    * @return ObservableList<HBoxCell>
    *
    * Returns the ObservableList<HBoxCell> of the given position name
    */ 
    public ObservableList<HBoxCell> getPlayerList(String position) {
        if (position.equals("PG")) {
            return pgPlayers;
        } 
        else if (position.equals("SG")) {
            return sgPlayers;
        }
        else if (position.equals("SF")) {
            return sfPlayers;
        }
        else if (position.equals("PF")) {
            return pfPlayers;
        }
        else {
            return cPlayers;
        }
    }

   /* getPlayerList() method
    * @param position name
    * @return ListView<HBoxCell>
    *
    * Returns the ListView<HBoxCell> of the given position name
    */
    public ListView<HBoxCell> getListView(String position) {
        if (position.equals("PG")) {
            return pgListView;
        } 
        else if (position.equals("SG")) {
            return sgListView;
        }
        else if (position.equals("SF")) {
            return sfListView;
        }
        else if (position.equals("PF")) {
            return pfListView;
        }
        else {
            return cListView;
        }
    }

}
