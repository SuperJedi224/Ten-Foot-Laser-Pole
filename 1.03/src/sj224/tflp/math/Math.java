package sj224.tflp.math;

import java.math.BigInteger;

/**
 * Duplicates the key functionality of java.lang.Math, and adds various additional utility methods for dealing with special Number types.
 * @author Johnathan Waugh
 */
public final class Math {
	private Math() {}

	public static final double E = java.lang.Math.E;
	public static final double PHI = (1 + sqrt(5)) / 2;
	public static final double PI = java.lang.Math.PI;

	/**
	 * @see Complex#magnitude()
	 */
	public static double abs(Complex z) {
		return z.magnitude();
	}

	/** @see java.lang.Math#abs(double) */
	public static double abs(double a) {
		return java.lang.Math.abs(a);
	}

	/** @see java.lang.Math#abs(float) */
	public static float abs(float a) {
		return java.lang.Math.abs(a);
	}
	
	/**
	 * Tests if n is a prime number
	 * @param n The number to be tested
	 * @return true if n is prime, false otherwise
	 */
	public static boolean isPrime(long n) {
	    if(n < 2) return false;
	    if(n == 2 || n == 3)return true;
	    if(n%2 == 0 || n%3 == 0)return false;
	    long sqrtN=(long)Math.sqrt(n)+1;
	    for(long i=6; i <= sqrtN; i += 6) {
	        if(n%(i-1) == 0 || n%(i+1) == 0) return false;
	    }
	    return true;
	}

	/** @see java.lang.Math#abs(int) */
	public static int abs(int a) {
		return java.lang.Math.abs(a);
	}

	/** @see java.lang.Math#abs(long) */
	public static long abs(long a) {
		return java.lang.Math.abs(a);
	}

	/** @see java.lang.StrictMath#acos(double) */
	public static double acos(double a) {
		return java.lang.StrictMath.acos(a);
	}

	/** @see java.lang.StrictMath#asin(double) */
	public static double asin(double a) {
		return java.lang.StrictMath.asin(a);
	}

	/** @see java.lang.StrictMath#atan(double) */
	public static double atan(double a) {
		return java.lang.StrictMath.atan(a);
	}

	/** @see java.lang.StrictMath#atan2(double,double) */
	public static double atan2(double y, double x) {
		return java.lang.StrictMath.atan2(y, x);
	}

	public static Complex cbrt(Complex z) {
		return z.pow(1 / 3.0);
	}

	/** @see java.lang.StrictMath#cbrt(double) */
	public static double cbrt(double a) {
		return java.lang.StrictMath.cbrt(a);
	}

	/** @see java.lang.StrictMath#ceil(double) */
	public static double ceil(double a) {
		return java.lang.Math.ceil(a);
	}

	/** @see java.lang.StrictMath#cos(double) */
	public static double cos(double a) {
		return java.lang.StrictMath.cos(a);
	}
	
	public static Complex exp(Complex d) {
		double k=exp(d.real());
		return new Complex(k*cos(d.imag()),k*sin(d.imag()));
	}

	/** @see java.lang.StrictMath#exp(double) */
	public static double exp(double d) {
		return java.lang.StrictMath.exp(d);
	}

	/** @see java.lang.StrictMath#floor(double) */
	public static double floor(double a) {
		return java.lang.Math.floor(a);
	}

	/**
	 * @see Complex#log()
	 */
	public static Complex log(Complex z) {
		return z.log();
	}

	/** @see java.lang.StrictMath#log(double) */
	public static double log(double a) {
		return java.lang.StrictMath.log(a);
	}

	/** @see java.lang.Math#max(double,double) */
	public static double max(double a, double b) {
		return java.lang.Math.max(a, b);
	}

	/** @see java.lang.Math#max(float,float) */
	public static float max(float a, float b) {
		return java.lang.Math.max(a, b);
	}

	/** @see java.lang.Math#max(int,int) */
	public static int max(int a, int b) {
		return java.lang.Math.max(a, b);
	}

