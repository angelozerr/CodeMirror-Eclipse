package codemirror.eclipse.ui.sql.preferences;

import codemirror.eclipse.swt.sql.builder.SQLMode;
import codemirror.eclipse.ui.preferences.FoldingPreferencePage;
import codemirror.eclipse.ui.sql.internal.Activator;

public class SQLFoldingPreferencePage extends FoldingPreferencePage {

	public SQLFoldingPreferencePage() {
		super(Activator.getDefault().getPreferenceStore(), SQLMode.INSTANCE);
	}
}
