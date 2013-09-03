package codemirror.eclipse.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPreferencePage;

public abstract class CMEditorPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	@Override
	protected Control createContents(Composite parent) {
		return new Composite(parent, SWT.NONE);
	}

}
