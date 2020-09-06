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

public class ApplicationState implements ILanguageStateObservable, IActivePanelObservable{


	private static ApplicationState state;
	private ILanguageFactory languageFactory;
	private ILanguage appLanguage;
	private ArrayList<ILanguageStateObserver> languageObservers;
	private PanelType activePanelType;
	private ArrayList<IActivePanelObserver> activePanelObservers;
	
	private ApplicationState() {
		languageObservers = new ArrayList<ILanguageStateObserver>();
		activePanelObservers = new ArrayList<IActivePanelObserver>();
		languageFactory = new LanguageFactory();
		appLanguage = languageFactory.createLanguage(LanguageType.English);
	};
	
	public synchronized static ApplicationState instance() {
		if(state == null) {
			state = new ApplicationState();
		}
		
		return state;
	}
	
	public ILanguage getActiveLanguage() {
		return appLanguage;
	}
	
	public void changeApplicationLanguage(LanguageType language) {
		appLanguage = languageFactory.createLanguage(language);
		notifyStateChange();
	}
	
	@Override
	public void notifyStateChange() {
		for (ILanguageStateObserver observer : languageObservers) {
			observer.updateOnLanguageChange();
		}
		
	}

	@Override
	public void addLanguageObserver(ILanguageStateObserver observer) {
		languageObservers.add(observer);
		
	}

	@Override
	public void removeLanguageObserver(ILanguageStateObserver observer) {
		languageObservers.remove(observer);
		
	}

	@Override
	public void changeActivePanel(PanelType type) {
		
		activePanelType = type;
		for(IActivePanelObserver observer : activePanelObservers) {
			observer.updateActivePanel(type);
		}
	}

	@Override
	public void addPanelObserver(IActivePanelObserver observer) {
		activePanelObservers.add(observer);
		
	}
	
}
