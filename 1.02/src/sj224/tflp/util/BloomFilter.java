package sj224.tflp.util;

import java.util.function.Predicate;
import java.util.Random;
import java.lang.Iterable;

/**
 * A counting Bloom Filter; a type of probabilistic dictionary backed by an array of integers
 * (The non-counting version would be backed by an array of booleans, and lack support for the removal operation.)
 * Assuming no removal operations are made, the probability of a false negative is 0, but the probability of a false positive will increase as you add more items.
 * @author SuperJedi224
 *
 * @param <V> The type of value being "stored."
 */
public class BloomFilter<V> implements Predicate<V>{
	/**
	 * Constructs a counting bloom filter with 64 cells in the backing array
	 */
	public BloomFilter(){
		this(64);
	}
	/**
	 * Constructs a counting bloom filter, allowing the user to specify the size of the backing array
	 * @param space the size of the backing array
	 */
	public BloomFilter(int space){
		data=new int[space];
		size=space;
		bpe=Math.max(3, Math.min(size/8,(int)Math.sqrt(size)));
		count=0;
	}
	/**
	 * Constructs a counting bloom filter with 64 cells in the backing array, then fills it with values from the specified iterable
	 * @param source An Iterable&lt;V&gt; from which to take the elements to preinitialize it with
	 */
	public BloomFilter(Iterable<V> source){
		this(64);
		for(V i:source)insert(i);
	}
	private final int bpe;
	private int count;
	private final int[] data;
	private final int size;
	/**
	 * Empties the bloom filter
	 */
	public void clear(){
		for(int i=0;i<size;i++)data[i]=0;
	}
	/**
	 * Checks if the bloom filter may contain the specified element. While probability of a false negative is 0 (assuming no removals have been made) the probability of a false positive is not.
	 * @param v The value being checked for.
	 * @return true if it determines that the value is present, false otherwise
	 */
	public boolean contains(V v){
		return hashOp(v,0);
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		long t=count;
		for(int i=0;i<size;i++){
			t+=(long)((data[i]%2)*Math.pow(2, i%64));
		}
		return new Random(t).nextInt();
	}
	/**
	 * Inserts an element into the bloom filter
	 * @param v The value being inserted
	 */
	public void insert(V v){
		hashOp(v,1);
		count++;
	}
	/**
	 * Checks if a value is present, according to the conventions of the {@link #contains(Object) contains} operator, removing it if present.
	 * @param v The value being removed
	 * @return true if the backing array was modified as a result, false otherwise
	 */
	public boolean remove(V v){
		if(contains(v)){hashOp(v,-1);count--;return true;}
		return false;
	}
	/**
	 * The number of elements currently in the Bloom Filter.
	 * @return the number of elements currently in the bloom filter
	 */
	public int size(){
		return count;
	}
	/**
	 * Mandated by the Predicate interface. An alias for the {@link #contains(Object) contains} method.
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	public boolean test(V v){
		return contains(v);
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return size+"-bit Bloom Filter containing "+count+" elements (hash code "+hashCode()+")";
	}
	private boolean hashOp(V v,int op){
		Random r=new Random(v.hashCode());
		boolean[] keys=new boolean[size];
		boolean b=true;
		for(int i=0;i<bpe;i++){
			int j=r.nextInt(size);
			if(!keys[j]&op!=0){keys[j]=true;data[j]+=op;}
			if(op==0){b=b&&data[j]>0;}
		}
		return b;
	}
}
