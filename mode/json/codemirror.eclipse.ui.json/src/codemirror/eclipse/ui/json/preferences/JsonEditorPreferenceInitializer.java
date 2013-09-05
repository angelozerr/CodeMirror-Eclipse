package codemirror.eclipse.ui.json.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.swt.builder.addon.fold.FoldType;
import codemirror.eclipse.swt.json.builder.JsonMode;
import codemirror.eclipse.ui.json.internal.Activator;
import codemirror.eclipse.ui.preferences.CMPreferenceInitializer;
import codemirror.eclipse.ui.preferences.PreferenceHelper;

public class JsonEditorPreferenceInitializer extends CMPreferenceInitializer {

	public JsonEditorPreferenceInitializer() {
		this(Activator.getDefault().getPreferenceStore());
	}

	public JsonEditorPreferenceInitializer(IPreferenceStore store) {
		super(store, JsonMode.INSTANCE);
	}

	@Override
	protected void initializeDefaultPreferences(IPreferenceStore store) {

		// Folding
		PreferenceHelper.setDefaultFoldType(store, FoldType.BRACE_FOLD, true);

		// Theme
		PreferenceHelper.setDefaultTheme(store, Theme.ECLIPSE);

		// Hover
		//PreferenceHelper.setDefaultHover(store, true);

		// Mark Occurrences
		// PreferenceHelper.setDefaultMarkOccurrences(store,
		// ShowTokenType.VARIABLE, true);
		// PreferenceHelper.setDefaultMarkOccurrences(store,
		// ShowTokenType.VARIABLE_DEF, true);

	}

}
