package edu.iastate.cs2280.hw1;

public class Streamer extends TownCell {
    
    public Streamer(Town p, int r, int c) {
        super(p, r, c);
    }
    
    @Override
    public State who() {
        return State.STREAMER;
    }
    
    @Override
    public TownCell next(Town tNew) {
        int nCensus[] = new int[NUM_CELL_TYPE]; 
        census(nCensus);
        nCensus[STREAMER]--; // Exclude self from census
        
        // Transition rules for Streamer cells
        if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
            return new Reseller(tNew, row, col);
        } else if (nCensus[RESELLER] > 0) {
            return new Outage(tNew, row, col);
        } else if (nCensus[OUTAGE] > 0) {
            return new Empty(tNew, row, col);
        } else if (nCensus[CASUAL] >= 5) {
            return new Streamer(tNew, row, col);
        } else {
            return new Streamer(tNew, row, col);
        }
    }
}