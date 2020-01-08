/**
 * Represents a string of CAPITAL letters only for RadixSort. 
 * <p>
 * @author CS310 GMU
 * <p>
 * CS310
 * Spring 2019
 */
public class SortableString implements Sortable{
	/**
	 *  There are 27 allowed characters (26 capital letters and spaces)
	 */
	public static final int BASE = 27;
	
	/**
	 *  The value to sort as a string of letters
	 */
	private String digits;
	
	/**
	 *  Valid characters are capital letters only,
	 *  so A is the start
	 */
	private char start='A';
	
	/**
	 *  Space character will be used for padding.
	 */
	private String pad = " ";
	
	/**
	 *  The padded copy of the string
	 */
	private String paddedDigits;

	/**
	 * Constructs a new SortableString with the specified digits
	 * @param digits String used as the collection of characters
	 * of this object. It is assumed that the incoming string is 
	 * not empty and has no leading/trailing spaces.
	 */
	public SortableString(String digits){
		this.digits = digits;
		this.paddedDigits = digits;
	}
	
	/**
	 * Return the non-padded digits of the SortableString object.
	 * @return the non-padded digits of the SortableString object
	 */	
	public String digits(){
		return digits;
	}

	/**
	 * Return the padded digits of the SortableString object.
	 * If no padding has been performed, original digits should be returned.
	 * @return the padded digits of the SortableString object.
	 */	
	public String paddedDigits(){
		return paddedDigits;
	}
	
	/**
	 * Return the max possible numeric value of a single digit in decimal
	 * @return the max possible numeric value of a single digit in decimal
	 */	
	public int maxNum(){
		return BASE-1;  //26 capital letters + space for [0,26]
	}
	
	/**
	 * Convert and return the value at specified position as a decimal
	 * If padded, use the padded digits for conversion
	 * @param pos integer position; rightmost position is 0
	 * @return corresponding numeric value (in decimal) from position pos
	 */
	public int posToNum(int pos){
		int length = paddedDigits.length();
		int returnV;
		if (0<=pos && pos<length) {
			//valid range
			if (paddedDigits.charAt(length-1-pos) == ' ')
				returnV = 0;  //space is the smallest, return 0
			else		
				returnV = paddedDigits.charAt(length-1-pos)-start+1;	
				// otherwise, capital letters would return a number in [1,26]
				// think: what will invalid digit return?		
		}
		else
			returnV = -1;	//invalid range: return a negative value
		return returnV;
	}
	
	/**
	 * Performs padding to ensure the length of padded string is at least minLength.
	 * @param minLength integer as the minimum number of digits after padding
	 */
	public void padDigits(int minLength){
		// right pad with spaces if needed 
		// only update paddedDigits while digits kept unchanged

		// start over with a fresh copy of digits
		this.paddedDigits = this.digits;
		
		// pad if needed
		for (int i=0;i<minLength-digits.length();i++)
			this.paddedDigits = this.paddedDigits + pad;
	}
}	
	