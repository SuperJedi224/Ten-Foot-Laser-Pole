package sj224.tflp.util;

/**
 * A utility class for associating a pair of objects of arbitrary types.
 *
 * @author Johnathan Waugh
 *
 * @param <T> The type of the first value
 * @param <U> The type of the second value
 */

public class Pair<T, U> {
	public Pair(T a,U b){
		one=a;
		two=b;
	}
	/**
	 * The first object (which will be of type T)
	 */
	public final T one;
	/**
	 * The second object (which will be of type U)
	 */
	public final U two;
	public int hashCode(){
		int a=one==null?0:one.hashCode();
		int b=two==null?0:two.hashCode();
		return a^(b*b);
	}
}
