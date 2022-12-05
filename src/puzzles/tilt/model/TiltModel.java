package puzzles.tilt.model;

import puzzles.common.Observer;
import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;
import puzzles.jam.model.JamConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TiltModel {
    /** the collection of observers of this model */
    private final List<Observer<TiltModel, String>> observers = new LinkedList<>();
    /** the current configuration */
    private TiltConfig currentConfig;

    //HINT, TILT, LOAD, QUIT, RESET

    /**
     * The view calls this to add itself as an observer.
     *
     * @param observer the view
     */
    public void addObserver(Observer<TiltModel, String> observer) {
        this.observers.add(observer);
    }

    public boolean gameOver(){
        return currentConfig.isSolution();
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

    public boolean loadFromFile(String filename)  {      // NOT COMPLETE
        try {
            currentConfig = new TiltConfig(filename);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public Configuration getHint() {
        Solver solver = new Solver();
        List<Configuration> tiltPath = solver.solve(currentConfig);
        Configuration next = tiltPath.get(1);
        // or return boolean of whether it can be solved and update current config if it can then use that for visual change
        return next;
    }
    public Configuration tilt(char direction) {      // NOT COMPLETE
        return null;
    }

}
