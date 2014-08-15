package codemirror.eclipse.ui.cypher.preferences;

import codemirror.eclipse.swt.cypher.builder.CypherMode;
import codemirror.eclipse.ui.cypher.internal.Activator;
import codemirror.eclipse.ui.preferences.ThemePreferencePage;

public class CypherThemePreferencePage extends ThemePreferencePage {

	public CypherThemePreferencePage() {
		super(Activator.getDefault().getPreferenceStore(), CypherMode.INSTANCE);
	}

}
