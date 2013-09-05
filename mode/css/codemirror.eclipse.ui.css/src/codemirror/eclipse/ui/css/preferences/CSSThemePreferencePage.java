package codemirror.eclipse.ui.css.preferences;

import codemirror.eclipse.swt.css.builder.CSSMode;
import codemirror.eclipse.ui.css.internal.Activator;
import codemirror.eclipse.ui.preferences.ThemePreferencePage;

public class CSSThemePreferencePage extends ThemePreferencePage {

	public CSSThemePreferencePage() {
		super(Activator.getDefault().getPreferenceStore(), CSSMode.INSTANCE);
	}
}
