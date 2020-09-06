package Controller;

import javax.swing.JOptionPane;

public class ExceptionHandler {

	
	public static void handle(Exception e, String message) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, message + " " + e.getCause().toString());
		
	}
}
