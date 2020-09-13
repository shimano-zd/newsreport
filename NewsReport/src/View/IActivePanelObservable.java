package View;

import Model.PanelType;

/**
 * The interface for the app's observable state. Used to notify observers of the changed active panel.
 * @author Sime
 *
 */
public interface IActivePanelObservable {

	void changeActivePanel(PanelType type);
	void addPanelObserver(IActivePanelObserver observer);
}
