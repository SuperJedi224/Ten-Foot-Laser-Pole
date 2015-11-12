package sj224.tflp.util;

import javax.swing.JOptionPane;

/**
 * This class provides utility methods for creating simple alerts and prompts.
 * @author Johnathan Waugh
 */
public class PopupGui{
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
	 */
	public static String prompt(){
		return prompt("");
	}
	/**
	 * Produces a prompt box with a given request and returns the value the user enters
	 * @param request The request shown in the prompt box
	 * @return The value entered into the prompt box
	 */
	public static String prompt(String request){
		return JOptionPane.showInputDialog(null, request, "", JOptionPane.QUESTION_MESSAGE);
	}
	/**
	 * Produces a prompt box and returns the value the user enters, parsed as a double. If input given is invalid, yields NaN.
	 * @return The value entered into the prompt box, parsed as a double
	 */
	public static double promptDouble(){
		return promptDouble("");
	}
	/**
	 * Produces a prompt box with a given request and returns the value the user enters, parsed as a double. If input given is invalid, yields NaN.
	 * @param request The request shown in the prompt box
	 * @return The value entered into the prompt box, parsed as a double
	 */
	public static double promptDouble(String request){
		try{return Double.parseDouble(prompt(request));}catch(Exception e){return Double.NaN;}
	}
	/**
	 * Produces a prompt box and returns the value the user enters, parsed as an integer.
	 * @throws NumberFormatException When an invalid input is given
	 * @return The value entered into the prompt box, parsed as an integer
	 */
	public static int promptInt(){
		return promptInt("");
	}
	/**
	 * Produces a prompt box with a given request and returns the value the user enters, parsed as an integer.
	 * @param request The request shown in the prompt box
	 * @throws NumberFormatException When an invalid input is given
	 * @return The value entered into the prompt box, parsed as an integer
	 */
	public static int promptInt(String request){
		return Integer.parseInt(prompt(request));
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
	 */
	public static boolean confirm(String title,String text){
		int a=JOptionPane.showConfirmDialog(null, text, title, JOptionPane.OK_CANCEL_OPTION);
		return a==JOptionPane.OK_OPTION;
	}
	/**
	 * Produces an OK/CANCEL confirm dialogue, and returns a boolean representing the user's response
	 * @param text The text of the confirm dialogue
	 * @return true if the user selects OK, false otherwise
	 */
	public static boolean confirm(String text){
		return confirm("",text);
	}
	@SafeVarargs
	/**
	 * Produces a popup dialogue for requesting that the user select an element from a list
	 * @param prompt the text of the prompt
	 * @param options a tuple of same-type objects to be selected from
	 * @return the selected object
	 */
	public static <T> T option(String prompt,T... options){
		return options[JOptionPane.showOptionDialog(null, prompt, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])];
	}
}
