package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import Controller.ApplicationState;
import Model.ILanguage;
import Model.PanelType;

/**
 * The panel used to switch between different active panels on the main window.
 * @author Sime
 *
 */
public class MenuPanel extends JPanel implements ILanguageStateObserver {

	private JButton scrapingButton;
	private JButton analysisButton;
	private JButton settingsButton;
	private JButton exitButton;
	private JButton helpButton;
	private ApplicationState appState;

	public MenuPanel() {

		setLayout(null);
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.lightGray));
		setBackground(Color.WHITE);
		setBounds(20, 49, 132, 279);
		setLocation(20, 348);

		appState = ApplicationState.instance();
		appState.addLanguageObserver(this);

		createComponents();
		activateComponents();
	}

	private void createComponents() {

		scrapingButton = new JButton();
		scrapingButton.setBackground(Color.WHITE);
		scrapingButton.setBounds(10, 11, 110, 23);
		add(scrapingButton);

		analysisButton = new JButton();
		analysisButton.setBackground(Color.WHITE);
		analysisButton.setBounds(10, 45, 110, 23);
		add(analysisButton);

		settingsButton = new JButton();
		settingsButton.setBackground(Color.WHITE);
		settingsButton.setBounds(10, 79, 110, 23);
		add(settingsButton);

		exitButton = new JButton();
		exitButton.setBackground(Color.WHITE);
		exitButton.setBounds(10, 148, 110, 23);
		add(exitButton);

		helpButton = new JButton();
		helpButton.setBackground(Color.WHITE);
		helpButton.setBounds(10, 113, 110, 23);
		add(helpButton);

		refreshLanguage();
	}

	private void activateComponents() {
		scrapingButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				appState.changeActivePanel(PanelType.Scraping);

			}
		});

		analysisButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				appState.changeActivePanel(PanelType.Analysis);

			}
		});

		helpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				appState.changeActivePanel(PanelType.Help);

			}
		});

		settingsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				appState.changeActivePanel(PanelType.Settings);

			}
		});

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(null, appState.getActiveLanguage().confirmExit(), "",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					System.exit(0);

			}
		});
	}

	/**
	 * Update the panel's language content in response to the app's state.
	 */
	@Override
	public void updateOnLanguageChange() {
		refreshLanguage();
	}

	private void refreshLanguage() {

		ILanguage activeLanguage = appState.getActiveLanguage();
		scrapingButton.setText(activeLanguage.scrapeMenuTitle());
		settingsButton.setText(activeLanguage.settingsMenuTitle());
		helpButton.setText(activeLanguage.helpMenuTitle());
		exitButton.setText(activeLanguage.exitMenuTitle());
		analysisButton.setText(activeLanguage.analysisMenuTitle());
	}

}
