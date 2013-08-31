package codemirror.eclipse.ui.preferences;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Mode;
import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.ui.internal.Messages;

public class ThemePreferencePage extends CMFieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private static final String[][] allThemes;

	static {

		allThemes = new String[Theme.getAll().size()][2];
		int i = 0;
		for (Theme theme : Theme.getAll()) {
			allThemes[i][0] = theme.getName();
			allThemes[i][1] = theme.getName();
			i++;
		}
	}

	private ComboFieldEditor themeField;

	public ThemePreferencePage(IPreferenceStore store, Mode mode) {
		super(store, mode);
		super.setPreferenceStore(store);
	}

	@Override
	protected void createFieldEditors() {

		themeField = new ComboFieldEditor(
				PreferenceHelper.THEME_PREFERENCE_NAME,
				Messages.ThemePreferencePage_theme_label, allThemes,
				getFieldEditorParent());
		addField(themeField);
	}

	public void init(IWorkbench workbench) {
		setDescription(Messages.ThemePreferencePage_description);
	}

	@Override
	protected void update(CMBuilder builder) {
		PreferenceHelper.updateTheme(builder, getPreferenceStore());
	}

}
