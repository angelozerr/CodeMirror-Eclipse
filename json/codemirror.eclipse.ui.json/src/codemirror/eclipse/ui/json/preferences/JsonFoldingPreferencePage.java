package codemirror.eclipse.ui.json.preferences;

import codemirror.eclipse.swt.json.builder.JsonMode;
import codemirror.eclipse.ui.json.internal.Activator;
import codemirror.eclipse.ui.preferences.FoldingPreferencePage;

public class JsonFoldingPreferencePage extends FoldingPreferencePage {

	public JsonFoldingPreferencePage() {
		super(Activator.getDefault().getPreferenceStore(), JsonMode.INSTANCE);
	}
}
