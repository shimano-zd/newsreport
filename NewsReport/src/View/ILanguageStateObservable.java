package View;

public interface ILanguageStateObservable {

	void notifyStateChange();
	void addLanguageObserver(ILanguageStateObserver observer);
	void removeLanguageObserver(ILanguageStateObserver observer);
}
