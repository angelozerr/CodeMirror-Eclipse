package codemirror.eclipse.ui.xquery.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.swt.builder.addon.fold.FoldType;
import codemirror.eclipse.swt.builder.addon.search.ShowTokenType;
import codemirror.eclipse.swt.xquery.builder.XQueryMode;
import codemirror.eclipse.ui.preferences.CMPreferenceInitializer;
import codemirror.eclipse.ui.preferences.PreferenceHelper;

public class XQueryPreferenceInitializer extends CMPreferenceInitializer {

	public XQueryPreferenceInitializer(IPreferenceStore store) {
		super(store, XQueryMode.INSTANCE);
	}

	@Override
	protected void initializeDefaultPreferences(IPreferenceStore store) {

		// Folding
		PreferenceHelper.setDefaultFoldType(store, FoldType.BRACE_FOLD, true);
		PreferenceHelper.setDefaultFoldType(store, FoldType.COMMENT_FOLD, true);
		PreferenceHelper.setDefaultFoldType(store, FoldType.XML_FOLD, true);

		// Theme
		PreferenceHelper.setDefaultTheme(store, Theme.XQ_LIGHT);

		// Hover
		PreferenceHelper.setDefaultHoverEnabled(store, true);
		PreferenceHelper.setDefaultHoverDelay(store, 500);

		// Mark Occurrences
//		PreferenceHelper.setDefaultMarkOccurrences(store,
//				ShowTokenType.VARIABLE, true);
//		PreferenceHelper.setDefaultMarkOccurrences(store,
//				ShowTokenType.VARIABLE_DEF, true);

	}

}
