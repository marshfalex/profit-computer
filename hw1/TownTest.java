package edu.iastate.cs2280.hw1;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class TownTest {

	Town t;
	
	@Before
	public void initialize() throws FileNotFoundException{
		t = new Town(2,2);
		t.randomInit(5);
	}
	
	
	@Test
	void testL() {
		assertEquals(2, t.getLength());
	}
	
	@Test
	void testW() {
		assertEquals(2,t.getWidth());
	}
}
