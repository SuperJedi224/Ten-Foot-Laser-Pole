package sj224.tflp.util.workflow;

import java.util.Arrays;
import java.util.function.*;

/**
 * An asynchronous serial workflow implementation.
 * @author SuperJedi224
 * @param <T> the type of object the workflow's entries manipulate
 */

public final class Workflow<T>{
	private volatile Thread t;
	/**
	 * If any Workflow entry returns this exact object, the workflow will be terminated yielding the preceding return value.
	 */
	public static final Object TERMINATE=new Object();
	@SuppressWarnings("rawtypes")
	private Function[]tasks=new Function[5];
	private int c=0;
	
	/**
	 * Creates a new empty workflow.
	 */
	
	public Workflow(){}
	
	/**
	 * Adds a new entry to the workflow, which will be passed the return value from the previous entry.
	 * @param e A <b>Function&lt;Object,Object&gt;</b> to be called on the result of the previous entry when the new entry is reached, giving the value to be passed to the next entry.
	 * @return This workflow
	 */
	
	public Workflow<T> andThen(Function<T,T>e){
		if(e==null){
			throw new NullPointerException();
		}
		if(c>=tasks.length)tasks=Arrays.copyOf(tasks,(int)(tasks.length*1.5));
		tasks[c]=e;
		c++;
		return this;
	}
	
	/**
	 * Adds a new task to the workflow, which will be passed the return value from the previous entry and is treated as returning null.
	 * @param e A Consumer&lt;Object&gt; to be called on the result of the previous entry when the new entry is reached.
	 * @return This workflow
	 */
	
	public Workflow<T> andThen(Consumer<T>e){
		if(e==null){
			throw new NullPointerException();
		}
		return andThen((Function<T,T>)o->{e.accept(o);return null;});
	}
	
	/**
	 * Adds a new task to the workflow.
	 * @param e A <b>Supplier&lt;Object&gt;</b> to be called when the new entry is reached, passing the supplied object to the next entry.
	 * @return This workflow
	 */
	
	public Workflow<T>andThen(Supplier<T>e){
		if(e==null){
			throw new NullPointerException();
		}
		return andThen((Function<T,T>)o->{return e.get();});
	}
	
	/**
	 * Adds a new task to the workflow, which is treated as returning null.
	 * @param e A <b>Runnable</b> to be executed when the new entry is reached.
	 * @return This workflow
	 */
	
	public Workflow<T> andThen(Runnable e){
		if(e==null){
			throw new NullPointerException();
		}
		return andThen((Function<T,T>)o->{e.run();return null;});
	}
	
	/**
	 * Tells the workflow to pause after completing the preceding task.
	 * @param t The number of milliseconds to pause for.
	 * @return This workflow
	 */
	
	public Workflow<T> sleep(long t){
		return andThen((Function<T,T>)o->{try {
			Thread.sleep(t);
		} catch (Exception e) {
			e.printStackTrace();
		};return o;});
	}
	/**
	 * Duplicates the last task in the Workflow.
	 * @return This workflow
	 */
	@SuppressWarnings("unchecked")
	public Workflow<T>again(){
		if(c==0){
			throw new IllegalStateException("The workflow has no entries to duplicate!");
		}
		andThen(tasks[c-1]);
		return this;
	}
	/**
	 * Creates a copy of the current workflow and runs the copy in a parallel thread.
	 * At most one copy of any given workflow may be running asynchronously at a time.
	 * @param o This object will be passed to the first task in the workflow.
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void start(T o){
		if(t!=null&&t.isAlive()){
			throw new IllegalStateException("This Workflow is already running!");
		}
		final Workflow p=new Workflow();
		for(Function f:tasks.clone()){
			if(f==null)break;
			p.andThen(f);
		}
		t=new Thread(()->{p.run(o);});
		t.start();
	}
	/**
	 * Equivalent to start(null).
	 */
	public void start(){
		start(null);
	}
	public void join() throws InterruptedException{
		if(t==null){
			throw new IllegalStateException("There is no asynchronous instance of this workfolow to join!");
		}
		t.join();
		t=null;
	}
	
	/**
	 * Runs the current workflow
	 * @param o This object is passed to the workflow's first entry
	 * @return The object the workflow's final executed task returns; unless this task returns TERMINATE, in which case the preceding return value is used.
	 */
	
	public T run(T o){
		T p=null;
		for(int i=0;i<tasks.length;i++){
			@SuppressWarnings("unchecked")
			Function<T,T>f=tasks[i];
			if(f==null)break;
			p=f.apply(o);
			if(p==TERMINATE)break;
			o=p;
		}
		return o;
	}	
	/**
	 * Equivalent to run(null).
	 */
	public T run(){
		return run(null);
	}
}