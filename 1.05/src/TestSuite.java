import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.swing.JOptionPane;

import sj224.tflp.dice.*;
import sj224.tflp.math.*;
import sj224.tflp.math.Math;
import sj224.tflp.util.ArrayUtil;
import sj224.tflp.util.Pair;
import sj224.tflp.util.PopupGui;
import sj224.tflp.util.Random;
import sj224.tflp.util.StringUtil;
import sj224.tflp.util.event.*;
import sj224.tflp.util.workflow.*;

public class TestSuite {
	public static void main(String[]args){
		System.out.println(ArrayUtil.toString(Math.delta(new int[]{0,1,5,3,2,9})));
	}
}