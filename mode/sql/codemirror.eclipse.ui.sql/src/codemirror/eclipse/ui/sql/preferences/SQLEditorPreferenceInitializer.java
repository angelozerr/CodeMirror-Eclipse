package codemirror.eclipse.ui.sql.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import codemirror.eclipse.swt.builder.addon.fold.FoldType;
import codemirror.eclipse.swt.sql.builder.SQLMode;
import codemirror.eclipse.ui.preferences.CMPreferenceInitializer;
import codemirror.eclipse.ui.preferences.PreferenceHelper;
import codemirror.eclipse.ui.sql.internal.Activator;

public class SQLEditorPreferenceInitializer extends CMPreferenceInitializer {

	public SQLEditorPreferenceInitializer() {
		this(Activator.getDefault().getPreferenceStore());
	}

	public SQLEditorPreferenceInitializer(IPreferenceStore store) {
		super(store, SQLMode.INSTANCE);
	}

	@Override
	protected void initializeDefaultPreferences(IPreferenceStore store) {

		// Folding
		//PreferenceHelper.setDefaultFoldType(store, FoldType.BRACE_FOLD, true);

		// Theme
		// PreferenceHelper.setDefaultTheme(store, Theme.ECLIPSE);

		// Hover
		// PreferenceHelper.setDefaultHover(store, true);

		// Mark Occurrences
		// PreferenceHelper.setDefaultMarkOccurrences(store,
		// ShowTokenType.VARIABLE, true);
		// PreferenceHelper.setDefaultMarkOccurrences(store,
		// ShowTokenType.VARIABLE_DEF, true);

	}

}
