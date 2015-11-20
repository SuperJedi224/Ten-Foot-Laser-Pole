package sj224.tflp.dice;

import java.util.Random;

/**
 * This class represents a single arbitrary die.
 * This class itself represents a standard die of from two to arbitrarily many sides, but can be subclassed to represent special kinds of dice or die rolls.
 * @author Johnathan Waugh
 *
 */
public class Die {
	private final int sides;
	protected String rollRecord;
	/**
	 * Constructs a <b>Die</b> object with a set number of sides.
	 * @param s The number of sides
	 */
	public Die(int s){
		sides=s;
	}
	/**
	 * Simulates 1 roll of the die.
	 * @param r the <b>Random</b> object from which any requisite random numbers are to be taken
	 * @return The result rolled
	 * @see DieRoller#roll(Die...)
	 */
	public int roll(Random r){
		int n=1+r.nextInt(sides);
		rollRecord=""+n;
		return n;
	}
	/**
	 * Returns a string representation of the die being used. It is suggested that you use algebraic dice notation. 
	 */
	public String toString(){
		return "d"+sides;
	}
	/**
	 * @return A string containing a record of the last roll
	 */
	public final String result(){
		return toString()+":"+rollRecord;
	}
	/**
	 * The number of sides on the die.
	 * @return The number of sides
	 */
	public final int sides(){
		return sides;
	}
	public boolean equals(Object o){
		return o instanceof Die && o.toString().equals(this.toString());
	}
	/**
	 * Construct a <b>Die</b> object that simulates the current die with a modifier added
	 * @param n The modifier to be used
	 * @return A <b>Die</b> object that represents the current die with a constant modifier added.
	 */
	public Die plus(int n){
		Die d=this;
		return new Die(sides()){
			public int roll(Random r){
				int a=d.roll(r)+n;
				this.rollRecord=""+a;
				return a;
			}
			public String toString(){
				String a=d.toString();
				if(n==0)return a;
				if(n<0)return a+n;
				return a+"+"+n;
			}
		};
	}
	public int hashCode(){
		return sides;
	}
}