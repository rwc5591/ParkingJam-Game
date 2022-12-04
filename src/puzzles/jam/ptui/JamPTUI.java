package puzzles.jam.ptui;

import puzzles.common.Observer;
import puzzles.jam.model.JamModel;

import java.util.Scanner;

public class JamPTUI implements Observer<JamModel, String> {
    private JamModel model;
    private boolean gameOn;
    private Scanner in;




    public JamPTUI(){
        model = new JamModel();
        model.addObserver(this);
        gameOn = false;
        in = new Scanner( System.in );

    }


    @Override
    public void update(JamModel jamModel, String message) {
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java JamPTUI filename");
        }


    }
}
