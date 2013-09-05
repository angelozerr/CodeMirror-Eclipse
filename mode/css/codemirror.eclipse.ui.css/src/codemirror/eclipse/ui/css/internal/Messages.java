package codemirror.eclipse.ui.css.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "codemirror.eclipse.ui.css.internal.Messages";//$NON-NLS-1$

	// ------------- Preferences

	public static String CSSEditorPreferencePage_description;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
