package codemirror.eclipse.ui.css.preferences;

import org.eclipse.ui.IWorkbench;

import codemirror.eclipse.ui.css.internal.Messages;
import codemirror.eclipse.ui.preferences.CMEditorPreferencePage;

public class CSSEditorPreferencePage extends CMEditorPreferencePage {

	public void init(IWorkbench workbench) {
		setDescription(Messages.CSSEditorPreferencePage_description);
	}

}
