package View;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import Controller.ApplicationState;
import Model.LanguageType;
import java.awt.Font;

/**
 * The panel used to change the application's language state.
 * @author Sime
 *
 */
public class SettingsPanel extends JPanel implements ILanguageStateObserver {

	private JLabel englishIcon;
	private JLabel croatianIcon;
	private ApplicationState appState;
	private JLabel settingsTitle;
	private JLabel settingsSubtitle;

	public SettingsPanel() {

		setLayout(null);
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.lightGray));
		setBackground(Color.WHITE);
		setBounds(20, 49, 639, 423);

		appState = ApplicationState.instance();
		appState.addLanguageObserver(this);

		createComponents();
		activateComponents();
	}

	private void createComponents() {

		englishIcon = new JLabel("");
		englishIcon.setIcon(new ImageIcon(getClass().getResource("/images/icons8-great-britain-40.png")));
		englishIcon.setBounds(10, 88, 42, 30);
		englishIcon.setEnabled(false);
		add(englishIcon);

		croatianIcon = new JLabel("");
		croatianIcon.setIcon(new ImageIcon(getClass().getResource("/images/icons8-croatia-40.png")));
		croatianIcon.setBounds(62, 88, 42, 30);
		add(croatianIcon);

		settingsTitle = new JLabel("");
		settingsTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
		settingsTitle.setBounds(10, 11, 211, 22);
		add(settingsTitle);

		settingsSubtitle = new JLabel("");
		settingsSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		settingsSubtitle.setBounds(10, 44, 590, 20);
		add(settingsSubtitle);

		refreshLanguage();
	}

	private void activateComponents() {

		englishIcon.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				englishIcon.setBorder(null);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				englishIcon.setBorder(BorderFactory.createRaisedBevelBorder());

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				appState.changeApplicationLanguage(LanguageType.English);
				englishIcon.setEnabled(false);
				croatianIcon.setEnabled(true);
				refreshLanguage();

			}
		});

		croatianIcon.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				croatianIcon.setBorder(null);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				croatianIcon.setBorder(BorderFactory.createRaisedBevelBorder());

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				appState.changeApplicationLanguage(LanguageType.Croatian);
				englishIcon.setEnabled(true);
				croatianIcon.setEnabled(false);
				refreshLanguage();

			}
		});

	}

	
	private void refreshLanguage() {
		settingsTitle.setText(appState.getActiveLanguage().applicationLanguageTitle());
		settingsSubtitle.setText(appState.getActiveLanguage().applicationLanguageSubtitle());

	}
	
	/**
	 * Update the panel's own language in response to app's state change.
	 */
	@Override
	public void updateOnLanguageChange() {

		refreshLanguage();

	}
}
