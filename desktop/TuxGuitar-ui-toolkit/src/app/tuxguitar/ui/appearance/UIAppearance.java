package app.tuxguitar.ui.appearance;

import app.tuxguitar.ui.resource.UIColorModel;

public interface UIAppearance {

	UIColorModel getColorModel(UIColorAppearance color);

	default void setDarkMode(boolean darkMode) {
	}

	default boolean isDarkMode() {
		return false;
	}
}
