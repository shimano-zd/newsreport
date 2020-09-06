package View;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import Controller.ApplicationState;
import Model.ILanguage;

import javax.swing.JLabel;
import java.awt.Font;

public class HelpPanel extends JPanel implements ILanguageStateObserver {

	private JLabel helpTitle;
	private JLabel stepOneTitle;
	private JLabel stepTwoTitle;
	private JLabel stepThreeTitle;
	private JLabel stepThreeATitle;
	private JLabel stepThreeBTitle;
	private ApplicationState appState;
	
	public HelpPanel() {

		setLayout(null);
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.lightGray));
		setBackground(Color.WHITE);
		setBounds(20, 49, 639, 423);
		
		appState = ApplicationState.instance();
		appState.addLanguageObserver(this);
		
		createComponents();
	}
	
	private void createComponents() {
		
		helpTitle = new JLabel();
		helpTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
		helpTitle.setBounds(10, 11, 216, 29);
		add(helpTitle);
		
		stepOneTitle = new JLabel();
		stepOneTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		stepOneTitle.setBounds(10, 66, 619, 22);
		add(stepOneTitle);
		
		stepTwoTitle = new JLabel();
		stepTwoTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		stepTwoTitle.setBounds(10, 99, 619, 22);
		add(stepTwoTitle);
		
		stepThreeTitle = new JLabel();
		stepThreeTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		stepThreeTitle.setBounds(10, 132, 619, 22);
		add(stepThreeTitle);
		
		stepThreeATitle = new JLabel();
		stepThreeATitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		stepThreeATitle.setBounds(20, 165, 609, 16);
		add(stepThreeATitle);
		
		stepThreeBTitle = new JLabel();
		stepThreeBTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		stepThreeBTitle.setBounds(20, 192, 609, 16);
		add(stepThreeBTitle);
		
		refreshLanguage();
	}

	@Override
	public void updateOnLanguageChange() {
		refreshLanguage();
		
	}
	
	private void refreshLanguage() {
		ILanguage activeLanguage = appState.getActiveLanguage();
		
		helpTitle.setText(activeLanguage.helpTitle());
		stepOneTitle.setText(activeLanguage.helpStepOne());
		stepTwoTitle.setText(activeLanguage.helpStepTwo());
		stepThreeTitle.setText(activeLanguage.helpStepThree());
		stepThreeATitle.setText(activeLanguage.helpStepThreeA());
		stepThreeBTitle.setText(activeLanguage.helpStepThreeB());
		
	}

}
