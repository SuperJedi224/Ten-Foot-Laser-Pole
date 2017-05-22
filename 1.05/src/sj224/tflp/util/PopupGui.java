package sj224.tflp.util;

import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * This class provides utility methods for creating simple alerts and prompts.
 * @author Johnathan Waugh
 */
public final class PopupGui{	
	/**
	 * This exception is thrown when a GUI prompt is closed without providing input.
	 * @author Johnathan Waugh
	 */
	public static final class InputCancelledException extends IOException{
		private static final long serialVersionUID = 1L;
		public InputCancelledException(){};
	}
	/**
	 * This exception is thrown when a GUI prompt is provided an illegal value for that input type.
	 * @author Johnathan Waugh
	 */
	public static final class IllegalInputException extends IOException{
		private static final long serialVersionUID = 1L;
		public IllegalInputException(Exception e){};
	}
	private PopupGui(){}
	/**
	 * Produces a simple alert box
	 * @param text The contents of the alert box
	 */
	public static void alert(String text){
		alert("",text);
	}
	/**
	 * Produces a simple alert box, with a custom title
	 * @param title The title of the alert box
	 * @param text The contents of the alert box
	 */
	public static void alert(String title,String text){
		JOptionPane.showMessageDialog(null, text, title, JOptionPane.PLAIN_MESSAGE);
	}
	/**
	 * Produces a prompt box and returns the value the user enters
	 * @return The value entered into the prompt box
	 * @throws InputCancelledException 
	 */
	public static String prompt() throws InputCancelledException{
		return prompt("");
	}
	/**
	 * Produces a prompt box with a given request and returns the value the user enters
	 * @param request The request shown in the prompt box
	 * @return The value entered into the prompt box
	 * @throws InputCancelledException 
	 */
	public static String prompt(String request) throws InputCancelledException{
		String g=JOptionPane.showInputDialog(null, request, "", JOptionPane.QUESTION_MESSAGE);
		if(g==null)throw new InputCancelledException();
		return g;
	}
	/**
	 * Produces a prompt box and returns the value the user enters, parsed as a double. If input given is invalid, yields NaN.
	 * @return The value entered into the prompt box, parsed as a double
	 * @throws InputCancelledException 
	 * @throws IllegalInputException 
	 */
	public static double promptDouble() throws IllegalInputException, InputCancelledException{
		return promptDouble("");
	}
	/**
	 * Produces a prompt box with a given request and returns the value the user enters, parsed as a double. If input given is invalid, yields NaN.
	 * @param request The request shown in the prompt box
	 * @return The value entered into the prompt box, parsed as a double
	 * @throws IllegalInputException 
	 * @throws InputCancelledException 
	 */
	public static double promptDouble(String request) throws IllegalInputException, InputCancelledException{
		try{return Double.parseDouble(prompt(request));}catch(NumberFormatException e){throw new IllegalInputException(e);}
	}
	/**
	 * Produces a prompt box and returns the value the user enters, parsed as an integer.
	 * @throws NumberFormatException When an invalid input is given
	 * @return The value entered into the prompt box, parsed as an integer
	 * @throws InputCancelledException 
	 * @throws IllegalInputException 
	 */
	public static int promptInt() throws InputCancelledException, IllegalInputException{
		return promptInt("");
	}
	/**
	 * Produces a prompt box with a given request and returns the value the user enters, parsed as an integer.
	 * @param request The request shown in the prompt box
	 * @throws NumberFormatException When an invalid input is given
	 * @return The value entered into the prompt box, parsed as an integer
	 * @throws InputCancelledException 
	 * @throws IllegalInputException 
	 */
	public static int promptInt(String request) throws InputCancelledException, IllegalInputException{
		String k=prompt(request);
		try{return Integer.parseInt(k);}catch(NumberFormatException e){throw new IllegalInputException(e);}
	}
	/**
	 * Produces a warning message
	 * @param text The contents of the warning
	 */
	public static void warn(String text){
		warn("",text);
	}
	/**
	 * Produces a warning message, with a custom title
	 * @param title The title of the warning
	 * @param text The contents of the warning
	 */
	public static void warn(String title,String text){
		JOptionPane.showMessageDialog(null, text, title, JOptionPane.WARNING_MESSAGE);
	}
	/**
	 * Produces an OK/CANCEL confirm dialogue, and returns a boolean representing the user's response
	 * @param title The title of the confirm dialogue
	 * @param text The text of the confirm dialogue
	 * @return true if the user selects OK, false otherwise
	 * @throws InputCancelledException 
	 */
	public static boolean confirm(String title,String text) throws InputCancelledException{
		int a=JOptionPane.showConfirmDialog(null, text, title, JOptionPane.OK_CANCEL_OPTION);
		if(a==-1)throw new InputCancelledException();
		return a==JOptionPane.OK_OPTION;
	}
	/**
	 * Produces an OK/CANCEL confirm dialogue, and returns a boolean representing the user's response
	 * @param text The text of the confirm dialogue
	 * @return true if the user selects OK, false otherwise
	 * @throws InputCancelledException 
	 */
	public static boolean confirm(String text) throws InputCancelledException{
		return confirm("",text);
	}
	@SafeVarargs
	/**
	 * Produces a popup dialogue for requesting that the user select an element from a list
	 * @param prompt the text of the prompt
	 * @param options a tuple of same-type objects to be selected from
	 * @return the selected object
	 * @throws InputCancelledException
	 */
	public static <T> T option(String prompt,T... options) throws InputCancelledException{
		int i=JOptionPane.showOptionDialog(null, prompt, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if(i==-1)throw new InputCancelledException();
		return options[i];
	}
}
