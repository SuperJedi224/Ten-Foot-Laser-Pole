package sj224.tflp.math;

/**
 * An implementation of complex numbers
 * 
 * @author Johnathan Waugh
 */
public class Complex extends Number{
	/**
	 * The <b>Complex</b> object representing x
	 * @param x The value being represented
	 */
	public Complex(double x){
		this(x,0);
	}
	/**
	 * The <b>Complex</b> object representing the value x+y<i>i</i>
	 * @param x The real part of the value being represented
	 * @param y The coefficient of the imaginary part of the value being represented
	 */
	public Complex(double x,double y){
		a=x;
		b=y;
	}
	/**
	 * The imaginary constant <i>i</i>
	 */
	public final static Complex I=new Complex(0,1);
	/**
	 * The imaginary constant -<i>i</i>
	 */
	public final static Complex MINUS_I=new Complex(0,-1);
	private static final long serialVersionUID = 1L;
	private final double a,b;
	/**
	 * The natural logarithm of a <b>double</b>, as a <b>Complex</b> object. <br>
	 * Unlike Math.log, this gives an actual value for negative inputs.
	 * @param a The value whose natural logarithm is to be taken
	 * @return The natural logarithm of the specified <b>double</b>, as a <b>Complex</b> object
	 */
	public static Complex log(double a){
		return (new Complex(a)).log();
	}
	/**
	 * The square root of a complex number, as another <b>Complex</b> object
	 * @param z The value whose the natural logarithm is to be taken
	 * @return The natural logarithm of the specified complex number
	 */
	public static Complex sqrt(Complex z){
		return z.sqrt();
	}
	/**
	 * The square root of a <b>double</b>, as a <b>Complex</b> object. <br>
	 * Unlike Math.sqrt, this gives an actual value for negative inputs.
	 * @param a The value whose square root is to be taken
	 * @return The square root of the specified <b>double</b>, as a <b>Complex</b> object
	 */
	public static Complex sqrt(double a){
		if(a<=0)return new Complex(0,Math.sqrt(-a));
		return new Complex(Math.sqrt(a));
	}
	/**
	 * Divides the represented complex number by the specified real
	 * @param r The number to be divided by
	 * @return A <b>Complex</b> object representing the quotient
	 */
	public Complex dividedBy(double r){
		return new Complex(a/r,b/r);
	}
	/**
	 * Divides the represented complex number by another complex number
	 * @param z A <b>Complex</b> object representing the number to be divided by
	 * @return A <b>Complex</b> object representing the quotient
	 */
	public Complex dividedBy(Complex z){
		Complex conj=new Complex(z.a,-z.b);
		Complex p1=times(conj);
		double r2=z.times(conj).real();
		return new Complex(p1.a/r2,p1.b/r2);
	}
	/**
	 * @see java.lang.Number#doubleValue()
	 */
	public double doubleValue() {
		if(isReal())return a;
		return Double.NaN;
	}
	/**
	 * @see java.lang.Number#floatValue()
	 */
	public float floatValue() {
		return (float)doubleValue();
	}
	/**
	 * The coefficient of the imaginary part of the represented complex number
	 * @return The coefficient of the imaginary part of the represented complex number
	 */
	public double imag(){
		return b;
	}
	/**
	 * @see java.lang.Number#intValue()
	 * @throws ArithmeticException when thic Complex object represents a nonreal value
	 */
	public int intValue() {
		if(isReal())return (int)a;
		throw new ArithmeticException();
	}
	/**
	 * Checks whether or not the current <b>Complex</b> object represents a real number
	 * @return true, if and only if imag() returns zero; false otherwise.
	 * @see #imag()
	 */
	public boolean isReal(){
		return b==0;
	}
	/**
	 * The natural logarithm of the represented complex number
	 * @return Another <b>Complex</b> object representing the natural logarithm of the value represented by the current one
	 */
	public Complex log(){
		return new Complex(Math.log(magnitude()),theta());
	}
	/**
	 * @see java.lang.Number#longValue()
	 * @throws ArithmeticException when this Complex object represents a nonreal value
	 */
	public long longValue() {
		if(isReal())return (long)a;
		throw new ArithmeticException();
	}
	/**
	 * The magnitude (absolute value) of the represented complex number
	 * @return The magnitude of the represented complex number
	 */
	public double magnitude(){
		return Math.sqrt(a*a+b*b);
	}
	/**
	 * The sum of this complex number, and another one.
	 * @param z A <b>Complex</b> object representing the value to be added
	 * @return A <b>Complex</b> object representing the sum
	 */
	public Complex plus(Complex z){
		return new Complex(a+z.a,b+z.b);
	}
	/**
	 * The sum of this complex number, and a real number.
	 * @param x The value to be added
	 * @return A <b>Complex</b> object representing the sum
	 */
	public Complex plus(double x){
		return new Complex(a+x,b);
	}
	/**
	 * This complex number raised to the power of a double
	 * @param a The power to which it is being raised
	 * @return A <b>Complex</b> object representing the result
	 */
	public Complex pow(double a){
		return new Complex(Math.pow(magnitude(),a)*Math.cos(theta()*a),Math.pow(magnitude(),a)*Math.sin(theta()*a));
	}
	/**
	 * The real part of this complex number
	 * @return The real part of this complex number
	 */
	public double real(){
		return a;
	}
	/**
	 * The square root of the represented complex number
	 * @return Another <b>Complex</b> object representing the square root of this one
	 * @see #sqrt(Complex)
	 */
	public Complex sqrt(){
		return pow(0.5);
	}
	/**
	 * The angle counterclockwise from the positive x-axis to the ray starting at the origin and going through the point on the complex plane which represents this complex number
	 * @return Said angle, in radians, which is equal to the coefficient of the imaginary part of this complex number's natural logarithm
	 */
	public double theta(){
		return java.lang.Math.atan2(b,a);
	}
	/**
	 * The product of this and another complex number
	 * @param z A <b>Complex</b> object representing the complex number to be multiplied by
	 * @return A <b>Complex</b> object representing the product
	 */
	public Complex times(Complex z){
		return new Complex(a*z.a-b*z.b,b*z.a+a*z.b);
	}
	/**
	 * The product of this complex number and a real number
	 * @param x the number to be multiplied by
	 * @return A <b>Complex</b> object representing the product
	 */
	public Complex times(double x){
		return new Complex(a*x,b*x);
	}
	public boolean equals(Object o){
		if(o instanceof Complex){
			Complex c=(Complex)o;
			return a==c.a&&b==c.b;
		}
		if(o instanceof Number && isReal()){
			Number c=(Number)o;
			return a==c.doubleValue();
		}
		return false;
	}	
	public String toString(){
		String j=(b>0)?"+":"";
		String k=(b==1)?"":((b==-1)?"-":""+((b==(int)b)?""+(int)b:""+b));
		if(b==0){
			if(a==(int)a)return ""+(int)a;
			return ""+a;
		}
		if(a==0){
			if(b==(int)b)return k+"i";
			return b+"i";
		}
		if(a==(int)a&&b==(int)b)return (int)a+j+k+"i";
		if(a==(int)a)return (int)a+j+k+"i";
		if(b==(int)b)return a+j+k+"i";
		return a+j+k+"i";
	}
}
