import java.util.function.*;

import sj224.tflp.util.workflow.Workflow;

public class WorkflowDemo {
	public static void main(String[]args){
		Workflow<Integer>t=new Workflow<Integer>()
				.andThen((Function<Integer,Integer>/*Java is really bad at inferring lambda types*/)a->a+1)
				.sleep(400)/*pause the workflow for 400 milliseconds*/
				.andThen((Consumer<Integer>/*Ditto*/)e->System.out.println(e));
		//Start the workflow asynchronously. Pass 3 to its first entry.
		t.start(3);
		System.out.println(1);
		try {
			//Wait for it to finish.
			t.join();
		} catch (InterruptedException e){}
		System.out.println(2);
	}
}
