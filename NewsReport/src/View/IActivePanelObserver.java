package View;

import Model.PanelType;

/**
 * The interface used by any subscriber to the app's observable panel state.
 * @author Sime
 *
 */
public interface IActivePanelObserver {
	
	void updateActivePanel(PanelType type);
}
