package hr.fer.zemris.java.cstr;

/**
 * Represents a custom implementation of a String. It has all functionalities just like the original implementation.
 * Substring operations and similar create new objects, but they do not instantiate a new array containing string
 * characters, its char array just points to the old string char array, with its own offset representing the
 * begin point of a string, and its own length variable representing a length of a string, starting from offset.
 * 
 * @author Mislav Gillinger
 */

public class CString {
	
	/** Character array containing characters of a string. */
	private char[] data;
	/** Index of starting point in char array of a string. */
	private int offset;
	/** Length of a string, starting from offset. */
	private int length;

	/**
	 * Creates a new instance of a CString. 
	 * @param data Character array containing characters of a string.
	 * @param offset Index of starting point in char array of a string.
	 * @param length Length of a string, starting from offset.
	 * @throws NullPointerException if null pointer is provided as an argument.
	 * @throws IndexOutOfBoundsException If provided offset is invalid.
	 */
	public CString(char[] data, int offset, int length) {
		if (data == null) {
			throw new NullPointerException("Data in constructor must not be null!");
		}
		
		if(offset + length > data.length){
			throw new IndexOutOfBoundsException("CString does not contain that much symbols!");
		}

		this.data = data;
		this.offset = offset;
		this.length = length;
	}

	/**
	 * Creates a new instance of a CString.	
	 * @param data Character array containing characters of a string.
	 * @throws NullPointerException if null pointer is provided as an argument.
	 */
	public CString(char[] data) {
		if (data == null) {
			throw new NullPointerException("Data in constructor must not be null!");
		}
		
		String assistant = String.valueOf(data);
		
		this.data = data;
		this.length = assistant.length();
		this.offset = 0;
	}

	/**
	 * Creates a new instance of a string. If original's internal character array is larger than needed, the
	 * new instance allocates its own character array of minimal required size and copies data;
	 * otherwise it reuses original's character array
	 * @param original String to be instantiated.
	 * @throws NullPointerException if null pointer is provided as an argument.
	 */
	public CString(CString original) {
		if (original == null) {
			throw new NullPointerException("Original string in constructor must not be null!");
		}

		char[] assistantCharArray = new char[original.length];
		if(original.offset != 0 || original.length != original.data.length){
			assistantCharArray = original.toCharArray();
		}
		else {
			assistantCharArray = original.data;
		}
		
		this.data = assistantCharArray;
		this.length = original.length;
		this.offset = 0;
	}
	
	/**
	 * Instantiates new CString object which has the same character data as given Java's String object.
	 * @param s New CString will be made out of this string's data.
	 * @return Instance of a new CString.
	 * @throws NullPointerException if null pointer is provided as an argument.
	 */
	public static CString fromString(String s){
		if(s == null){
			throw new NullPointerException("Argument must not be null!");
		}
 		
		return new CString(s.toCharArray());
	}
	
	/**
	 * Returns length of this CString.
	 * @return Length of this CString.
	 */
	public int length(){
		return length;
	}
	
	/**
	 * Returns a char which stands on a given position in inner char array.
	 * @param index Char at this position will be returned.
	 * @return Char at position index.
	 * @throws IndexOutOfBoundsException If index is invalid.
	 */
	public char charAt(int index){
		if(index < 0){
			throw new IndexOutOfBoundsException("Index must be a positive number!");
		}
		
		if(index > length){
			throw new IndexOutOfBoundsException("Argument index is invalid!");
		}
		
		return data[offset + index];
	}
	
	/**
	 * Returns a character array of length equals to length of this CString.
	 * @return A character array of length equals to length of this CString.
	 */
	public char[] toCharArray(){
		char[] charArray = new char[length];
		for(int i = offset, j = 0; i < offset + length; i++){
			charArray[j++] = data[i];
		}
		
		return charArray;
	}
	
	/**
	 * Converts this CString into a String and returns it.
	 * @return String made out of this CString.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = offset; i < offset + length; i++){
			sb.append(data[i]);
		}
		return sb.toString();
	}
	
	/**
	 * Returns index of first occurrence of the given char or -1.
	 * @param c Index of first occurrence of this char will be returned.
	 * @return Index of first occurrence of the given char or -1.
	 */
	public int indexOf(char c){
		for(int index = offset; index < offset + length; index++){
			if(data[index] == c){
				return index - offset;
			}
		}
		
		return -1;
	}
	
	/**
	 * Checks whether this string begins with the given one.
	 * @param s String which is a potential beginning of this string.
	 * @return True if the given string is a beginning of this string, false otherwise.
	 * @throws NullPointerException if null pointer is provided as an argument.
	 */
	public boolean startsWith(CString s){
		if(s == null){
			throw new NullPointerException("Argument must not be null!");
		}
		
		if(s.length > length){
			return false;
		}
		
		boolean same = true;
		for(int i = offset, j = 0; i < s.length + offset; i++){
			if(s.data[j++] != data[i]){
				same = false;
			}
		}
		
		return same;
	}
	
	/**
	 * Checks whether this string ends with the given one.
	 * @param s String which is a potential end of this string.
	 * @return True if the given string is an end of this string, false otherwise.
	 * @throws NullPointerException if null pointer is provided as an argument.
	 */
	public boolean endsWith(CString s){
		if(s == null){
			throw new NullPointerException("Argument must not be null!");
		}
		
		if(s.length > length){
			return false;
		}
		
		boolean same = true;
		for(int i = offset + length - 1, j = s.length - 1; j >= s.offset; i--){
			if(s.data[j--] != data[i]){
				same = false;
			}
		}
		
		return same;
	}
	
