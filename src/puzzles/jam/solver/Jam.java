package puzzles.jam.solver;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;
import puzzles.jam.model.JamConfig;


import java.util.List;

public class Jam {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Jam filename");
        }
        else  {
            JamConfig s = new JamConfig(args[0]);
            Solver solv = new Solver();
            List<Configuration> sol = solv.solve(s);
            System.out.println("Total configs: " + JamConfig.totalConfigs);
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
