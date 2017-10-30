package sj224.tflp.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ImmutableList<E> implements List<E> {
	private final List<E> list;
	@SafeVarargs
	public static <E> ImmutableList<E> of(E... values){
		return new ImmutableList<E>(values);
	}
	public ImmutableList(List<E>list){
		this.list=new LinkedList<>(list);
	}
	public ImmutableList(E[]array){
		this.list=Arrays.asList(array);
	}
	@Override
	public boolean add(E arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int arg0, E arg1) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public boolean contains(Object arg0) {
		return list.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return list.containsAll(arg0);
	}

	@Override
	public E get(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int indexOf(Object arg0) {
		return list.indexOf(arg0);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return new ImmutableIterator();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		return list.lastIndexOf(arg0);
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ImmutableIterator();
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		return new ImmutableIterator(arg0);
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int arg0, E arg1) {
		return null;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		return new ImmutableList<>(list.subList(arg0, arg1));
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return list.toArray(arg0);
	}
	class ImmutableIterator implements ListIterator<E>{
		ListIterator<E>i;
		
		ImmutableIterator(){
			i=list.listIterator();
		}
		
		ImmutableIterator(int j){
			i=list.listIterator(j);
		}
		
		@Override
		public void add(E arg0) {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public boolean hasNext() {
			return i.hasNext();
		}

		@Override
		public boolean hasPrevious() {
			return i.hasPrevious();
		}

		@Override
		public E next() {
			return i.next();
		}

		@Override
		public int nextIndex() {
			return i.nextIndex();
		}

		@Override
		public E previous() {
			return i.previous();
		}

		@Override
		public int previousIndex() {
			return i.previousIndex();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void set(E arg0) {
			throw new UnsupportedOperationException();
			
		}
		
	}

}
