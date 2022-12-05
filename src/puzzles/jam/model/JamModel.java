package puzzles.jam.model;

import puzzles.common.Observer;
import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class JamModel {
    /** the collection of observers of this model */

    private final List<Observer<JamModel, String>> observers = new LinkedList<>();

    /** the current configuration */
    private JamConfig currentConfig;
    //private Solver solver;
    /**
     * Message sent when a board has successfully loaded.
     */
    public static String LOADED = "loaded";
    /**
     * Message sent when a board has failed to load.
     */
    public static String LOAD_FAILED = "loadFailed";
    public static String HINT_PREFIX = "Hint:";


    private static String filename;
    private boolean isSelected;
    private char selectedCar;
    private static int moves;
    public static final char EMPTY= '.';

    public JamModel(){
      //  this.solver=new Solver();
        isSelected=false;

    }

    /**
     * Checks if current config is a solution
     * moves the car to the next position
     * alerts observers if already solved or no solution
     */
    public void getHint(){
        Solver solver = new Solver();
        if(currentConfig.isSolution()){
            alertObservers("Already Solved!");

        }
        else {
            LinkedList<Configuration> hint= solver.solve(currentConfig);
            if(hint==null){
                alertObservers("No solution!");
            }
            else {
                currentConfig=(JamConfig)hint.get(1);
                alertObservers(HINT_PREFIX +"Next step!");
            }
        }

    }

    public static int getMoves() {
        return moves;
    }

    /**
     * @param row
     * @param col
     * Selects the car to be moved and the position its moving to
     * alerts observers of movement or if it cant move
     */
    public void select(int row, int col){
        if(isSelected){
            isSelected=false;
            if(currentConfig.cell(row,col)!=EMPTY || !currentConfig.canMove(row,col,selectedCar)){
                alertObservers("Can't move car!");
            }
            else {
                currentConfig.moveCar(row,col,selectedCar);
                moves++;
                alertObservers("Moved from  " + row +", "+ col );
            }

        }
        else {
            isSelected=true;
            if(currentConfig.cell(row, col)!=EMPTY){
                selectedCar= currentConfig.cell(row,col);
                alertObservers("Selected " + row +", " + col);
            }
            else {
                    isSelected=false;
                    alertObservers("No car at " + row +", "+ col);
                }
            }


    }

    /**
     * @param filename
     * Loads the game from file
     * alerts observers if loaded or not
     */
    public void loadGame(String filename){
        try {
            Scanner scan = new Scanner(new File(filename));
            JamModel.filename =filename;
            currentConfig=new JamConfig(filename);
              alertObservers(LOADED + filename);
        } catch (FileNotFoundException e) {
            alertObservers(LOAD_FAILED);
            
        }



    }

    /**
     * Resets the game back to the start
     */
    public void reset(){
        currentConfig=new JamConfig(filename);
        alertObservers("Reset: ");

    }

    /**
     * @param row
     * @param col
     * @return the current cell on the board
     */
    public char getCell(int row, int col){
        return currentConfig.cell(row,col);
    }

    /**
     * @return checks if game is over
     * alerts observers
     */
    public boolean gameOver(){
        if(currentConfig.cell(JamConfig.row,JamConfig.col-1)=='X'){
            alertObservers("Game over!");
            }
        return false;
    }


    /**
     * The view calls this to add itself as an observer.
     *
     * @param observer the view
     */


    public void addObserver(Observer<JamModel, String> observer) {
        this.observers.add(observer);
    }

    /**
     * The model's state has changed (the counter), so inform the view via
     * the update method
     */
    private void alertObservers(String data) {
        for (var observer : observers) {
            observer.update(this, data);
        }
    }
}
