package codemirror.eclipse.ui.sql.preferences;

import org.eclipse.ui.IWorkbench;

import codemirror.eclipse.ui.preferences.CMEditorPreferencePage;
import codemirror.eclipse.ui.sql.internal.Messages;

/**
 * SQL Editor preference page.
 * 
 */
public class SQLEditorPreferencePage extends CMEditorPreferencePage {

	public void init(IWorkbench workbench) {
		setDescription(Messages.SQLEditorPreferencePage_description);
	}

}
