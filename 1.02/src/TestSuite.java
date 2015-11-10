import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import sj224.tflp.dice.*;
import sj224.tflp.math.*;
import sj224.tflp.util.ArrayUtil;
import sj224.tflp.util.Random;
import sj224.tflp.util.event.*;
import sj224.tflp.util.workflow.*;

public class TestSuite {
	static class TestEvent extends Event{{setMessage("Hi world!");}}
	public static void main(String[]args) throws InterruptedException{
		EventBus bus=new EventBus();
		bus.logEvents(System.out);
		bus.post(new TestEvent());
	}
}