package edu.iastate.cs2280.hw1;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ResellerTest {

	@Test
	void test1() {
		
		Town t = new Town(2,2);
		t.grid[0][0] = new Reseller(t,0,0);
        t.grid[0][1] = new Casual(t,0,1);
        t.grid[1][0] = new Casual(t,1,0);
        t.grid[1][1] = new Casual(t,1,1);
        assertEquals(State.EMPTY, t.grid[0][0].next(t).who());
	}
}
