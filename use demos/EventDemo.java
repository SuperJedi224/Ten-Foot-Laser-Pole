import sj224.tflp.util.event.*;

public class EventDemo{
	static class TestEvent extends Event{
		public TestEvent(){
			
		}
		public TestEvent(String a){
			//This protected method may be used to add an additional message to the log entry for an event
			this.setMessage(a);
		}
	}
	static class PrintEvent extends Event{
		public PrintEvent(Object o){
			this.o=o;
		}
		Object o;
	}
	public static void main(String[]args){
		EventBus b=new EventBus();
		b.logEvents(System.out);
		b.post(new TestEvent());
		b.post(new TestEvent("Hello, World!"));
		EventBus c=new EventBus();
		//This listener will be executed first
		c.addListener(PrintEvent.class,a->{System.out.println(a.o);},EventPriority.MAXIMUM);
		//This one won't be run, because of the following one
		c.addListener(PrintEvent.class,a->{System.out.println("Hi!");},EventPriority.LOW);
		//Default priority is NORMAL. This listener cancels the event, suppressing all remaining listeners.
		c.addListener(PrintEvent.class,a->a.cancel());
		//This event won't be logged- it's on a different bus!
		c.post(new PrintEvent(new Object()));
	}
}
