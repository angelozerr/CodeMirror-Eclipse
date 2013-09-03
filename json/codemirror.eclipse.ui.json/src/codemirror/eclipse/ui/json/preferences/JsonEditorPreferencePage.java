package codemirror.eclipse.ui.json.preferences;

import org.eclipse.ui.IWorkbench;

import codemirror.eclipse.ui.json.internal.Messages;
import codemirror.eclipse.ui.preferences.CMEditorPreferencePage;

public class JsonEditorPreferencePage extends CMEditorPreferencePage {


	public void init(IWorkbench workbench) {
		setDescription(Messages.JsonEditorPreferencePage_description);
	}

}
