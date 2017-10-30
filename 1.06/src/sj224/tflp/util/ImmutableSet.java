package sj224.tflp.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ImmutableSet<E> implements Set<E> {
	private final Set<E> set=new HashSet<>();
	private ImmutableSet(){}
	@SafeVarargs
	public static <E> ImmutableSet<E> of(E... values){
		ImmutableSet<E> s=new ImmutableSet<>();
		for(E val:values)s.set.add(val);
		return s;
	}
	public static <E,F extends E> ImmutableSet<E> of(Set<F>values){
		ImmutableSet<E> s=new ImmutableSet<>();
		for(E val:values)s.set.add(val);
		return s;
	}
	public boolean equals(Object e){
		return set.equals(e);
	}
	@Override
	public boolean add(E arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public boolean contains(Object arg0) {
		return set.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return set.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}
	
	@Override
	public Iterator<E> iterator() {
		return set.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
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
	public int size() {
		return set.size();
	}

	@Override
	public Object[] toArray() {
		return set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return set.toArray(arg0);
	}
}
