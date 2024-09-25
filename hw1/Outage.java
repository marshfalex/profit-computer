package edu.iastate.cs2280.hw1;

public class Outage extends TownCell {

    public Outage(Town p, int r, int s) {
        super(p, r, s);
    }
    
    @Override
    public State who() {
        return State.OUTAGE;
    }
    
    @Override
    public TownCell next(Town tNew) {
        int nCensus[] = new int[NUM_CELL_TYPE]; 
        census(nCensus);
        nCensus[OUTAGE]--; // Exclude self from census
        
        // Outage always transitions to Empty in the next cycle
        return new Empty(tNew, row, col);
    }
}