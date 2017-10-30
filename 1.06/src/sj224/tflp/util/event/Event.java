package sj224.tflp.util.event;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Concrete implementations of this abstract class represent different kinds of events for use with the event engine.
 * @author Johnathan Waugh
 */
public abstract class Event {
	public Event(){
		time=new Date();
	}
	private boolean c=false;
	boolean isCancelled(){
		return c;
	}
	/**
	 * Cancel this event, suppressing all remaining listeners. Should only be called from within an event listener.
	 */
	public void cancel(){
		c=true;
	}
	private String message="";
	
	/**
	 * Sets the message associated with this event. This message will be included when and if the event is logged.
	 * @param message The message to assign.
	 */
	protected void setMessage(String message){
		this.message=message==null?"":message;
	}
	void log(PrintStream p){
		p.println("["+new SimpleDateFormat("HH:mm:ss").format(time)+"] Event "+getClass().getSimpleName()+(message.isEmpty()?"":" - "+message));
	}
	private final Date time;
}