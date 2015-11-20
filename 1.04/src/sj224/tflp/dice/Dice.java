package sj224.tflp.dice;

import java.util.Random;

/**
 * This class has additional static methods for manipulating dice.
 * @author Johnathan Waugh
 */
public class Dice {
	private Dice(){}
	/**
	 * Create a <b>Die</b> object for simulating the roll of several of the same kind of die
	 * @param d The kind of die to be rolled
	 * @param n The number of dice to be rolled
	 * @return A <b>Die</b> object to simulate rolling the specified set of dice
	 */
	public static Die rollOf(int n,Die d){
		return new Die(d.sides()){
			public int roll(Random r){
				rollRecord="";
				int a=0;
				for(int i=1;i<=n;i++){
					a+=d.roll(r);
					rollRecord+=d.rollRecord;
					if(i<n)rollRecord+=",";
				}
				rollRecord+=" ("+a+")";
				return a;
			}
			public String toString(){
				return n+d.toString();
			}
		};
	}
	/**
	 * A four-sided die 
	 */
	public static final Die D4=new Die(4);
	/**
	 * A six-sided die
	 */
	public static final Die D6=new Die(6);
	/**
	 * An eight-sided die
	 */
	public static final Die D8=new Die(8);
	/**
	 * A ten-sided die
	 */
	public static final Die D10=new Die(10);
	/**
	 * A twelve-sided die 
	 */
	public static final Die D12=new Die(12);
	/**
	 * A twenty-sided die
	 */
	public static final Die D20=new Die(20);
	/**
	 * A hundred-sided die
	 */
	public static final Die D100=new Die(100);
	/**
	 * A FUDGE die
	 */
	public static final Die DF=new FudgeDie();
}
