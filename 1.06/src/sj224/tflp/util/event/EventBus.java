package sj224.tflp.util.event;

import java.io.PrintStream;
import java.util.*;
import java.util.function.Consumer;

import sj224.tflp.util.Pair;


/**
 * A class for posting and handling events.
 * @author Johnathan Waugh
 */
public final class EventBus{
	private PrintStream p=null;
	/**
	 * Enable logging on this EventBus
	 * @param logTo The PrintStream to which logging is to be done.
	 */
	public void logEvents(PrintStream logTo){
		if(logTo==null)throw new NullPointerException();
		if(p!=null)throw new IllegalStateException("This bus is already logging!");
		p=logTo;
	}
	private Map<Class<? extends Event>,List<Pair<EventPriority,Consumer<? extends Event>>>>listeners=new HashMap<>();
	/**
	 * Register a listener for the given class of event
	 * @param type The type of event to be checked for
	 * @param listener What should be done when the event occurs
	 * @param p The priority of the listener- high priority listeners are executed before low priority ones
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends Event> void addListener(Class<T>type,Consumer<T>listener,EventPriority p){
		List<Pair<EventPriority, Consumer<? extends Event>>>q=listeners.get(type);
		if(q==null)listeners.put(type,q=new ArrayList<Pair<EventPriority,Consumer<? extends Event>>>());
		q.add(new Pair(p,listener));
		
	}
	/**
	 * Register a listener for the given class of event, at the default priority level
	 * @param type The type of event to be checked for
	 * @param listener What should be done when the event occurs
	 */
	public <T extends Event> void addListener(Class<T>type,Consumer<T>listener){
		addListener(type,listener,EventPriority.NORMAL);
	}
	
	/**
	 * Submit an event to this bus, invoking any listeners assigned to that event class
	 * @param e The event to be submitted
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean post(Event e){
		if(p!=null)p.println(e);
		List<Pair<EventPriority,Consumer>>listeners=listeners(e.getClass());
		for(Pair<EventPriority,Consumer>pair:listeners){pair.two.accept(e);if(e.isCancelled())return true;}
		return false;
	}
	public void postAsync(Event e){
		new Thread(()->{post(e);}).start();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Pair<EventPriority,Consumer>>listeners(Class<? extends Event>k){
		List<Pair<EventPriority,Consumer>>list=new ArrayList<>();
		for(Class<? extends Event>key:listeners.keySet()){
			if(key.isAssignableFrom(k)){
				list.addAll((List)listeners.get(key));
			}
		}
		list.sort((a,b)->{
			return b.one.compareTo(a.one);
		});
		return list;
		
	}
	
	/**
	 * Submit the specified event to all of the specified EventBus instances, in order
	 * @param e The event to be submitted
	 * @param buses The EventBus instances to receive the event
	 */
	public static void postAll(Event e,Iterable<EventBus>buses){
		for(EventBus b:buses)b.post(e);
	}
}
