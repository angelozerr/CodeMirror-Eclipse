package codemirror.eclipse.ui.cypher.preferences;

import org.eclipse.ui.IWorkbench;

import codemirror.eclipse.ui.cypher.internal.Messages;
import codemirror.eclipse.ui.preferences.CMEditorPreferencePage;

public class CypherEditorPreferencePage extends CMEditorPreferencePage {

	public void init(IWorkbench workbench) {
		setDescription(Messages.CypherEditorPreferencePage_description);
	}

}
