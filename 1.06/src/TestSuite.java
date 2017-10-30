import java.util.Random;
import java.util.Set;

import sj224.tflp.util.ArrayUtil;
import sj224.tflp.util.BijectiveHashMap;
import sj224.tflp.util.ImmutableSet;
import sj224.tflp.util.SetUtil;
import sj224.tflp.util.WeightedRandom;
import sj224.tflp.util.event.Event;
import sj224.tflp.util.event.EventBus;
import sj224.tflp.util.event.EventPriority;

public class TestSuite {
	static class E1 extends Event{
		
	}
	static class E2 extends Event{
		
	}
	public static void main(String[]args){
		Set<Integer>set=SetUtil.empty();
		System.out.println(SetUtil.complement(set).contains(1));
	}
}