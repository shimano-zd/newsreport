package View;

import Model.PanelType;

public interface IActivePanelObserver {
	
	void updateActivePanel(PanelType type);
}
