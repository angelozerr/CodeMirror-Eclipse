package codemirror.eclipse.ui.preferences;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.preference.IPreferenceStore;

import codemirror.eclipse.swt.BrowserFactory;
import codemirror.eclipse.swt.WebBrowserType;
import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.builder.Mode;
import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.swt.builder.addon.fold.FoldType;

public class PreferenceHelper {

	public static final String THEME_PREFERENCE_NAME = "theme";
	public static final String HOVER_ENABLED_PREFERENCE_NAME = "hoverEnabled";
	public static final String BROWSER_PREFERENCE_NAME = "browser";

	public static void initialize(Mode mode, IPreferenceStore store) {
		CMBuilder builder = CMBuilderRegistry.getInstance().getBuilder(mode);
		initialize(builder, store);
	}

	public static void initialize(CMBuilder builder, IPreferenceStore store) {
		// Initialize the theme builder from the store
		updateTheme(builder, store);
		// Initialize the fold builder from the store
		updateFold(builder, store);
		// Initialize the hover builder from the store
		updateHover(builder, store);
		// Initialize browser type
		updateDefaultBrowserType(store);
	}

	// ------------------------ Theme

	/**
	 * Set the given theme as default in the store.
	 * 
	 * @param store
	 *            the preference store to update.
	 * @param theme
	 *            the default theme to use.
	 */
	public static void setDefaultTheme(IPreferenceStore store, Theme theme) {
		store.setDefault(THEME_PREFERENCE_NAME, theme.getName());
	}

	/**
	 * Get the theme from the preference store.
	 * 
	 * @param store
	 *            the preference store
	 * @return
	 */
	public static Theme getTheme(IPreferenceStore store) {
		String name = store.getString(THEME_PREFERENCE_NAME);
		if (name != null) {
			return Theme.getTheme(name);
		}
		return null;
	}

	/**
	 * Update the builder with the theme defined in the given store.
	 * 
	 * @param builder
	 * @param store
	 */
	public static void updateTheme(CMBuilder builder, IPreferenceStore store) {
		builder.getOptions().setTheme(getTheme(store));
	}

	// ------------------------ Theme

	/**
	 * 
	 * @param store
	 * @param enabled
	 */
	public static void setDefaultFoldType(IPreferenceStore store,
			FoldType foldType, boolean enabled) {
		store.setDefault(foldType.getName(), enabled);
	}

	public static void updateFold(CMBuilder builder, IPreferenceStore store) {
		Collection<FoldType> types = new ArrayList<FoldType>();
		FoldType[] supportedFoldTypes = builder.getSupportedFoldTypes();
		if (supportedFoldTypes != null) {
			for (FoldType foldType : supportedFoldTypes) {
				if (store.getBoolean(foldType.getName())) {
					types.add(foldType);
				}
			}
		}
		builder.getOptions().getFoldGutter()
				.setRangeFinder(types.toArray(FoldType.EMPTY));
	}

	// ------------------------ Hover

	/**
	 * 
	 * @param store
	 * @param enabled
	 */
	public static void setDefaultHover(IPreferenceStore store, boolean enabled) {
		store.setDefault(PreferenceHelper.HOVER_ENABLED_PREFERENCE_NAME,
				enabled);
	}

	public static void updateHover(CMBuilder builder, IPreferenceStore store) {
		boolean textHover = store
				.getBoolean(PreferenceHelper.HOVER_ENABLED_PREFERENCE_NAME);
		builder.getOptions().getTextHover(null).setTextHover(textHover);
	}

	// ------------------------ Web Browser type

	public static void updateDefaultBrowserType(IPreferenceStore store) {
		WebBrowserType browser = getWebBrowserType(store);
		if (browser != null) {
			BrowserFactory.setDefaultBrowserStyle(browser.getStyle());
		}
	}

	public static WebBrowserType getWebBrowserType(IPreferenceStore store) {
		String name = store.getString(BROWSER_PREFERENCE_NAME);
		if (name != null) {
			return WebBrowserType.getWebBrowserType(name);
		}
		return null;
	}

	public static void setDefaultWebBrowserType(IPreferenceStore store,
			WebBrowserType browserType) {
		store.setDefault(BROWSER_PREFERENCE_NAME, browserType.name());
	}
}
