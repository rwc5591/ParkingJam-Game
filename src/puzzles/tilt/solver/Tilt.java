package puzzles.tilt.solver;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;
import puzzles.jam.model.JamConfig;
import puzzles.tilt.model.TiltConfig;

import java.util.List;

public class Tilt {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Tilt filename");
        }
        else  {
            TiltConfig tilter = new TiltConfig(args[0]);
            Solver solver = new Solver();
            List<Configuration> sol = solver.solve(tilter);
            System.out.println("Total configs: ");
            System.out.println("Unique configs: ");       //needs to use solver.getUniqueConfigCount , not Solver super class variable

            if(sol!=null) {
                for (int i = 0; i < sol.size(); i++) {
                    System.out.println("Step " + i + ": " + sol.get(i));
                }
            }
            else {
                System.out.println("No solution");
            }
        }
    }
}
