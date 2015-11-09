package sj224.tflp.math;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * An implementation of arbitrary-precision rational numbers, a more powerful alternative to java's built-in BigDecimal
 * @author SuperJedi224
 */
public class BigRational extends Number implements Comparable<Number>{
	public static final BigRational ONE=new BigRational(1);
	public static final BigRational TEN=new BigRational(10);
	public static final BigRational ZERO=new BigRational(0);
	public static final BigRational NaN=new BigRational(0,0);
	public static final BigRational ONE_HALF=new BigRational(1,2);
	public static final BigRational INFINITY=new BigRational(1,0);
	public static final BigRational MINUS_INFINITY=new BigRational(-1,0);
	/**
	 * The BigRational n/d.
	 * @param n The numerator as a BigInteger.
	 * @param d The denominator as a BigInteger.
	 */
	public BigRational(BigInteger n, BigInteger d) {
		if(d.compareTo(BigInteger.ZERO)<0){
			n=n.negate();
			d=d.negate();
		}
		BigInteger r=n.gcd(d);
		if(!r.equals(BigInteger.ZERO)){n=n.divide(r);d=d.divide(r);}
		this.n=n;
		this.d=d;
	}
	/**
	 * The BigRational val/1=val.
	 * @param val The value as a long
	 */
	public BigRational(long val) {
		this(val,1L);
	}
	/**
	 * The BigRational n/d.
	 * @param n The numerator as a long.
	 * @param d The denominator as a long.
	 */
	public BigRational(long n,long d){
		this(BigInteger.valueOf(n),BigInteger.valueOf(d));
	}
	/**
	 * The BigRational representation of the value represented by a string.
	 * The string may be formatted as a fraction, an integer percentage, a terminating decimal, or a whole number.
	 * @param val The string representation of the value.
	 */
	public BigRational(String val) {
		String[] i=val.split("/");
		BigInteger x,y;
		if(i.length==1){
			if(val.indexOf("%")!=-1){
				x=new BigInteger(i[0].substring(0,i[0].length()-1));
				y=BigInteger.valueOf(100);
			}else if(val.indexOf(".")!=-1){
				BigDecimal v=new BigDecimal(val);
				x=v.unscaledValue();
				y=BigInteger.TEN.pow(v.scale());
			}else{
				x=new BigInteger(i[0]);
				y=BigInteger.ONE;
			}
		}else{
			x=new BigInteger(i[0]);
			y=new BigInteger(i[1]);
		}
		BigRational z=new BigRational(x,y);
		n=z.n;
		d=z.d;
	}
	public BigRational(BigInteger value) {
		this(value,BigInteger.ONE);
	}
	private static final long serialVersionUID = 1L;
	private final BigInteger n,d;
	/**
	 * Find and return the sum of this and another BigRational.
	 * @param v The BigRational value to be added.
	 * @return The sum, as a third BigRational.
	 */
	public BigRational add(BigRational v){
		return new BigRational(n.multiply(v.d).add(v.n.multiply(d)),d.multiply(v.d));
	}
	/**
	 * @see #compareTo(Number)
	 */
	public int compareTo(BigDecimal o){
		return compareTo(valueOf(o));
	}
	/**
	 * @see #compareTo(Number)
	 */
	public int compareTo(BigInteger o){
		BigInteger[] k=((BigInteger)n).divideAndRemainder(d);
		int q=k[0].compareTo(o);
		if(q==0&&!k[1].equals(BigInteger.ZERO))q=1;
		return q;
	}
	/**
	 * @see #compareTo(Number)
	 */
	public int compareTo(BigRational o){
		if(isNaN())return o.isNaN()?0:-1;
		if(toString().equals("Infinity"))return o.toString().equals("Infinity")?0:1;
		if(toString().equals("-Infinity"))return o.toString().equals("-Infinity")?0:-1;
		return n.multiply(o.d).compareTo(o.n.multiply(d));
	}
	/**
	 * @throws ArithmeticException when the Complex object represents a nonreal value
	 * @see #compareTo(Number)
	 */
	public int compareTo(Complex o){
		if(o.isReal())return compareTo(o.real());
		throw new ArithmeticException();
	}
	/**
	 * @see #compareTo(Number)
	 */
	public int compareTo(Double o){
		if(isNaN())return o.isNaN()?0:-1;
		if(toString().equals("Infinity"))return o==Double.POSITIVE_INFINITY?0:1;
		if(toString().equals("-Infinity"))return o==Double.NEGATIVE_INFINITY?0:-1;
		return -o.compareTo(doubleValue());
	}
	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Number n){
		if(n instanceof Double)return compareTo((Double)n);
		if(n instanceof BigDecimal)return compareTo((BigDecimal)n);
		if(n instanceof BigInteger)return compareTo((BigInteger)n);
		return compareTo(n.doubleValue());
	}
	/**
	 * Find and return the quotient of this and another BigRational.
	 * @param v The BigRational value to be added.
	 * @return The quotient, as a third BigRational.
	 */
	public BigRational divide(BigRational v){
		return new BigRational(n.multiply(v.d),d.multiply(v.n));
	}
	/**
	 * @see java.lang.Number#doubleValue()
	 */
	public double doubleValue() {
		return doubleValue(16);
	}
	/**
	 * The BigRational representation of a BigDecimal object.
	 * @param val The BigDecimal object whose numeric value the returned BigRational represents.
	 * @return The BigRational object having the same numeric value as the passed BigDecimal.
	 */
	public static BigRational valueOf(BigDecimal val){
		BigInteger x,y;
		if(val.scale()<0){
			x=val.toBigIntegerExact();
			y=BigInteger.ONE;
		}else{
			x=val.unscaledValue();
			y=BigInteger.TEN.pow(val.scale());
		}
		return new BigRational(x,y);
	}
	/**
	 * The double value of this BigRational to a specified number of decimal places. Returns NaN if this BigRational represents the ratio 0/0.
	 * @param decimalPlaces
	 * @return The double value of the represented rational number, truncated to the specified number of decimal places.
	 */
	public double doubleValue(int decimalPlaces) {
		if(isNaN())return Double.NaN;
		if(d.equals(BigInteger.ZERO))return n.compareTo(BigInteger.ZERO)>0?Double.POSITIVE_INFINITY:Double.NEGATIVE_INFINITY;
		BigInteger v=n;
		BigInteger[] k=n.divideAndRemainder(d);
		double c=k[0].longValue();
		double f=0.1;
		for(int i=0;i<decimalPlaces;i++){
			v=k[1].multiply(BigInteger.TEN);
			k=v.divideAndRemainder(d);
			c+=k[0].longValue()*f;
			f*=0.1;
		}
		return c;
	}
	/**
	 * @see java.lang.Number#floatValue()
	 */
	public float floatValue() {
		return (float)doubleValue();
	}
	/**
	 * @throws ArithmeticException if this BigRational represents the ratio 0/0 
	 * @see java.lang.Number#intValue()
	 */
	public int intValue() {
		return n.divide(d).intValue();
	}
	/**
	 * Returns true if this BigRational represents the ratio 0/0
	 */
	public boolean isNaN(){
		return n.toString().equals("0")&&d.toString().equals("0");
	}
	/**
	 * @throws ArithmeticException if this BigRational represents the ratio 0/0
	 * @see java.lang.Number#longValue()
	 */
	public long longValue() {
		return n.divide(d).longValue();
	}
	/**
	 * @see java.math.BigInteger#negate()
	 */
	public BigRational negate(){
		return new BigRational(n.negate(),d);
	}
	/**
	 * Raises this BigRational to an integer power
	 * @param exp The power to raise it to
	 * @return The result, as another BigRational
	 */
	public BigRational pow(int exp){
		if(exp==0)return BigRational.ONE;
		if(exp<0)return new BigRational(d.pow(-exp),n.pow(-exp));
		return new BigRational(n.pow(exp),d.pow(exp));
	}
	/**
	 * Find and return the difference of this and another BigRational.
	 * @param v The BigRational value to be subtracted.
	 * @return The difference, as a third BigRational.
	 */
	public BigRational subtract(BigRational v){
		return add(v.negate());
	}
	/**
	 * Find and return the product of this and another BigRational.
	 * @param v The BigRational value to be multiplied by.
	 * @return The product, as a third BigRational.
	 */
	public BigRational multiply(BigRational v){
		return new BigRational(n.multiply(v.n),d.multiply(v.d));
	}
	/**
	 * Returns a string containing the fractional representation of this BigRational's value
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		if(isNaN())return "NaN";
		if(d.equals(BigInteger.ZERO))return n.compareTo(BigInteger.ZERO)>0?"Infinity":"-Infinity";
		if(d.equals(BigInteger.ONE))return ""+n;
		return n+"/"+d;
	}
	/**
	 * Returns a string containing a decimal approximation of this BigRational's value. Truncates to a specified number of decimal places.
	 * @param decimalPlaces The number of decimal places to use
	 * @return A string representation as described above
	 */
	public String toString(int decimalPlaces){
		BigInteger v=n;
		BigInteger[] k=n.divideAndRemainder(d);
		String c=k[0].longValue()+".";
		for(int i=0;i<decimalPlaces;i++){
			v=k[1].multiply(BigInteger.TEN);
			k=v.divideAndRemainder(d);
			c+=k[0].longValue();
		}
		return c;
	}
	public BigInteger numerator(){
		return n;
	}
	public BigInteger denominator(){
		return d;
	}
}