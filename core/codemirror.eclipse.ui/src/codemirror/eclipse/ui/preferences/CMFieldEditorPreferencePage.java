package codemirror.eclipse.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPreferencePage;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.builder.Mode;

public abstract class CMFieldEditorPreferencePage extends
		FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private final Mode mode;

	public CMFieldEditorPreferencePage(IPreferenceStore store, Mode mode) {
		super(GRID);
		super.setPreferenceStore(store);
		this.mode = mode;
	}

	@Override
	public boolean performOk() {
		boolean result = super.performOk();
		CMBuilder builder = CMBuilderRegistry.getInstance().getBuilder(mode);
		if (builder != null) {
			update(builder);
		}
		return result;
	}

	protected abstract void update(CMBuilder builder);
}
