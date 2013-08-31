package codemirror.eclipse.ui.preferences;

import java.util.Collection;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Mode;
import codemirror.eclipse.swt.builder.addon.search.ShowTokenType;
import codemirror.eclipse.ui.internal.Messages;

public class MarkOccurrencesPreferencePage extends CMFieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	private final Collection<ShowTokenType> showTokenTypes;

	public MarkOccurrencesPreferencePage(IPreferenceStore store, Mode mode) {
		super(store, mode);
		this.showTokenTypes = ShowTokenType.getAll();
	}

	@Override
	protected void createFieldEditors() {
		for (ShowTokenType showTokenType : showTokenTypes) {

			BooleanFieldEditor contentAssistCacheFieldEditor = new BooleanFieldEditor(
					showTokenType.getType(), showTokenType.getType(),
					getFieldEditorParent());
			addField(contentAssistCacheFieldEditor);
		}
	}

	public void init(IWorkbench workbench) {
		setDescription(Messages.MarkOccurrencesPreferencePage_description);
	}

	@Override
	protected void update(CMBuilder builder) {
		PreferenceHelper.updateMarkOccurrences(builder, getPreferenceStore());
	}
}
