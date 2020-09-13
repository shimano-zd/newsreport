package View;

/**
 * The interface for the app's observable state. Used to notify observers of the changed language.
 * @author Sime
 *
 */
public interface ILanguageStateObservable {

	void notifyLanguageChange();
	void addLanguageObserver(ILanguageStateObserver observer);
	void removeLanguageObserver(ILanguageStateObserver observer);
}
