package codemirror.eclipse.ui.cypher.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.swt.cypher.builder.CypherMode;
import codemirror.eclipse.ui.cypher.internal.Activator;
import codemirror.eclipse.ui.preferences.CMPreferenceInitializer;
import codemirror.eclipse.ui.preferences.PreferenceHelper;

public class CypherEditorPreferenceInitializer extends CMPreferenceInitializer {

	public CypherEditorPreferenceInitializer() {
		this(Activator.getDefault().getPreferenceStore());
	}

	public CypherEditorPreferenceInitializer(IPreferenceStore store) {
		super(store, CypherMode.INSTANCE);
	}

	@Override
	protected void initializeDefaultPreferences(IPreferenceStore store) {

		// Theme
		PreferenceHelper.setDefaultTheme(store, Theme.NEO);

	}

}
