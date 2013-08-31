package codemirror.eclipse.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.builder.Mode;
import codemirror.eclipse.swt.builder.addon.fold.FoldType;
import codemirror.eclipse.ui.internal.Messages;

public class FoldingPreferencePage extends CMFieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	private final FoldType[] foldTypes;

	public FoldingPreferencePage(IPreferenceStore store, Mode mode) {
		super(store, mode);
		this.foldTypes = CMBuilderRegistry.getInstance().getBuilder(mode)
				.getSupportedFoldTypes();
	}

	@Override
	protected void createFieldEditors() {
		for (FoldType foldType : foldTypes) {

			BooleanFieldEditor contentAssistCacheFieldEditor = new BooleanFieldEditor(
					foldType.getName(), foldType.getName(),
					getFieldEditorParent());
			addField(contentAssistCacheFieldEditor);
		}
	}

	public void init(IWorkbench workbench) {
		setDescription(Messages.FoldingPreferencePage_description);
	}

	@Override
	protected void update(CMBuilder builder) {
		PreferenceHelper.updateFold(builder, getPreferenceStore());
	}
}
