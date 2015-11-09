package sj224.tflp.dice;

import java.util.Random;

/**
 * A subclass of <b>Die</b> for simulating die rolls with the Exploding mechanic; that is, rolling the maximum value allows you to reroll and add.
 */
public class ExplodingDie extends Die{
	/**Construct an <b>ExplodingDie</b> object representing an exploding die with the specified number of sides
	 * @param s The number of sides
	 */
	public ExplodingDie(int s){
		super(s);
	}
	/**
	 * Simulates one set of rolls, in accordance with the Exploding Die mechanic
	 * @return The total value rolled
	 * @see sj224.tflp.dice.Die#roll(java.util.Random)
	 */
	public int roll(Random r){
		int a=0,tally=0;
		String s="";
		do{
			if(s.length()>0)s+="+";
			a=super.roll(r);
			s+=a;
			tally+=a;
		}while(a==sides());
		if(s.indexOf('+')!=-1)s+="="+tally;
		rollRecord=s;
		return tally;
	}
	/**
	 * @see sj224.tflp.dice.Die#toString()
	 */
	public String toString(){
		return super.toString()+"^";
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		return -sides();
	}
}