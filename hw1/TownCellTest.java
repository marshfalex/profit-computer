package edu.iastate.cs2280.hw1;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class TownCellTest {
	
	
	@Test
	public void testCensus() 
	{
		Town t = new Town(4,4);
		t.randomInit(10);
		t.grid[0][1].census(TownCell.nCensus);
		assertEquals(2,TownCell.nCensus[TownCell.OUTAGE]);
	}
}
