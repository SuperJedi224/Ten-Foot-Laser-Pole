package sj224.tflp.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetUtil {
	private SetUtil(){};
	public static <E> Set<E> empty(){
		return new EmptySet<>();
	}
	private static class EmptySet<E> implements Set<E>{

		@Override
		public boolean add(E e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public boolean contains(Object o) {
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return c.isEmpty();
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public Iterator<E> iterator() {
			return new Iterator<E>(){

				@Override
				public boolean hasNext() {
					return false;
				}

				@Override
				public E next() {
					throw new IllegalStateException();
				}
			};
		}

		@Override
		public boolean remove(Object o) {
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			return false;
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public Object[] toArray() {
			return new Object[]{};
		}

		@Override
		public <T> T[] toArray(T[] a) {
			return Arrays.copyOf(a, 0);
		}

	}
	public static <E> Set<E> universal(){
		return new UniversalSet<>();
	}
	private static class UniversalSet<E> implements Set<E>{

		@Override
		public boolean add(E e) {
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			return false;
		}

		@Override
		public void clear() {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public boolean contains(Object o) {
			return true;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return true;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public Iterator<E> iterator() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			return false;
		}

		@Override
		public int size() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object[] toArray() {
			throw new UnsupportedOperationException();
		}

		@Override
		public <T> T[] toArray(T[] a) {
			throw new UnsupportedOperationException();
		}
	}
	public static <E> Set<E> complement(Set<E>in){
		return new ComplementSet<E>(in);
	}
	static class ComplementSet<E> implements Set<E>{
		private Set<E>back;
		ComplementSet(Set<E>set){
			back=set;
		}

		@Override
		public boolean add(E e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public boolean contains(Object o) {
			return !back.contains(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			for(Object o:c){
				if(back.contains(o))return false;
			}
			return true;
		}

		@Override
		public boolean isEmpty() {
			return back instanceof UniversalSet;
		}

		@Override
		public Iterator<E> iterator() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int size() {
			if(isEmpty())return 0;
			throw new UnsupportedOperationException();
		}

		@Override
		public Object[] toArray() {
			throw new UnsupportedOperationException();
		}

		@Override
		public <T> T[] toArray(T[] a) {
			throw new UnsupportedOperationException();
		}
		
	}
}