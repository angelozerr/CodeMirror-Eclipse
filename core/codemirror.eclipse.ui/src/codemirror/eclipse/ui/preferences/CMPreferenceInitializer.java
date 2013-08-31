package codemirror.eclipse.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.builder.Mode;

public abstract class CMPreferenceInitializer extends
		AbstractPreferenceInitializer {

	private final IPreferenceStore store;
	private final CMBuilder builder;

	public CMPreferenceInitializer(IPreferenceStore store, Mode mode) {
		this(store, CMBuilderRegistry.getInstance().getBuilder(mode));
	}

	public CMPreferenceInitializer(IPreferenceStore store, CMBuilder builder) {
		this.store = store;
		this.builder = builder;
	}

	@Override
	public void initializeDefaultPreferences() {
		initializeDefaultPreferences(store);
		PreferenceHelper.initialize(builder, store);
	}

	protected abstract void initializeDefaultPreferences(IPreferenceStore store);

}
