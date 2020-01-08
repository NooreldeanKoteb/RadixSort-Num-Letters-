// TO DO: add your implementation and JavaDoc
/**
 * @author Nooreldean Koteb G01085380
 * @version 1.0
 */
public class SortableNumber implements Sortable{
	/**
	 * Given digits in string Format
	 */
	private String digits;
	/**
	 * Given base in int format
	 */
	private int base;
	/**
	 * Given digits + padding
	 */
	private String padded;
	// ADD PRIVATE MEMBERS HERE IF NEEDED!
	/**
	 * This constructor initializes the base and digits
	 * @param base - base given for the numbers
	 * @param digits - digits in string form given
	 * 
	 */
	public SortableNumber(int base, String digits){
		// constructor
		// set the base and digits of the number
		this.base = base;
		this.digits = digits;
		this.padded = digits;
		// Note: you can assume that 
		//		 - the incoming base is an integer from 2 to 16 inclusively
		// 		 - the incoming digits has no leading/trailing spaces
		//       - the incoming digits is not an empty string
		
	}
	/**
	 * This method will return the given digits in string form
	 * @return Returns the given digits in string form
	 * 
	 */
	public String digits(){
		// return the non-padded digits
		return this.digits;	
	}
	/**
	 * This method will return the padded digits in string form
	 * @return Returns the padded digits in string form
	 * 
	 */
	public String paddedDigits(){
		// return the padded digits
		return this.padded;
	}
	/**
	 * This method will return the max possible numeric value of a single digit
	 * @return Returns the max possible numeric value of a single digit	 * 
	 */
	public int maxNum(){
		// return the max possible numeric value of a single digit as a decimal
		
		return (this.base - 1);
	}
	/**
	 * This method will find and return the digit at a given position in a number
	 * @param pos - takes in an index number
	 * @return Returns the int of the digit at that pos in the number
	 * 
	 */
	public int posToNum(int pos){
		// return the value at location pos of the padded digits as a decimal
		// rightmost position (least significant digit position) is 0
		// return -1 if position is invalid or any exception occurs
		if (this.padded.length() < pos+1) {
			return -1;
		}else {
			int index = (padded.length() - (pos + 1));
			return Character.getNumericValue(this.padded.charAt(index)) ;
		}		
	}
	/**
	 * This method will pad numbers smaller than the minimum length
	 * @param minLength - takes the minimum length a number should be
	 * 
	 */
	public void padDigits(int minLength){
		// pad to ensure the length of padded string is
		// at least minLength
		while (this.padded.length() < minLength) {
			this.padded = '0' + this.padded;
		}
		
		
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