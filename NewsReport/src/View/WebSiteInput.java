package View;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.UUID;

public class WebSiteInput extends JPanel{
	
	private JTextField textField;
	private JButton buttonRemove;
	private UUID id;
	
	public WebSiteInput() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		buttonRemove = new JButton("");
		buttonRemove.setBackground(Color.WHITE);
		buttonRemove.setIcon(new ImageIcon(WebSiteInput.class.getResource("/images/icon8-removes.png")));
		buttonRemove.setBounds(5, 0, 22, 20);
		add(buttonRemove);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField.setBounds(30, 0, 260, 20);
		add(textField);
		textField.setColumns(10);
		
		id = UUID.randomUUID();
	}

	public JButton getRemoveButton() {
		return this.buttonRemove;
	}
	
	public String getText() {
		return this.textField.getText();
	}
	
	public void setText(String text) {
		this.textField.setText(text);
	}
	
	public UUID getId() {
		return this.id;
	}
	
}
