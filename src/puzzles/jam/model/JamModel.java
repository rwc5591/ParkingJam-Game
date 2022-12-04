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
    private Solver solver;


    private static String filename;
    private boolean isSelected;
    private char selectedCar;
    private static int moves;
    public static final char EMPTY= '.';

    public JamModel(){
        this.solver=new Solver();
        isSelected=false;

    }
    public void getHint(){
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
                alertObservers("Next step!");
            }
        }

    }
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
    public void loadGame(String filename){
        try {
            Scanner scan = new Scanner(new File(filename));
            JamModel.filename =filename;
            currentConfig=new JamConfig(filename);
              alertObservers("Loaded: "+ filename);
        } catch (FileNotFoundException e) {
            alertObservers("Failed to load: ");
            
        }



    }
    public void reset(){
        currentConfig=new JamConfig(filename);
        alertObservers("Reset: ");

    }
    public char getCell(int row, int col){
        return currentConfig.cell(row,col);
    }
    public void gameOver(){
        if(currentConfig.cell(JamConfig.row,JamConfig.col-1)=='X'){
            alertObservers("Game over!");
            }
        }


    /**
     * The view calls this to add itself as an observer.
     *
     * @param observer the view
     */
    //load config/ reset the game get hints selections  tostring for the tui quit
    //, load, ,quit,reset.




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