	/**
	 * Checks if this string contains the given starting at any position.
	 * @param s String which is a potential substring of this string.
	 * @return True if this string contains the given one, false otherwise.
	 * @throws NullPointerException if null pointer is provided as an argument.
	 */
	public boolean contains(CString s){
		if(s == null){
			throw new NullPointerException("Argument must not be null!");
		}
	
		if(s.length == 0){
			return false;
		}
		
		for(int i = offset, j = 0; i < offset + length; i++){
			if(data[i] == s.data[j]){
				if(j == s.length-1){
					return true;
				}
				j++;
				continue;
			}
			j = 0;
		}
		return false;
	}
	
	/**
	 * Returns new CString which represents a part of original string.
	 * Position endIndex does not belong to the substring. 
	 * Complexity is O(1).
	 * @param startIndex Start point of a new CString. Must not be negative.
 	 * @param endIndex End point of a new CString. Must not be less than startIndex.
	 * @return New CString which was a part of this string.
	 * @throws IndexOutOfBoundsException If index is invalid.
	 */
	public CString substring(int startIndex, int endIndex){
		if(startIndex < 0 || endIndex < startIndex){
			throw new IndexOutOfBoundsException("Invalid start or end index!");
		}
		
		return new CString(data, offset + startIndex, offset + endIndex - (offset + startIndex));
	}
	
	/**
	 * Returns new CString which represents starting part of original string and is of length n.
	 * @param n Length of a new string.
	 * @return New CString which represents starting part of original string and is of length n.
	 * @throws IndexOutOfBoundsException If index is invalid.
	 */
	public CString left(int n){
		if(n < 0) {
			throw new IndexOutOfBoundsException("Argument must not be negative!");
		}
		
		if(n > length){
			throw new IndexOutOfBoundsException("Original CString does not contain that much symbols!");
		}
		
		return new CString(data, offset, n);
	}
	
	/**
	 * Returns new CString which represents ending part of original string and is of length n.
	 * @param n Length of a new string.
	 * @return New CString which represents ending part of original string and is of length n.
	 * @throws IndexOutOfBoundsException If index is invalid.
	 */
	public CString right(int n){
		if(n < 0) {
			throw new IndexOutOfBoundsException("Argument must not be negative!");
		}
		
		if(n > length){
			throw new IndexOutOfBoundsException("Original CString does not contain that much symbols!");
		}
		
		return new CString(data, offset + length - n, n);
	}
	
	/**
	 * Creates a new CString which is concatenation of current and given string.
	 * @param s String to be added on the end of this one.
	 * @return A new CString which is concatenation of current and given string.
	 * @throws NullPointerException if null pointer is provided as an argument.
	 */
	public CString add(CString s){
		if(s == null){
			throw new NullPointerException("Argument must not be null!");
		}
		
		if(offset + length + s.length > data.length){
			char[] resizedArray = new char[data.length * 2];
			for(int i = 0; i < data.length; i++){
				resizedArray[i] = data[i];
			}
			data = resizedArray;
		}
		
		int j = s.offset;
		for(int i = offset + length; i < offset + length + s.length; i++){
			data[i] = s.data[j++];
		}
		length += s.length;
		
		return new CString(data, offset, length);
	}
	
	/**
	 * Creates a new CString in which each occurrence of old character is replaced with new character.
	 * @param oldChar 
	 * @param newChar
	 * @return A new CString in which each occurrence of old character is replaced with new character.
	 */
	public CString replaceAll(char oldChar, char newChar){
		char[] dataCopy = new char[data.length];
		
		for(int i = 0; i < data.length; i++){
			dataCopy[i] = data[i];
		}
		
		for(int i = offset; i < offset + length; i++){
			if(dataCopy[i] == oldChar){
				dataCopy[i] = newChar;
			}
		}
		
		return new CString(dataCopy, offset, length);
	}
	
	/**
	 * Creates a new CString in which each occurrence of old substring is replaced with the new substring.
	 * @param oldStr
	 * @param newStr
	 * @return A new CString in which each occurrence of old substring is replaced with the new substring.
	 * @throws NullPointerException if null pointer is provided as an argument.
	 */
	public CString replaceAll(CString oldStr, CString newStr) {
		if (oldStr == null || newStr == null) {
			throw new NullPointerException("Method replaceAll doesn't expects null as argument");
		}
		
		boolean changed = false;
		char[] dataCopy = this.toCharArray();
		
		for (int i = 0; i <= dataCopy.length - oldStr.length; i++) {
			for (int j = 0; j < oldStr.length; j++) {
				if (dataCopy[i + j] != oldStr.data[oldStr.offset + j]) {
					break;
				}
				if (j == oldStr.length - 1) {
					dataCopy = insert(dataCopy, newStr.toCharArray(), oldStr.length, i);
					i += newStr.length - 1;
					changed = true;
				}
			}
		}
		if (changed) {
			return new CString(dataCopy);
		} else
			return this;
	}

	/**
	 * Returns an array with the given newString inserted on a position of an oldString.
	 * @param array Array containing an old string.
	 * @param newStr 
	 * @param oldStrLength
	 * @param position Position where the new string will be inserted.
	 * @return New array where the newString is inserted on a given position.
	 */
	private static char[] insert(char[] array, char[] newStr, int oldStrLength, int position) {
		int newStrLength = newStr.length;
		char[] newArray = new char[array.length + newStrLength - oldStrLength];
		for (int i = 0; i < position; i++) {
			newArray[i] = array[i];
		}
		for (int i = position; i < position + newStrLength; i++) {
			newArray[i] = newStr[i - position];
		}
		for (int i = position + newStrLength; i < newArray.length; i++) {
			newArray[i] = array[i - newStrLength + oldStrLength];
		}
		return newArray;

	}
}
