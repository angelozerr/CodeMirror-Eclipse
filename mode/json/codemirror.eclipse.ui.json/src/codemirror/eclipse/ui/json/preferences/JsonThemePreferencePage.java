package codemirror.eclipse.ui.json.preferences;

import codemirror.eclipse.swt.json.builder.JsonMode;
import codemirror.eclipse.ui.json.internal.Activator;
import codemirror.eclipse.ui.preferences.ThemePreferencePage;

public class JsonThemePreferencePage extends ThemePreferencePage {

	public JsonThemePreferencePage() {
		super(Activator.getDefault().getPreferenceStore(), JsonMode.INSTANCE);
	}
}
