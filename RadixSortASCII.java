/**
 * Represents an ASCII UI that helps to apply radix sort on files
 * and to display the status of Radix Sort object. 
 * <p>
 * @author CS310 GMU
 * <p>
 * CS310
 * Spring 2019
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class RadixSortASCII {
	/**
	 * Returns a string representation of a RadixSort object.
	 * The string is basically showing the current group of values
	 * radix sort is applied on.
	 * @param rs a RadixSort object
	 * @return a string showing the group of values rs is sorting
	 */
	public static String toString(RadixSort rs){
		SmartArray<Sortable> values = rs.getValues();
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(values.size()+" values: ");
		
		for (int i=0;i<values.size();i++){
			sBuilder.append(values.get(i).digits()+" ");
			if ((i+1)%10 == 0)
				sBuilder.append("\n");
		}		
		return sBuilder.toString();
	}

	/**
	 * Returns a string representation of one bucket in radix sort.
	 * The string is basically showing the current contents of the bucket.
	 * @param rs a RadixSort object
	 * @param bucketNum integer bucket number that specifies which bucket to represent
	 * @return a string showing contents of bucket bucketNum in rs
	 */	
	public static String bucketToString(RadixSort rs, int bucketNum){
		SmartArray<Sortable> bucket = rs.getBucket(bucketNum);
		
		if (bucket == null)
			return "";
		
		StringBuilder sBuilder = new StringBuilder();
		for (int i=0; i<bucket.size(); i++){
			sBuilder.append(bucket.get(i).digits());
			if (i<bucket.size()-1)
				sBuilder.append(" ");
		}
		return sBuilder.toString();
	}
	
	/**
	 * Print all non-empty buckets of a RadixSort object.
	 * @param rs a RadixSort object
	 */
	public static void showBuckets(RadixSort rs, boolean showAll){
		for (int i=0;i<rs.getNumBuckets();i++){
			String oneBucket = bucketToString(rs, i);
			if (showAll || !oneBucket.equals(""))
				System.out.println(i+": ["+bucketToString(rs, i)+"]");
		}
	}
	
	/**
	 * Interact with the user and returns when user presses enter/return
	 */
	public static void enterToContinue() {
		System.out.print("Press enter to continue ...");
		Scanner s = new Scanner(System.in);
		s.nextLine();
	}

	/**
	 * Read the values to sort from a file or standard input and 
	 * use a Radix Sort to sort the values.  The details of each 
	 * round of radix sort is displayed until the values are sorted.
	 * args can be used to specify a file to read in values to sort,
	 * If no arg is provided, use standard input to read in values.
	 * 
	 * @param args flag "-all" to display all buckets, or none to display
	 *  only non-empty buckets; followed by file to read, or none for 
	 *	default standard in.
	 */		
	public static void main(String[] args) {
		RadixSort rs = new RadixSort();
		Scanner scanner;
		boolean showAll = false;

		try{
			if(args.length > 2 || (args.length == 2 && !args[0].equals("-all"))){
				System.out.println("Usage: java RadixSortASCII [-all] filename");
				System.exit(0);
			}
			else if (args.length == 0 || (args.length == 1 && args[0].equals("-all"))) {
				showAll = (args.length == 1) ;
				
				System.out.println("Use keyboard to input.\n"+
								   "Line 1: Specify the type of values to sort.\n"+
								   "\tUse lowercase 'a' for SortableString.\n"+
								   "\tUse numeric base for SortableNumber\n\t\te.g. 2 (for base 2).\n"+
								   "Line 2: Type in values to sort (space-separated).\n\t\te.g. 101 111 100\n"+
								   "Line 3: End input with Ctrl-D(mac) or\n\tCtrl-Z(windows) and hit enter.");
				scanner = new Scanner(System.in);
				rs.initValuesFromScanner(scanner);
			}
			else{
				showAll = (args.length == 2);
				scanner = new Scanner(new File(args[args.length-1]));
				rs.initValuesFromScanner(scanner);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		System.out.println("\n----------------------------");
		System.out.println(toString(rs));
		int removed = rs.sanitizeValues();
		int padded = rs.setSameLength();
		System.out.println("Values removed: "+removed);
		System.out.println("Values padded: "+padded);
		System.out.println(toString(rs));
		enterToContinue();
		System.out.println("----------------------------");
		
		while(!rs.isSorted()){
			System.out.println("round "+(rs.getRound())+":");
			rs.oneRound();
			showBuckets(rs, showAll);
			System.out.println("\nValues after round:");
			System.out.println(toString(rs));
			
			enterToContinue();
			System.out.println("----------------------------");
		}
		
		System.out.println("\nSorted!");
		System.out.println(toString(rs));
		System.out.println("----------------------------");
	}
}

