package codemirror.eclipse.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Mode;
import codemirror.eclipse.ui.internal.Messages;

public class HoversPreferencePage extends CMFieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public HoversPreferencePage(IPreferenceStore store, Mode mode) {
		super(store, mode);
		super.setPreferenceStore(store);
	}

	@Override
	protected void createFieldEditors() {

		BooleanFieldEditor hoverEnabledFieldEditor = new BooleanFieldEditor(
				PreferenceHelper.HOVER_ENABLED_PREFERENCE_NAME,
				Messages.HoversPreferencePage_hoverEnabled_label,
				getFieldEditorParent());
		addField(hoverEnabledFieldEditor);

		IntegerFieldEditor hoverDelayFieldEditor = new IntegerFieldEditor(
				PreferenceHelper.HOVER_DELAY_PREFERENCE_NAME,
				Messages.HoversPreferencePage_hoverDelay_label,
				getFieldEditorParent());
		addField(hoverDelayFieldEditor);
	}

	public void init(IWorkbench workbench) {
		setDescription(Messages.HoversPreferencePage_description);
	}

	@Override
	protected void update(CMBuilder builder) {
		PreferenceHelper.updateHover(builder, getPreferenceStore());

	}
}
