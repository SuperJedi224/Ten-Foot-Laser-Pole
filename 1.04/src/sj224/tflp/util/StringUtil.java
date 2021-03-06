package sj224.tflp.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil{
	private StringUtil(){}
	/**
	 * Creates an iterator that iterates over a string's characters, in order
	 * @param s The string
	 * @return The iterator
	 */
	public static Iterator<Character> charIterator(final String s){
		return new Iterator<Character>(){
			int i=0;
			@Override
			public boolean hasNext() {
				return i<s.length();
			}

			@Override
			public Character next() {
				try{
					return s.charAt(i++);
				}catch(Exception e){
					throw new NoSuchElementException();
				}
			}
		};
	}
	public static String[]groups(Matcher m){
		String[]out=new String[m.groupCount()+1];
		for(int i=0;i<out.length;i++){
			out[i]=m.group(i);
		}
		return out;
	}
	/**
	 * Replaces substrings of a string which match a given regex with new substrings generated by a given function.
	 * The array passed to the function will have the matched substring as element 0, and any captured groups as additional elements.
	 * @param source The input string
	 * @param regex The regex to be replaced
	 * @param sub The function with which the replacements will be generated
	 * @return The result string
	 */
	public static String replace(String source,String regex,Function<String[],String>sub){
		Matcher m=Pattern.compile(regex).matcher(source);
		StringBuilder out=new StringBuilder();
		int a=0;
		while(m.find()){
			out.append(source.substring(a,m.start()));
			out.append(sub.apply(groups(m)));
			a=m.end();
		}
		if(a<source.length()){
			out.append(source.substring(a,source.length()));
		}
		return out.toString();
	}
}