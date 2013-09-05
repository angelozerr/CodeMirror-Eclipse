package codemirror.eclipse.ui.css.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.swt.builder.addon.fold.FoldType;
import codemirror.eclipse.swt.css.builder.CSSMode;
import codemirror.eclipse.ui.css.internal.Activator;
import codemirror.eclipse.ui.preferences.CMPreferenceInitializer;
import codemirror.eclipse.ui.preferences.PreferenceHelper;

public class CSSEditorPreferenceInitializer extends CMPreferenceInitializer {

	public CSSEditorPreferenceInitializer() {
		this(Activator.getDefault().getPreferenceStore());
	}

	public CSSEditorPreferenceInitializer(IPreferenceStore store) {
		super(store, CSSMode.INSTANCE);
	}

	@Override
	protected void initializeDefaultPreferences(IPreferenceStore store) {

		// Folding
		PreferenceHelper.setDefaultFoldType(store, FoldType.BRACE_FOLD, true);
		PreferenceHelper.setDefaultFoldType(store, FoldType.COMMENT_FOLD, true);
		
		// Theme
		//PreferenceHelper.setDefaultTheme(store, Theme.ECLIPSE);

		// Hover
		//PreferenceHelper.setDefaultHover(store, true);

		// Mark Occurrences
		// PreferenceHelper.setDefaultMarkOccurrences(store,
		// ShowTokenType.VARIABLE, true);
		// PreferenceHelper.setDefaultMarkOccurrences(store,
		// ShowTokenType.VARIABLE_DEF, true);

	}

}
