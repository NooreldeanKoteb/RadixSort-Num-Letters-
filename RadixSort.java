// TO DO: add your implementation and JavaDoc
/**
 * @author Nooreldean Koteb G01085380
 * @version 1.0
 */
//These are all the imports you are allowed, don't add any more!
import java.util.Scanner;
import java.io.IOException;

public class RadixSort{
	// DO NOT MODIFY INSTANCE VARIABLES PROVIDED BELOW
	// EXCEPT TO ADD JAVADOCS
	/**
	 * Number of buckets (Base number)
	 */
	private int bucketSize;
	/**
	 * Round of radix sort
	 */
	private int roundNum = 0;
	//an array of buckets to be used in each round
	/**
	 * An Array holding other Arrays holding a sortable element
	 */
	private SmartArray<SmartArray<Sortable>> buckets;
	/**
	 * An Array holding a sortable element
	 */
	private SmartArray<Sortable>bucket;	
	//A SmartArray to store values to be sorted.
	//It is also used to store intermediate results after 
	//each round and the final group of sorted values.
	//The values should always be sorted in ascending order.	
	/**
	 * An Array holding a sortable element
	 */
	private SmartArray<Sortable> values; 
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	// constructor
	/**
	 * This is a constructor that creates the default array that will hold the incoming values
	 */
	public RadixSort(){
		this.values = new SmartArray<Sortable>();
	}
	// return true if the numbers are guaranteed to be sorted,
	// i.e. all digit locations have been inspected
	// return false otherwise
	/**
	 * This method will check if the list is sorted.
	 * @return boolean - true if list is sorted, false if it isn't
	 */
	public boolean isSorted() { 
		int j = 0;
		for (int i = 0; i < this.values.size()-1; i++) {
			j = this.values.get(i).paddedDigits().length();
			while (this.values.get(i).posToNum(j) == (this.values.get(i+1).posToNum(j))) {
				if(j != 0) {
					j--;
				}else {
					break;
				}
			}
			if(this.values.get(i).posToNum(j) > (this.values.get(i+1).posToNum(j))) {
				return false;
			}
		}
		return true;
	}
	// return which round is about to be performed
	// round number starts with 0 after a new group of value
	// gets initialized
	/**
	 * This method will return the round radix sort is in.
	 * @return int Round Number for radix sort
	 */
	public int getRound() {
		return this.roundNum;
	}
	
	// return the number of buckets to be used in each round
	/**
	 * This method will return number of buckets
	 * @return int number of buckets
	 */
	public int getNumBuckets() {
		return this.bucketSize;
	}

	// return the group of values to be sorted
	/**
	 * This method will return the array of values
	 * @return SmartArray - Values
	 */
	public SmartArray<Sortable> getValues() {
		return this.values;
	}

	// return the array of values corresponding to bucketNum
	// return null for invalid bucketNum
	/**
	 * This method will return the values within a specific bucket
	 * @param bucketNum - Takes in an int bucket number
	 * @return SmartArray - values in specified bucket
	 */
	public SmartArray<Sortable> getBucket(int bucketNum) {
		return this.buckets.get(bucketNum);
	}
	// return the max number of digits of the values
	/**
	 * This method will get the largest number's length
	 * @return int - largest number's length
	 */
	public int getMaxLength() {
		int length = 0;
		for (int i = (this.values.size() - 1); i !=-1 ; i--) {
			if(this.values.get(i).digits().length() > length) {
				length = this.values.get(i).digits().length();
			}
		}
		return length;
	}
	// read values from the provided scanner
	// get ready to sort the new group of values
		
	// first specify the base:
	// "a" - alphabetic string with CAPITAL LETTERS only;
	// otherwise it would be an integer base in [2,16]
	// (10 for decimal, 2 for binary, 16 for hexdecimal, etc.)
	
