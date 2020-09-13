package Controller;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Model.ILanguage;
import Model.ILanguageFactory;
import Model.LanguageFactory;
import Model.LanguageType;
import Model.PanelType;
import View.IActivePanelObservable;
import View.IActivePanelObserver;
import View.ILanguageStateObservable;
import View.ILanguageStateObserver;

/**
 * Class responsible for holding the application state. The class is instantiated as a singleton and holds the app's language and active panel.
 * @author Sime
 *
 */
public class ApplicationState implements ILanguageStateObservable, IActivePanelObservable {

	private static ApplicationState state;
	private ILanguageFactory languageFactory;
	private ILanguage appLanguage;
	private ArrayList<ILanguageStateObserver> languageObservers;
	private ArrayList<IActivePanelObserver> activePanelObservers;

	private ApplicationState() {
		languageObservers = new ArrayList<ILanguageStateObserver>();
		activePanelObservers = new ArrayList<IActivePanelObserver>();
		languageFactory = new LanguageFactory();
		appLanguage = languageFactory.createLanguage(LanguageType.English);
	};

	public synchronized static ApplicationState instance() {
		if (state == null) {
			state = new ApplicationState();
		}

		return state;
	}

	public ILanguage getActiveLanguage() {
		return appLanguage;
	}

	/**
	 * Exposed method for changing the application language from any observer.
	 * @param language Language type that the application should use.
	 */
	public void changeApplicationLanguage(LanguageType language) {
		appLanguage = languageFactory.createLanguage(language);
		notifyLanguageChange();
	}

	/**
	 * Notifies any subscribed observer about the app's state change.
	 */
	@Override
	public void notifyLanguageChange() {
		for (ILanguageStateObserver observer : languageObservers) {
			observer.updateOnLanguageChange();
		}

	}

	/**
	 * Used to add language observers.
	 */
	@Override
	public void addLanguageObserver(ILanguageStateObserver observer) {
		languageObservers.add(observer);

	}

	@Override
	public void removeLanguageObserver(ILanguageStateObserver observer) {
		languageObservers.remove(observer);

	}

	/**
	 * Method that should be called from observers to change the app's active panel.
	 */
	@Override
	public void changeActivePanel(PanelType type) {

		for (IActivePanelObserver observer : activePanelObservers) {
			observer.updateActivePanel(type);
		}
	}

	/**
	 * Used to add panel observers.
	 */
	@Override
	public void addPanelObserver(IActivePanelObserver observer) {
		activePanelObservers.add(observer);

	}

}
