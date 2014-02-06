package proj4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Project4 {

	private int inputs,interval, tableSize, R; // arguments variable
	public Project4(String[] args) throws InvalidArgumentException{
		if(args.length != 5)
			throw new InvalidArgumentException("Too many/little command Arguments");
		String inputFile = args[0];
		inputs = Integer.parseInt(args[1]);
		interval= Integer.parseInt(args[2]);
		tableSize = Integer.parseInt(args[3]);
		R = Integer.parseInt(args[4]);
		if(inputs < interval)
			throw new InvalidArgumentException("Interval is way to big");
		try {
			File file = new File(inputFile);	
			ProbingHashTable<Integer> hashTable = null;
			linearFunction(file, hashTable);
			quadFunction(file, hashTable);
			doubleFunction(file, hashTable);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find " + inputFile);
		}
	}
	/**
	 * print the outline of table in format
	 * @param type - of table
	 */
	private void printTable(String type){
		System.out.println("\n\t\t\t\t " + type + " Probing Analysis (Table size = " + tableSize + ")");
		System.out.println("\t\t   ----- Inserts ------    ----------- Probes ----------    --------- Clusters ---------");
		System.out.printf("%5s %10s %10s %10s %10s %10s %10s %10s %10s %10s\n", 
				"N", "lambda", "success", "failed", "total", "avg", "max", "number", "avg", "max");
	}
	/**
	 * running table in linear hashing method
	 * @param file of text that has list of integer
	 * @param linearProb - hashing table 
	 * @throws FileNotFoundException - throw when the file is not exist
	 */
	private void linearFunction(File file, ProbingHashTable linearProb) throws FileNotFoundException{
		Scanner numbers = new Scanner(file);
		int intervalCount = 0;		
		linearProb = new LinearProbingHashTable<Integer>(tableSize);
		printTable("Linear");
		while(numbers.hasNextInt() && intervalCount < inputs){
			linearProb.insert(numbers.nextInt());
			if(++intervalCount % interval == 0)
				linearProb.print(intervalCount);
		}
		numbers.close();
	}
	/**
	 * running table in quadratic hashing method
	 * @param file of text that has list of integer
	 * @param quadProb - hashing table 
	 * @throws FileNotFoundException - throw when the file is not exist
	 */
	private void quadFunction(File file, ProbingHashTable quadProb) throws FileNotFoundException{
		Scanner numbers = new Scanner(file);
		int intervalCount = 0;
		quadProb = new QuadraticProbingHashTable<Integer>(tableSize);
		printTable("Quadratic");
		while(numbers.hasNextInt() && intervalCount < inputs){
			quadProb.insert(numbers.nextInt());
			if(++intervalCount % interval == 0)
				quadProb.print(intervalCount);
		}
		numbers.close();
	}
	/**
	 * running table in double hashing method
	 * @param file of text that has list of integer
	 * @param doubleProb - hashing table 
	 * @throws FileNotFoundException - throw when the file is not exist
	 */
	private void doubleFunction(File file, ProbingHashTable doubleProb) throws FileNotFoundException{
		Scanner numbers = new Scanner(file);
		int intervalCount = 0;
		doubleProb = new DoubleProbingHashTable<Integer>(tableSize, R);
		printTable("Double");
		while(numbers.hasNextInt() && intervalCount < inputs){
			doubleProb.insert(numbers.nextInt());
			if(++intervalCount % interval == 0)
				doubleProb.print(intervalCount);
		}
		numbers.close();
	}
	public static void main(String[] args){
		try {
			new Project4(args);
		} catch (InvalidArgumentException e) {
			System.out.println(e.toString());
		}
	}
}
