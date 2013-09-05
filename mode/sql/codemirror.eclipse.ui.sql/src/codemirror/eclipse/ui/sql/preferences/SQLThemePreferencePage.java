package codemirror.eclipse.ui.sql.preferences;

import codemirror.eclipse.swt.sql.builder.SQLMode;
import codemirror.eclipse.ui.preferences.ThemePreferencePage;
import codemirror.eclipse.ui.sql.internal.Activator;

public class SQLThemePreferencePage extends ThemePreferencePage {

	public SQLThemePreferencePage() {
		super(Activator.getDefault().getPreferenceStore(), SQLMode.INSTANCE);
	}
}
