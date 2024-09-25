package edu.iastate.cs2280.hw1;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author <<Marshall Alexander>>
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getWidth(), tOld.getLength());
		for(int i = 0; i <= tOld.getLength() - 1; i++) {
		//goes through the old grid
			for(int j = 0; j <= tOld.getWidth()-1; j++) {
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
				//creates a new grid after the next fuction is called
			}
		}
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
	    int p = 0;
	    for(int i = 0; i < town.getLength(); i++) {
	        for(int j = 0; j < town.getWidth(); j++) {
	            if(town.grid[i][j].who().equals(State.CASUAL)) {
	                p++;
	            }
	        }
	    }
	    return p;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		Scanner scan = new Scanner(System.in);
	    System.out.println("Type 1 to input a file");
	    System.out.println("or 2 to generate randomly");
	    int choice = scan.nextInt();
	    scan.nextLine();
	    Town town;
	    switch (choice) {
	      case 1:
	        System.out.println("Please enter file path:");
	        String file = scan.nextLine();
	        Scanner fileScan = new Scanner(file);
	        try {
	          town = new Town(file);
	          int profit = 0;
	          for (int i = 0; i < 12; i++) {
	            Town tNew = updatePlain(town);
	            profit += getProfit(town);
	            town = tNew;
	          }
	          System.out.println("Profit: " +String.format("%.2f",(double)(100 * profit)/((town.getWidth() * town.getLength()) * 12))+"%");
	        } catch (FileNotFoundException e) {
	          System.out.println("File not found");
	        }
	        break;
	      case 2:
	        System.out.println("Provide rows then columns and then seed integer separated by spaces:");
	        int rows = scan.nextInt();
	        int cols = scan.nextInt();
	        int seed = scan.nextInt();
	        town = new Town(rows, cols);
	        town.randomInit(seed);
	        int profit = 0;
	        for (int i = 0; i < 12; i++) {
	          Town tNew = updatePlain(town);
	          profit += getProfit(town);
	          town = tNew;
	        }
	        System.out.println("Profit: " +String.format("%.2f",(double)(100 * profit)/((town.getWidth() * town.getLength()) * 12))+"%");
	        break;
	    }

	    scan.close();
	  }
}
