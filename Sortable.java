/**
 * Represents a value that can be used for RadixSort. 
 * The value is stored as a collection of digits.
 * <p>
 * @author CS310 GMU
 * <p>
 * CS310
 * Spring 2019
 */
public interface Sortable{

	/**
	 * Return the max possible numeric value of a single digit in decimal
	 * @return the max possible numeric value of a single digit in decimal
	 */
	public int maxNum();

	/**
	 * Convert and return the value at specified position as a decimal
	 * If padded, use the padded digits for conversion
	 * @param pos integer position; rightmost position is 0
	 * @return corresponding numeric value (in decimal) from position pos
	 */
	public int posToNum(int pos);

	/**
	 * Return the non-padded string representation of the value.
	 * @return the non-padded string representation value
	 */	
	public String digits();

	/**
	 * Return the padded string representation of the value.
	 * If no padding has been performed, original string should be returned.
	 * @return the padded string representation value
	 */		
	public String paddedDigits();
	
	/**
	 * Pads if necessary to ensure the number of digits of the value
	 * @param minLength integer minimum number of digits after padding
	 */
	public void padDigits(int minLength);
	
}