package puzzles.jam.model;

// TODO: implement your JamConfig for the common solver

import puzzles.common.solver.Configuration;

import java.util.Collection;

public class JamConfig implements Configuration {
    @Override
    public boolean isSolution() {
        return false;
    }

    @Override
    public Collection<Configuration> getNeighbors() {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}
