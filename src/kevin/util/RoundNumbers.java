/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.util
 * RoundNumbers.java
 */
package kevin.util;

public class RoundNumbers {

	public static double round(double value, int precision) {
		
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	    
	}
	
}
