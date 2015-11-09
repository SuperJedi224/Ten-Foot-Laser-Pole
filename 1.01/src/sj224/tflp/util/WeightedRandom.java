package sj224.tflp.util;

import java.util.ArrayList;
import java.util.List;

public class WeightedRandom<T>{
	protected int totalweight;
	private static class WeightedRandomElement<T>{
		private final T item;
		int weight;
		int sweight;
		public WeightedRandomElement(T element,int weight){
			this.item=element;
			this.weight=weight;
		}
		@SuppressWarnings("rawtypes")
		public boolean equals(Object o){
			if(o instanceof WeightedRandomElement){
				return item.equals(((WeightedRandomElement)o).item);
			}
			return item.equals(o);
		}
	}
	private final List<WeightedRandomElement<T>> list=new ArrayList<>();
	/**
	 * Registers the given element if it has not yet been registered, then sets its weight to the given value.
	 * @param element The element to be assigned.
	 * @param weight The weight to be assigned to that element. Must be nonnegative. Giving 0 removes this element from the collection, if it is present.
	 * @throws java.lang.IllegalArgumentException if the given element is null, or the given weight is negative.
	 */
	public void setWeight(T element,int weight){
		if(element==null||weight<0)throw new IllegalArgumentException();
		int i=list.indexOf(element);
		if(i!=-1){
			list.get(i).weight=weight;
			if(weight==0){
				list.remove(i);
			}
		}else{
			if(weight!=0)list.add(new WeightedRandomElement<>(element,weight));
		}
		calculate();
	}
	protected void calculate(){
		int i=0;
		for(WeightedRandomElement<T> r:list){
			i+=r.weight;
			r.sweight=i;
		}
		totalweight=i;
	}
	protected WeightedRandomElement<T> nextWRE(Random r){
		int k=r.nextInt(totalweight);
		for(WeightedRandomElement<T>e:list){
			if(e.sweight>k)return e;
		}
		return null;
			
	}
	/**
	 * Select and return a random element. Elements of the highest weight are the most likely to be returned.
	 * @param r The <b>Random</b> object to be used in selecting the element.
	 * @return The selected element
	 */
	public T next(Random r){
		return nextWRE(r).item;
	}
	/**
	 * Select a random element, decrement its weight (removing it from the collection if its weight reaches 0), and return it. Elements of the highest weight are the most likely to be returned.
	 * @param r The <b>Random</b> object to be used in selecting the element.
	 * @return The selected element
	 */
	public T nextAndRemove(Random r){
		WeightedRandomElement<T>e=nextWRE(r);
		e.weight--;
		if(e.weight==0)list.remove(e);
		calculate();
		return e.item;
	}
	/**
	 * @return The combined weight of all registered elements
	 */
	public int totalWeight(){
		calculate();
		return totalweight;
	}
	/**
	 * @return
	 */
	public boolean isEmpty(){
		calculate();
		return totalweight==0;
	}
}
