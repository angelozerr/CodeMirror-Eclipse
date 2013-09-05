package codemirror.eclipse.ui.css.preferences;

import codemirror.eclipse.swt.css.builder.CSSMode;
import codemirror.eclipse.ui.css.internal.Activator;
import codemirror.eclipse.ui.preferences.FoldingPreferencePage;

public class CSSFoldingPreferencePage extends FoldingPreferencePage {

	public CSSFoldingPreferencePage() {
		super(Activator.getDefault().getPreferenceStore(), CSSMode.INSTANCE);
	}
}
