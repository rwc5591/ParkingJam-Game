package puzzles.strings;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main class for the strings puzzle.
 *
 * @author Reg Chuhi
 */
public class Strings {
    /**
     * Run an instance of the strings puzzle.
     *
     *
     * @param args [0]: the starting string;
     *             [1]: the finish string.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(("Usage: java Strings start finish"));
        }
        else  {
            System.out.println("Start: "+ args[0] +", End: "+args[1]);
            StringsConfig s = new StringsConfig(args[0], args[0], args[1]);
            Solver solv = new Solver();
            List<Configuration> sol = solv.solve(s);
            System.out.println("Total configs: " + StringsConfig.configCount );
            System.out.println("Unique configs: "  + Solver.uniqConfigCount);
            if(sol!=null) {
                for (int i = 0; i < sol.size(); i++) {
                    System.out.println("Step " + i + ": " + sol.get(i));

                }

            }
            else {
                System.out.println("No solution");
            }


        }


    }}
