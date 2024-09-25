package edu.iastate.cs2280.hw1;

/**
 * 
 * @author <Marshall Alexander>
 *	Also provide appropriate comments for this class
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	protected int width;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
		width = p.getWidth();
	}
	
	/**
	 * Checks all neighborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		
		// zeros the counts of all customers
		
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0;
		
		int UB = Math.max(0, row - 1);
		int LB = Math.min(plain.grid.length - 1, row + 1);
		int leftB = Math.max(0, col - 1);
		int rightB = Math.min(plain.getWidth() - 1, col + 1);
				
		for(int i = LB; i >= UB; i--) {
			for(int j = rightB; j >= leftB; j--) {
				if (plain.grid[i][j].who() == State.RESELLER){
					nCensus[RESELLER]++;
				}
				else if (plain.grid[i][j].who() == State.CASUAL){
					nCensus[CASUAL]++;
				}
				else if (plain.grid[i][j].who() == State.STREAMER){
					nCensus[STREAMER]++;
				}
				else if (plain.grid[i][j].who() == State.EMPTY){
					nCensus[EMPTY]++;
				}
				else if (plain.grid[i][j].who() == State.OUTAGE){
					nCensus[OUTAGE]++;
				}
			}
		}
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}
