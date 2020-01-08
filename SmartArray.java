// TO DO: add your implementation and JavaDoc
/**
 * @author Nooreldean Koteb G01085380
 * @version 1.0
 * @param <T>
 */
public class SmartArray<T>{
	/**
	 * default initial capacity / minimum capacity
	 */
	private static final int DEFAULT_CAPACITY = 2;	//default initial capacity / minimum capacity
	/**
	 * underlying array
	 */
	private T[] data;	//underlying array
	/**
	 * Size of the array
	 */
	private int size;
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/**
	 * This is a Constructor, It creates the default generic array with 2 open spots and initializes size to 0
	 * @param data this is set to a default array
	 * @param size Holds size within array
	 */
	@SuppressWarnings("unchecked")
	public SmartArray(){
		//constructor
		//initial capacity of the array should be DEFAULT_CAPACITY
		this.data = (T[]) new Object[DEFAULT_CAPACITY];
		this.size = 0;
	}
	/**
	 * This is a Constructor, It creates the generic array with a gives set of open spots and initializes size to 0
	 * @param initialCapacity - This is an int value that will set the initial array size
	 * @throws IllegalArgumentException if input is not at least 2
	 */
	@SuppressWarnings("unchecked")
	public SmartArray(int initialCapacity){
		// constructor
		// set the initial capacity of the smart array as initialCapacity
		if (initialCapacity > 1) {
			this.data = (T[]) new Object[initialCapacity];
			this.size = 0;
		}else {
		// throw IllegalArgumentException if initialCapacity is smaller than 1
			throw new IllegalArgumentException("Input must be larger than 1!");
		}
	}
	/**
	 * This method returns the size
	 * @return Returns the number of elements in an array as an Int
	 */
	public int size() {	  
		//report number of elements in the smart array
		// O(1)
		//System.out.println("Elements in Array:" + size);
		return this.size;
	}
	/**
	 * This method returns the capacity
	 * @return Returns the Capacity of an array as an Int
	 * 
	 */
	public int capacity() { 
		//report max number of elements before the next expansion
		// O(1)
		//System.out.println("Array Space Available:" + (this.data.length));
		return (this.data.length);
	}
	/**
	 * This method adds an element to the list using a given index
	 * @param index - index in the array to place the value
	 * @param value - Generic element to be placed
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T value){
		// insert value at index, shift elements if needed  
		// double the capacity if no space is available
		// throw IndexOutOfBoundsException for invalid index
		// O(N) where N is the number of elements in the array
		if (index > (this.data.length-1)) {
		//throw new IndexOutOfBoundsException("Invalid Index!");
		}
		if (this.data.length == this.size) {
			ensureCapacity(this.data.length * 2);	
		}
		if (this.data[index] != null) {
		T[] temp = (T[]) new Object[this.data.length];	
			if (index == 0){
				System.arraycopy(this.data,0,temp,1,this.size);
				temp[index] = value;
				this.size++;
			}else {
				System.arraycopy(this.data,0,temp,0,(index));
				temp[index] = value;
				this.size++;
				System.arraycopy(this.data,index,temp,index+1,(this.data.length - (index+1)));
			}
			System.arraycopy(temp,0,this.data,0,this.data.length);
		}else {
			while ((index != 0) && (this.data[index-1] == null)) {
				index--;
			}
			this.data[index] = value;
			this.size++;
		}
		// Note: this method may be used to append items as
		// well as insert items
	}
	/**
	 * This method will pull an element from the array using a given index
	 * @param index - takes in an index number
	 * @return Returns a generic element
	 * @throws IndexOutOfBoundsException - If index is out of array bounds
	 */
	public T get(int index){
		// return the item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		if (index > (this.data.length-1)) {
			throw new IndexOutOfBoundsException("Invalid Index!");
		}else {
			return this.data[index];
		}
	}
	/**
	 * This method will replace an element in the array given an index
	 * @param index - takes in an index number
	 * @param value - generic element
	 * @return Returns the generic element replaced
	 * @throws IndexOutOfBoundsException - If index is out of array bounds
	 */
	public T replace(int index, T value){
		// change item at index to be value	
		// return old item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		if (index > (this.size() - 1)) {
			throw new IndexOutOfBoundsException("Invalid Index!");
		}else {
			T temp = this.data[index];
			this.data[index] = value;
			return temp;
		}
		// Note: you cannot add new items with this method
	}

	/**
	 * This method will pull an element from the array using a given index
	 * @param index - takes in an index number
	 * @return Returns a generic element
	 * @throws IndexOutOfBoundsException - If index is out of array bounds
	 */
	@SuppressWarnings("unchecked")
	public T delete(int index){
		// remove and return element at 
		// shift elements to remove any gap in the array
		// throw IndexOutOfBoundsException for invalid index
		T removed = null;
		if (index > (this.data.length-1)) {
			throw new IndexOutOfBoundsException("Invalid Index!");
		}else {
			removed = this.data[index];
			System.arraycopy(this.data,index+1,this.data,index,(this.data.length - (index+1)));
			this.data[this.capacity()-1] = null;
			this.size--;
		}
		// halve capacity if the number of elements falls below 1/4 of the capacity
		// capacity should NOT go below DEFAULT_CAPACITY
		if (((double)size/(double)this.data.length) < 0.25 && this.data.length > 2) {
			T[] temp = (T[]) new Object[(this.data.length/2)];
			System.arraycopy(this.data,0,temp,0,(this.data.length/2));
			this.data = (T[]) new Object[(this.data.length/2)];
			System.arraycopy(temp,0,this.data,0,(this.data.length/2));
		}
		// O(N) where N is the number of elements in the list
		return removed;
	}  
	/**
	 * This method double the array if the new capacity is larger than current capacity
	 * @param newCapacity - takes in the new capacity
	 * @return boolean value
	 * @throws IndexOutOfBoundsException - If index is out of array bounds
	 */
	@SuppressWarnings("unchecked")
	public boolean ensureCapacity(int newCapacity){
		// change the max number of items allowed before next expansion to newCapacity
		// capacity should not be changed if:
		//   - newCapacity is below DEFAULT_CAPACITY; or 
		//   - newCapacity is not large enough to accommodate current number of items
		if ((newCapacity > (this.data.length+1)) && (newCapacity > DEFAULT_CAPACITY )){
			T[] temp = (T[]) new Object[newCapacity]; 
			System.arraycopy(this.data,0,temp,0,this.data.length);
			data = (T[]) new Object[newCapacity];
			System.arraycopy(temp,0,this.data,0,this.data.length);
			return true;
		}
		// return true if newCapacity gets applied; false otherwise
		// O(N) where N is the number of elements in the array	
		return false;	
	}
    // --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------
	// Not required, update for your own testing
	/**
	 * @return "SmartArray" String
	 */
	public String toString(){
		// return string representation of DynamicArray
		// update if you want to include more information 
		return "SmartArray";
	}
	// Not required, update for your own testing
	/**
	 * This is the main method
	 * @param args
	 */
	public static void main (String args[]){
	}
}