package sj224.tflp.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class StringUtil{
	private StringUtil(){}
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
}