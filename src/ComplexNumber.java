package hr.fer.zemris.java.tecaj.hw2;

import java.text.DecimalFormat;

/**
 * Class which supports working with complex numbers.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class ComplexNumber {

	/** Real part of a complex number. */
	double real;
	/** Imaginary part of a complex number. */
	double imaginary;
	
	/**
	 * Constructor which creates a new complex number.
	 * @param real Real part of a complex number.
	 * @param imaginary Imaginary part of a complex number.
	 */
	public ComplexNumber(double real, double imaginary){
		this.real = real;
		this.imaginary = imaginary;
	}
	
	/**
	 * Creates a new complex number which only has real part.
	 * @param real Number which represents real part of a complex number.
	 * @return Newly made complex number which only has real part.
	 */
	public static ComplexNumber fromReal(double real){
		return new ComplexNumber(real, 0);
	}

	/**
	 * Creates a new complex number which only has imaginary part.
	 * @param imaginary Number which represents imaginary part of a complex number.
	 * @return Newly made complex number which only has imaginary part.
	 */
	public static ComplexNumber fromImaginary(double imaginary){
		return new ComplexNumber(0, imaginary);
	}
	
	/**
	 * Creates a new complex number in form a+bi 
	 * from the given magnitude and angle.
	 * @param magnitude Magnitude of a complex number.
	 * @param angle Angle of a complex number.
	 * @return Newly made complex number in form a+bi. 
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle){
		return new ComplexNumber(magnitude * Math.cos(angle),
				magnitude * Math.sin(angle));
	}
	
	/**
	 * Creates a new complex number by parsing the given String.
	 * @param s String to be parsed, which represents a complex number.
	 * @return Newly made complex number.
	 */
	public static ComplexNumber parse(String s){
		if(s.equals("i")){
			return new ComplexNumber(0, 1);
		}
		else if(!s.contains("i")){
			return new ComplexNumber(Double.parseDouble(s), 0);
		}
		else if (!s.contains("+") && !s.contains("-")){
			return new ComplexNumber(0, Double.parseDouble(s.substring(0, s.length()-1)));
		}
		else if(s.contains("+")){
			if(s.charAt(s.lastIndexOf('+')) == '+'){
				if(s.substring(0, s.lastIndexOf('+')).equals("")){
					return new ComplexNumber(0, Double.parseDouble(s.substring(s.lastIndexOf('+'), s.length()-1)));
				}
				else{
					return new ComplexNumber(
							Double.parseDouble(s.substring(0, s.lastIndexOf('+')))
							, Double.parseDouble(s.substring(s.lastIndexOf('+'), s.length()-1)));
				}
			}
		}
		else{
			if(s.substring(0, s.lastIndexOf('-')).equals("")){
				return new ComplexNumber(0, Double.parseDouble(s.substring(s.lastIndexOf('-'), s.length()-1)));
			}
			else{
				return new ComplexNumber(
						Double.parseDouble(s.substring(0, s.lastIndexOf('-')))
						, Double.parseDouble(s.substring(s.lastIndexOf('-'), s.length()-1)));
			}
		}
		return new ComplexNumber(0,0);
	}
	
	/**
	 * Returns a real part of a complex number.
	 * @return A real part of a complex number.
	 */
	public double getReal(){
		return real;
	}
	
	/**
	 * Returns an imaginary part of a complex number.
	 * @return An imaginary part of a complex number.
	 */
	public double getImaginary(){
		return imaginary;
	}
	
	/**
	 * Returns a magnitude of a complex number.
	 * @return A magnitude of a complex number.
	 */
	public double getMagnitude(){
		return Math.sqrt(real*real + imaginary*imaginary);
	}
	
	/**
	 * Returns an angle of a complex number.
	 * @return An angle of a complex number.
	 */
	public double getAngle(){
		return Math.atan2(imaginary, real);
	}
	
	/**
	 * Sums the given complex number with this complex number.
	 * @param c Complex number to be added to this complex number.
	 * @return Sum of the two complex numbers in form a+bi.
	 */
	public ComplexNumber add(ComplexNumber c){
		return new ComplexNumber(this.real + c.real, this.imaginary + c.imaginary);
	}
	
	/**
	 * Subtracts the given complex number from this complex number.
	 * @param c Complex number to be subtracted from this complex number. 
	 * @return Difference between this complex number and the given one.
	 */
	public ComplexNumber sub(ComplexNumber c){
		return new ComplexNumber(this.real - c.real, this.imaginary - c.imaginary);
	}
	
	/**
	 * Multiplies the given complex number with this one.
	 * @param c Complex number to be multiplied with this complex number.
	 * @return Product of this complex number and the given one.
	 */
	public ComplexNumber mul(ComplexNumber c){
		return new ComplexNumber(this.real*c.real - this.imaginary*c.imaginary,
				this.real*c.imaginary + this.imaginary*c.real);
	}
	
	/**
	 * Divides this complex number with the given one.
	 * @param c Complex number by which this one will be divided.
	 * @return Quotient of the division.
	 */
	public ComplexNumber div(ComplexNumber c){
		double divisor = c.real*c.real + c.imaginary*c.imaginary;
		return new ComplexNumber(
				(this.real*c.real + this.imaginary*c.imaginary)/divisor,
				(this.imaginary*c.real - this.real*c.imaginary)/divisor);
	}
	
	/**
	 * Calculates the value of the complex number raised to the power of the 
	 * given argument. 
	 * @param n The exponent.
	 * @return Complex number raised to the power of the given argument.
	 * @throws IllegalArgumentException if the exponent is less than 0.
	 */
	public ComplexNumber power(int n){
		if (n < 0){
			throw new IllegalArgumentException("Exponent must be 0 or bigger!");
		}
		
		double magnitude = getMagnitude();
		double angle = getAngle();
		
		return new ComplexNumber(
				Math.pow(magnitude, n)*(Math.cos(n*angle)),
				Math.pow(magnitude, n)*(Math.sin(n*angle)));
	}
	
	/**
	 * Calculates all roots of a complex number.
	 * @param n Represents n-th root of a complex number.
	 * @return An array of complex numbers which are roots of the given one.
	 * @throws IllegalArgumentException if the argument is less than 1
	 */
	public ComplexNumber[] root(int n){
		if(n <= 0){
			throw new IllegalArgumentException("Argument must be bigger than 0!");
		}
		
		ComplexNumber[] ret = new ComplexNumber[n];
		double magnitude = getMagnitude();
		double angle = getAngle();
		
		for(int k = 0; k < n; k++){
			ret[k] = new ComplexNumber(
					Math.pow(magnitude, 1/(double)n)*(Math.cos((angle+2*k*Math.PI)/n)),
					Math.pow(magnitude, 1/(double)n)*(Math.sin((angle+2*k*Math.PI)/n)));
		}
		
		return ret;
	}
	
	/**
	 * Shapes the complex number in a String format.
	 * @return String which represents a complex number.
	 */
	public String toString(){
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		
		if(imaginary < 0){
			return decimalFormat.format(real) + "" + decimalFormat.format(imaginary) + "i";
		}
		else{
			return decimalFormat.format(real) + "+" + decimalFormat.format(imaginary) + "i";
		}
	}
}
