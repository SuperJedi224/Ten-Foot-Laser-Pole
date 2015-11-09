package sj224.tflp.dice;

import java.util.Random;

/**
 * A subclass of <b>Die</b> for simulating FUDGE dice, numbered with +1, -1, and 0.
 */
public class FudgeDie extends Die{
	private final double p0;
	/**Constructs a FUDGE die with the specified probability of rolling a 0, +1 and -1 occur with equal probability
	 * @param probability the probability of rolling a 0
	 */
	public FudgeDie(double probability){
		super(-1);
		p0=probability;
	}
	/**Constructs a FUDGE die for which all three possible outcomes occur with equal probability*/
	public FudgeDie(){
		this(1/3.0);
	}
	/**
	 * Simulates one roll, which may be either ±1, or 0
	 * @return The value rolled
	 * @see sj224.tflp.dice.Die#roll(java.util.Random)
	 */
	public int roll(Random r){
		int a=0;
		if(r.nextDouble()>p0){
			if(r.nextDouble()<0.5){
				a=-1;
			}else{
				a=1;
			}
		}
		rollRecord=(a==0?"0":(a==-1?"-":"+"));
		return a;
	}
	/**
	 * @see sj224.tflp.dice.Die#toString()
	 */
	public String toString(){
		return "dF";
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		return (int)(Integer.MAX_VALUE*(p0+0.5)/2);
	}
}