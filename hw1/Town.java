package edu.iastate.cs2280.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author <<Marshall Alexander>>
 *
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width){
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		File file = new File(inputFileName);
		Scanner input = new Scanner(file);
		// Read dimensions and initialize grid
		int length = input.nextInt();
		int width = input.nextInt();
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
		input.nextLine();
		
		// Populate grid from file
		for(int i = 0; i < length; i++) {
			String[] arrange = input.nextLine().split(" ");
			for(int j = 0; j < arrange.length; j++) {
				switch (arrange[j]) {
				case "R":
					grid[i][j] = new Reseller(this,i,j);
					break;
				case "C":
					grid[i][j] = new Casual(this,i,j);
					break;
				case "S":
					grid[i][j] = new Streamer(this,i,j);
					break;
				case "E":
					grid[i][j] = new Empty(this,i,j);
					break;
				case "O":
					grid[i][j] = new Outage(this,i,j);
					break;
				case "R\t":
					grid[i][j] = new Reseller(this,i,j);
					break;
				case "C\t":
					grid[i][j] = new Casual(this,i,j);
					break;
				case "S\t":
					grid[i][j] = new Streamer(this,i,j);
					break;
				case "E\t":
					grid[i][j] = new Empty(this,i,j);
					break;
				case "O\t":
					grid[i][j] = new Outage(this,i,j);
					break;
				default:
					break;
				}
			}
		}
		input.close();
	}
	
	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth() {
		//TODO: Write/update your code here.
		return grid[0].length;
	}
	
	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() {
		//TODO: Write/update your code here.
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		for(int i = 0; i <= length - 1; i++) {
			for(int j = 0; j <= width - 1; j++) {
				int temporary = rand.nextInt(5);
				if(temporary == TownCell.RESELLER) {
					grid[i][j] = new Reseller(this,i,j);
				}
				else if(temporary == TownCell.CASUAL) {
					grid[i][j] = new Casual(this,i,j);
				}
				else if(temporary == TownCell.STREAMER) {
					grid[i][j] = new Streamer(this,i,j);
				}
				else if(temporary == TownCell.EMPTY) {
					grid[i][j] = new Empty(this,i,j);
				}
				else if(temporary == TownCell.OUTAGE) {
					grid[i][j] = new Outage(this,i,j);
				}
			}
		}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < getLength(); i++) {
			s += "\n";
			for(int j = 0; j < getWidth(); j++) {
				if (grid[i][j].who().equals(State.RESELLER)){
					s = s + "R"  + " ";
				}
				else if (grid[i][j].who().equals(State.CASUAL)){
					s = s + "C" + " ";
				}
				else if (grid[i][j].who().equals(State.STREAMER)){
					s = s + "S" + " ";
				}
				else if (grid[i][j].who().equals(State.EMPTY)){
					s = s + "E" + " ";
				}
				else if (grid[i][j].who().equals(State.OUTAGE)){
					s = s + "O" + " ";
				}
			}
		}
		return s;
	}
}
