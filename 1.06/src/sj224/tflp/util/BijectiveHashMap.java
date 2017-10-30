package sj224.tflp.util;

import java.util.*;

/**
 * A bijective hash map implementation.
 * It is recommended that immutable values be used for both the keys and the values.
 * @author Johnathan Waugh
 * @param <K> The key type
 * @param <V> The value type
 */
public class BijectiveHashMap<K,V> implements BijectiveMap<K, V> {
	private class BijectiveMapValueCollection implements Collection<V>{

		@Override
		public boolean add(V e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean addAll(Collection<? extends V> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			BijectiveHashMap.this.clear();
		}

		@Override
		public boolean contains(Object o) {
			return values.containsKey(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			for(Object o:c){
				if(!values.containsKey(o))return false;
			}
			return true;
		}

		@Override
		public boolean isEmpty() {
			return BijectiveHashMap.this.isEmpty();
		}

		@Override
		public Iterator<V> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean remove(Object o) {
			return BijectiveHashMap.this.removeKey(values.get(o));
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			boolean b=false;
			for(Object o:c){
				b|=BijectiveHashMap.this.removeKey(values.get(o));
			}
			return b;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int size() {
			return BijectiveHashMap.this.size();
		}

		@Override
		public Object[] toArray() {
			return values.keySet().toArray();
		}

		@Override
		public <T> T[] toArray(T[] a) {
			return values.keySet().toArray(a);
		}
		
	}
	private class BijectiveMapKeySet implements Set<K>{

		@Override
		public boolean add(K arg0) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean addAll(Collection<? extends K> arg0) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			throw new UnsupportedOperationException();			
		}

		@Override
		public boolean contains(Object arg0) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean containsAll(Collection<?> arg0) {
			return BijectiveHashMap.this.keys.keySet().containsAll(arg0);
		}

		@Override
		public boolean isEmpty() {
			return BijectiveHashMap.this.isEmpty();
		}

		@Override
		public Iterator<K> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean remove(Object arg0) {
			return BijectiveHashMap.this.removeKey(arg0);
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean removeAll(Collection<?> arg0) {
			boolean b=false;
			for(Object o:arg0){
				b|=BijectiveHashMap.this.removeKey((K)o);
			}
			return b;
		}

		@Override
		public boolean retainAll(Collection<?> arg0) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int size() {
			return BijectiveHashMap.this.size();
		}

		@Override
		public Object[] toArray() {
			return BijectiveHashMap.this.keys.keySet().toArray();
		}

		@Override
		public <T> T[] toArray(T[] arg0) {
			return BijectiveHashMap.this.keys.keySet().toArray(arg0);
		}
		
	}
	private final HashMap<K,V>keys;
	private final HashMap<V,K>values;
	/**
	 * Validates that the map is bijective.
	 * @throws IllegalStateException if map is not bijective. This may be because the map was initialized with invalid values, or is storing mutable objects.
	 */
	public void validate(){
		for(V i:keys.values()){
			if(!keys.get(values.get(i)).equals(i)){
				throw new IllegalStateException();
			}
		}
		for(K i:values.values()){
			if(!values.get(keys.get(i)).equals(i)){
				throw new IllegalStateException();
			}
		}
	}
	public boolean removeKey(Object arg0) {
		if(keys.containsKey(arg0)){
			remove(arg0);
			return true;
		}
		return false;
	}
	private BijectiveHashMap(HashMap<K,V>keys,HashMap<V,K>values){
		
		this.values=values;
		this.keys=keys;
		try{validate();}catch(Exception e){
			throw new IllegalArgumentException();
		}
	}
	
	public BijectiveHashMap(){
		keys=new HashMap<>();
		values=new HashMap<>();
	}
	
	@Override
	public void clear() {
		keys.clear();
		values.clear();
		
	}

	@Override
	public boolean containsKey(Object key) {
		return keys.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return values.containsKey(value);
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(Object key) {
		validate();
		return keys.get(key);
	}
	public K getKey(Object value) {
		validate();
		return values.get(value);
	}

	@Override
	public boolean isEmpty() {
		return keys.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return new BijectiveMapKeySet();
	}

	@Override
	public V put(K key, V value) {
		if(values.containsKey(value)){
			keys.remove(values.get(value));
			values.remove(value);
		}
		values.put(value,key);
		V a=keys.put(key, value);
		validate();
		return a;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public V remove(Object key) {
		values.remove(keys.get(key));
		V a=keys.remove(key);
		validate();
		return a;
	}

	@Override
	public int size() {
		return keys.size();
	}

	@Override
	public Collection<V> values() {
		return new BijectiveMapValueCollection();
	}
	public BijectiveHashMap<V,K>inverse(){
		return new BijectiveHashMap<>(values,keys);
	}

}