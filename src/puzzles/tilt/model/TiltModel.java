package puzzles.tilt.model;

import puzzles.common.Observer;
import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

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

    /**
     * The view calls this to add itself as an observer.
     *
     * @param observer the view
     */
    public void addObserver(Observer<TiltModel, String> observer) {
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

    private void announce( String arg ) {
        for ( var obs : this.observers ) {
            obs.update( this, arg );
        }
    }
    public boolean loadBoardFromFile(File filename)  {      // NOT COMPLETE
        try {
            Scanner in = new Scanner(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public Configuration getHint() {
        Solver solver = new Solver();
        List<Configuration> tiltPath = solver.solve(currentConfig);
        Configuration next = tiltPath.get(1);

        return next;
    }
    public Configuration tilt(char direction) {      // NOT COMPLETE
        return null;
    }

}
