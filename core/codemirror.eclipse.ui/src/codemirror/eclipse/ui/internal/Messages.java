package codemirror.eclipse.ui.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "codemirror.eclipse.ui.internal.Messages";//$NON-NLS-1$

	public static String saveErrorTitle;
	public static String saveErrorMessage;

	// ------------- Preferences 
	public static String CodeMirrorPreferences_description;
	
	public static String ThemePreferencePage_description;
	public static String ThemePreferencePage_theme_label;

	public static String HoversPreferencePage_description;
	public static String FoldingPreferencePage_description;

	public static String HoversPreferencePage_hoverEnabled_label;
	
	public static String MarkOccurrencesPreferencePage_description;
	
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