	// Note: you can assume the type input is always valid
	/**
	 * This method reads in all the values, finds the base then creates the buckets
	 * It then continues to add the rest of the values to SmartArray values
	 * @param s - File being read
	 * @throws IOException - can't read file/ not found
	 */
	public void initValuesFromScanner(Scanner s) throws IOException {	
		//--------------------------------------------------------
		// The following lines are provided to you as a starting point.
		// Use it as is or make changes as you want
		String type = s.next();
		int base;
		if (!type.equals("a")) {
			base = Integer.parseInt(type);
			System.out.println(type);
		}else {
			base = SortableString.BASE;
		}
		// end of provided code
		//--------------------------------------------------------
		// your code can start from here to finish 
		// reading from the scanner and initialization of values
		this.bucketSize = base;
		buckets = new SmartArray<SmartArray<Sortable>>(this.bucketSize);
		for (int i =0; i<this.bucketSize; i++) {
			bucket = new SmartArray<Sortable>();
			this.buckets.add(i,bucket);
		}
		int counter = 0;
		while(s.hasNext()) {
			String value = s.next();
			switch(this.bucketSize) {
				case(27):
					SortableString word = new SortableString(value);
					this.values.add(counter, word);
					break;
				default:
					SortableNumber num = new SortableNumber(this.bucketSize,value);
					this.values.add(counter, num);
					break;
			}
			counter++;
		}
	}
	// check the list of values and remove the ones with invalid digits
	// return the number of invalid values removed
	/**
	 * This method removes any value that has a lower case letter
	 * @return int - Number of items removed
	 */
	public int sanitizeValues(){
		int removed = 0;
		char letter;
		for (int i = (this.values.size()-1); i !=-1; i--) {
			for (int j = 0; j < this.values.get(i).digits().length(); j++) {
				letter = this.values.get(i).digits().charAt(j);
					if(this.bucketSize == 27) {
						if(Character.isLowerCase(letter) | Character.isDigit(letter)) {
							this.values.delete(i);
							removed++;
							break;
						}
					}else {
						if(this.values.get(i).posToNum(j) > this.bucketSize | Character.isLowerCase(letter)) {
							this.values.delete(i);
							removed++;
							break;
						}
				}
			}
		}
		return removed;
	}
	// make sure all values are of the same length
	// use padding if needed
	// return the number of values padded
	/**
	 * This method will pad all numbers and return the number of items padded
	 * @return int - Number of items padded
	 */
	public int setSameLength(){
		int length = getMaxLength();
		int padded = 0;
		for (int i = (this.values.size()-1); i !=-1; i--) {
			if(this.values.get(i).digits().length() < length) {
				this.values.get(i).padDigits(length);
				padded++;
			}
		}
		return padded;
	}
	// perform one round of radix sort
	//  - round number should be incremented by 1
	//  - instance variables buckets and values should be updated 
	//    based on LSD radix sort algorithm
	/**
	 * This method will run one round of Radix Sort
	 */
	public void oneRound(){
		int valuesIndex = 0;
		//clean Up buckets
		if(this.roundNum != 0) {
			for (int i = 0; i < this.bucketSize; i++) {
				while (this.buckets.get(i).size() != 0) {
					System.out.println(this.buckets.get(i).get(0).digits());
					this.buckets.get(i).delete(0);				
				}
			}
		}
		//Insert into buckets
		for (int i = 0; i < this.values.size(); i++) {
				this.buckets.get(this.values.get(i).posToNum(this.roundNum)).add(this.buckets.get(this.values.get(i).posToNum(this.roundNum)).size(),this.values.get(i));
			}
		//Insert items from buckets into values array
		for (int i = 0; i < this.bucketSize; i++) {
			for (int j = 0; j < this.buckets.get(i).size(); j++) {
				switch(this.bucketSize){
					case(27):
						SortableString newWord = new SortableString(this.buckets.get(i).get(j).paddedDigits());
						this.values.replace(valuesIndex,newWord);
						break;
					default:
						SortableNumber newNum = new SortableNumber(this.bucketSize,this.buckets.get(i).get(j).paddedDigits());
						this.values.replace(valuesIndex,newNum);
						break;
				}
				valuesIndex++;
			}
		}
		this.roundNum++;
	}
	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------
	/**
	 * This is the main method
	 * @param args
	 */
	public static void main(String[] args){
	}

}

