package View;

import Model.PanelType;

public interface IActivePanelObservable {

	void changeActivePanel(PanelType type);
	void addPanelObserver(IActivePanelObserver observer);
}
