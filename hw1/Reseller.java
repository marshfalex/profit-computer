package edu.iastate.cs2280.hw1;

public class Reseller extends TownCell {

    public Reseller(Town p, int r, int c) {
        super(p, r, c);
    }
    
    @Override
    public State who() {
        return State.RESELLER;
    }
    
    @Override
    public TownCell next(Town tNew) {
        int nCensus[] = new int[NUM_CELL_TYPE];
        census(nCensus);
        nCensus[RESELLER]--; // Exclude self from census
        
        // Transition rules for Reseller cells
        if (nCensus[CASUAL] <= 3 || nCensus[EMPTY] >= 3) {
            return new Empty(tNew, row, col);
        } else {
            return new Reseller(tNew, row, col);
        }
    }
}