package sj224.tflp.dice;

import java.io.PrintStream;
import java.util.Random;

public final class DieRoller{
	private DieRoller(){}
	private static PrintStream out;
	private static String separator="~~~~~";
	private final static Random r=new Random((long)(Math.random()*System.currentTimeMillis())%10000);
	/**
	 * Deprecated in support of the enableLogging and disableLogging methods.
	 */
	@Deprecated public static void setOutputEnabled(boolean b){out=b?System.out:null;}
	public static void enableLogging(PrintStream p){
		out=p;
	}
	public static void disableLogging(){
		out=null;
	}
	public static void setLoggingSeparator(String sep){
		separator=sep;
	}
	/**
	 * Rolls one or more dice and returns the sum of the values rolled
	 * @param dice A tuple of one or more dice to be rolled
	 * @return The sum of the values rolled
	 */
	public static int roll(Die... dice){
		int a=0;
		for(Die d:dice){
			if(d==null){
				if(out!=null){out.println(separator);}
				continue ;
			}
			a+=d.roll(r);
			if(out!=null){out.println(d.result());}
		}
		if(out!=null){out.println(separator);}
		return a;
	}
}