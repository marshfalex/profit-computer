package edu.iastate.cs2280.hw1;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class ISPBusinessTest {

		
	@Test
	  public void testMain() {
	    String input = "2\n" + "4 4 1\n";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));

	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    ISPBusiness.main(new String[] {});
	    System.out.flush();
	    System.setOut(old);
	    assertEquals("28.13%", baos.toString().split("Profit: ")[1].trim());
	  }

	  @Test
	  public void testGetProfit() {
	    Town TestTown = new Town(4, 4);
	    TestTown.randomInit(10);
	    assertEquals(1, ISPBusiness.getProfit(TestTown));
	  }

	  @Test
	  public void testUpdatePlain() {
	    Town TestTown = new Town(4, 4);
	    TestTown.randomInit(10);
	    assertEquals(
	      "E E E E \nC C O E \nC O E O \nC E E E \n",
	      ISPBusiness.updatePlain(TestTown).toString()
	    );
	  }
}