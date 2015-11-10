package sj224.tflp.util;

import sj224.tflp.math.Complex;

/**Extends <b>java.util.Random</b>, adding an additional method for producing random booleans and random {@link sj224.tflp.math.Complex} objects*/
public class Random extends java.util.Random{

	private static final long serialVersionUID = 1L;
	public Random(){
		super();
	}
	public Random(long s){
		super(s);
	}
	/**
	 * Get a random integer on a specified interval
	 * @param min the lower bound
	 * @param max the upper bound
	 * @return A random integer between the lower bound, inclusive, and the upper bound, exclusive.
	 */
	public int nextInt(int min,int max){
		return nextInt(max-min)+min;
	}
	/**
	 * Get a random boolean, <b>true</b> with probability <i>p</i>
	 * @param p The probability of the returned value being <b>true</b>
	 * @return <b>true</b> with probability p, <b>false</b> otherwise
	 */
	public boolean nextBoolean(double p){
		return nextDouble()<=p;
	}
	/**
	 * @return A random complex number of magnitude 1
	 */
	public Complex nextComplex(){
		double a=nextDouble();
		return new Complex(Math.cos(2*Math.PI*a),Math.sin(2*Math.PI*a));
	}
}