	/** @see java.lang.Math#max(long,long) */
	public static long max(long a, long b) {
		return java.lang.Math.max(a, b);
	}

	/** @see java.lang.Math#min(double,double) */
	public static double min(double a, double b) {
		return java.lang.Math.min(a, b);
	}

	/** @see java.lang.Math#min(float,float) */
	public static float min(float a, float b) {
		return java.lang.Math.min(a, b);
	}

	/** @see java.lang.Math#min(int,int) */
	public static int min(int a, int b) {
		return java.lang.Math.min(a, b);
	}

	/** @see java.lang.Math#min(long,long) */
	public static long min(long a, long b) {
		return java.lang.Math.min(a, b);
	}

	/** @see Complex#pow(double) */
	public static Complex pow(Complex z, double b) {
		return z.pow(b);
	}

	public static Complex pow(double a, Complex z) {
		return exp(z).pow(log(a));
	}

	/** @see java.lang.StrictMath#pow(double,double) */
	public static double pow(double a, double b) {
		return java.lang.StrictMath.pow(a, b);
	}

	/** @see java.lang.Math#random() */
	public static double random() {
		return java.lang.Math.random();
	}

	/** @see java.lang.Math#round(double) */
	public static double round(double a) {
		return java.lang.Math.round(a);
	}

	/** @see java.lang.Math#round(float) */
	public static float round(float a) {
		return java.lang.Math.round(a);
	}

	/** @see java.lang.StrictMath#sin(double) */
	public static double sin(double a) {
		return java.lang.StrictMath.sin(a);
	}

	/**
	 * @see Complex#sqrt()
	 */
	public static Complex sqrt(Complex z) {
		return z.sqrt();
	}

	/** @see java.lang.StrictMath#sqrt(double) */
	public static double sqrt(double a) {
		return java.lang.StrictMath.sqrt(a);
	}

	/** @see java.lang.StrictMath#tan(double) */
	public static double tan(double a) {
		return java.lang.StrictMath.tan(a);
	}
	/**
	 * Approximates n! as a double, using Stirling's Approximation.
	 * @return The result
	**/
	public static double approxFactorial(int n){
		return pow(n/Math.E,n)*sqrt(2*n*Math.PI);
	}
	
	/**
	 * Finds floor(sqrt(n))
	 * @param n The number whose square root is to be taken
	 * @return floor(sqrt(n)) as a BigInteger.
	 */
	public static BigInteger sqrt(BigInteger n){
		if(n.signum()<0)throw new ArithmeticException();
		int i=n.bitLength()/2;
		if(n.equals(BigInteger.ZERO))return n;
		if(n.compareTo(BigInteger.valueOf(4))<0)return BigInteger.ONE;
		final BigInteger TWO=BigInteger.valueOf(2);
		BigInteger a=TWO.pow(i-1);
		BigInteger b=TWO.pow(i+1);
		while(b.multiply(b).compareTo(n)>0){
			b=b.add(a).divide(TWO);
		}
		if(b.multiply(b).equals(n))return b;
		while(i>=0){
			BigInteger c=b.add(TWO.pow(i--));
			if(c.multiply(c).compareTo(n)<=0){
				b=c;
			}
		}
		return b;
	}
	public static BigRational sqrt(BigRational v,int iter){
		if(v.compareTo(BigRational.ZERO)<0||v.isNaN())return BigRational.NaN;
		if(v.equals(BigRational.ZERO)||v.equals(BigRational.INFINITY)||v.equals(BigRational.MINUS_INFINITY))return v;
		BigRational a=new BigRational(sqrt(v.numerator()),sqrt(v.denominator()));
		if(a.multiply(a).equals(v))return a;
		for(int i=0;i<iter;i++){
			a=a.add(v.divide(a)).multiply(BigRational.ONE_HALF);
		}
		return a;
	}
	public static BigInteger floor(BigRational v){
		return v.numerator().divide(v.denominator());
	}
	public static BigRational sqrt(BigRational v){
		return sqrt(v,5);
	}
}