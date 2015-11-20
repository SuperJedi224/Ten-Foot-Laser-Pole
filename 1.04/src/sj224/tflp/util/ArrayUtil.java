package sj224.tflp.util;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Johnathan Waugh
 *
 */
public class ArrayUtil {
	private ArrayUtil() {}
	/**
	 * Merges one or more arrays of booleans into a single array
	 * @param arrays The arrays to merge
	 * @return A single array containing all the elements of the original arrays
	 */
	public static boolean[] merge(boolean[]...arrays){
		int l=0;
		for(boolean[] c:arrays)l+=c.length;
		boolean[]d=new boolean[l];
		l=0;
		for(boolean[]c:arrays)for(boolean f:c){d[l]=f;l++;}
		return d;
	}
	/**
	 * Merges one or more arrays of bytes into a single array
	 * @param arrays The arrays to merge
	 * @return A single array containing all the elements of the original arrays
	 */
	public static byte[] merge(byte[]...arrays){
		int l=0;
		for(byte[] c:arrays)l+=c.length;
		byte[]d=new byte[l];
		l=0;
		for(byte[]c:arrays)for(byte f:c){d[l]=f;l++;}
		return d;
	}
	/**
	 * Merges one or more arrays of characters into a single array
	 * @param arrays The arrays to merge
	 * @return A single array containing all the elements of the original arrays
	 */
	public static char[] merge(char[]...arrays){
		int l=0;
		for(char[] c:arrays)l+=c.length;
		char[]d=new char[l];
		l=0;
		for(char[]c:arrays)for(char f:c){d[l]=f;l++;}
		return d;
	}
	/**
	 * Merges one or more arrays of integers into a single array
	 * @param arrays The arrays to merge
	 * @return A single array containing all the elements of the original arrays
	 */
	public static int[] merge(int[]...arrays){
		int l=0;
		for(int[] c:arrays)l+=c.length;
		int[]d=new int[l];
		l=0;
		for(int[]c:arrays)for(int f:c){d[l]=f;l++;}
		return d;
	}
	/**
	 * Merges one or more arrays of longs into a single array
	 * @param arrays The arrays to merge
	 * @return A single array containing all the elements of the original arrays
	 */
	public static long[] merge(long[]...arrays){
		int l=0;
		for(long[] c:arrays)l+=c.length;
		long[]d=new long[l];
		l=0;
		for(long[]c:arrays)for(long f:c){d[l]=f;l++;}
		return d;
	}
	/**
	 * Merges one or more arrays of shorts into a single array
	 * @param arrays The arrays to merge
	 * @return A single array containing all the elements of the original arrays
	 */
	public static short[] merge(short[]...arrays){
		int l=0;
		for(short[] c:arrays)l+=c.length;
		short[]d=new short[l];
		l=0;
		for(short[]c:arrays)for(short f:c){d[l]=f;l++;}
		return d;
	}
	/**
	 * Merges one or more arrays of any one reference type into a single array
	 * @param arrays The arrays to merge
	 * @param <T> the type of object in the arrays
	 * @return A single array containing all the elements of the original arrays
	 */
	@SafeVarargs
	public static <T> T[] merge(T[]...arrays){
		int l=0;
		for(T[] c:arrays)l+=c.length;
		T[]d=Arrays.copyOf(arrays[0], l);
		l=0;
		for(T[]c:arrays)for(T f:c){d[l]=f;l++;}
		return d;
	}
	/**
	 * Constructs a deep string representation of an arbitrary array.
	 * @param array The array whose string representation is being constructed
	 * @return The string representation
	 * @see java.util.Arrays#deepToString(Object[])
	 * @throws IllegalArgumentException if the passed object is not actually an array
	 */
	public static String toString(Object array){
		if(array==null)return "<null>";
		int l=Array.getLength(array);
		if(l==0)return "{}";
		String s="{";
		Object o=Array.get(array, 0);
		try{s+=toString(o);}catch(Exception e){s+=o;}
		for(int i=1;i<l;i++){
			s+=",";
			Object p=Array.get(array, i);
			try{s+=toString(p);}catch(Exception e){s+=p;}
		}
		return s+"}";
	}
	/**
	 * Find the index of an item in an array, according to the Objects.equals() method.
	 * Slower than the binary search found in java.lang.Arrays, but does not require the array to be presorted.
	 * @param array The array in which to search
	 * @param item The item to search for
	 * @return The index at which the specified item or an item equal to it (by the conventions used by Objects.equals) first appears, if any; -1 otherwise.
	 */
	public static int indexOf(Object array,Object item){
		int l=Array.getLength(array);
		for(int i=1;i<l;i++){
			if(Objects.equals(item, Array.get(array,i)))return i;
		}
		return -1;
	}
	/**
	 * Find the index of an item in an array, according to reference equality.
	 * Slower than the binary search found in java.lang.Arrays, but does not require the array to be presorted.
	 * @param array The array in which to search
	 * @param item The item to search for
	 * @return The index at which the specified item exactly first appears, if any; -1 otherwise.
	 */
	public static int indexOfIdentity(Object array,Object item){
		int l=Array.getLength(array);
		for(int i=1;i<l;i++){
			if(Array.get(array,i)==item)return i;
		}
		return -1;
	}
	/**
	 * Find the index of an item in an array, according to hash equality.
	 * This uses the convention that the hash code of <b>null</b> is 0.
	 * Slower than the binary search found in java.lang.Arrays, but does not require the array to be presorted.
	 * @param array The array in which to search
	 * @param item The item to search for
	 * @return The index at which an object with the same hash code as the specified object first appears, if any; -1 otherwise.
	 */
	public static int indexOfHash(Object array,Object item){
		int l=Array.getLength(array);
		int h=item==null?0:item.hashCode();
		for(int i=1;i<l;i++){
			Object o=Array.get(array,i);
			int j=o==null?0:o.hashCode();
			if(j==h)return i;
		}
		return -1;
	}
	/**
	 * Pretty print a 2d array of integers to a specified print stream.
	 * @param array the array to be printed
	 * @param p the print stream to which to print it
	 */
	public static void print(int[][]array,PrintStream p){
		int l=0;
		for(int[] t:array){
			for(int i:t){
				int k=String.valueOf(i).length()+1;
				if(k>l)l=k;
			}
		}
		for(int[] t:array){
			p.print("{");
			for(int i:t){
				int k=String.valueOf(i).length();
				for(int j=k;j<l;j++)p.print(" ");p.print(i);
			}
			p.println("}");
		}
	}
	/**
	 * Pretty print a 2d array of booleans to a specified print stream.
	 * @param array the array to be printed
	 * @param p the print stream to which to print it
	 */
	public static void print(boolean[][]array,PrintStream p){
		for(boolean[] t:array){
			p.print("{");
			for(boolean i:t){
				int k=String.valueOf(i).length();
				for(int j=k;j<6;j++)p.print(" ");p.print(i);
			}
			p.println("}");
		}
	}
	/**
	 * Pretty print a 2d array of characters to a specified print stream.
	 * @param array the array to be printed
	 * @param p the print stream to which to print it
	 */
	public static void print(char[][]array,PrintStream p){
		for(char[] t:array){
			p.print("{");
			for(char i:t){
				p.print(i);
			}
			p.println("}");
		}
	}
	/**
	 * Maps an array of one type of object to an array of another type of object, using a specified function object to handle the mapping.
	 * If the given output array is not of the correct length, will create a clone of it to write the output to.
	 * @param in The input array
	 * @param outTo The output array
	 * @param map The Function object handling the mapping
	 * @param <T> The type of the objects in the input array
	 * @param <U> The type of the objects in the output array
	 * @return The output array
	 */
	public static <T,U> U[] map(T[]in,U[]outTo,Function<T,U>map){
		if(outTo.length!=in.length)outTo=Arrays.copyOf(outTo,in.length);
		for(int i=0;i<in.length;i++){
			outTo[i]=map.apply(in[i]);
		}
		return outTo;
	}
	/**
	 * Constructs an array representing a range of integers
	 * @param i The lower bound, inclusive
	 * @param j The upper bound, exclusive
	 * @throws IllegalArgumentException if i&gt;=j
	 * @return An array such that every integer [i,j) appears exactly once, in order
	 */
	public static int[] range(int i,int j){
		if(i>=j)throw new IllegalArgumentException();
		int[]k=new int[j-i];
		for(int l=0;l<k.length;l++){
			k[l]=i+l;
		}
		return k;
	}
	/**
	 * Returns range(0,j)
	 */
	public static int[]range(int j){
		return range(0,j);
	}
	/**
	 * Generates an array of a given length, using elements provided by a given Supplier function
	 * @param clazz The type of the objects in the generated array
	 * @param source A Supplier function from which the objects in the array will be sourced
	 * @param length The length of the generated array
	 * @return The generated array
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[]generate(Class<T>clazz,Supplier<T>source,int length){
		Object o=Array.newInstance(clazz,length);
		for(int i:range(0,length))Array.set(o,i,source.get());
		return (T[])o;
	}
	public static <T extends Enum<T>> void valueSort(T[]array){
		Class<T>c=null;
		for(T t:array){
			if(t==null)continue;
			c=t.getDeclaringClass();
			break;
		}
		try {
			T[]o=c.getEnumConstants();
			int[]a=new int[o.length+1];
			for(T t:array)a[t==null?0:t.ordinal()+1]++;
			int j=0;
			for(int x=0;x<a.length;x++)for(;a[x]>0;a[x]--)array[j++]=x==0?null:o[x-1];
		}catch (Exception e){e.printStackTrace();}
	}
}