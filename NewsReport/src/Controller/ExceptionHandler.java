package Controller;

import javax.swing.JOptionPane;

/**
 * A simple class with a single static method that can be used to pass an exception.
 * @author Sime
 *
 */
public class ExceptionHandler {

	/**
	 * Handles exceptions by showing a pop-up with the message and prints the details of the exception's stack trace. 
	 * @param e The handled exception.
	 * @param message Any custom message to show in the pop-up modal.
	 */
	public static void handle(Exception e, String message) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, message);
		
	}
}
