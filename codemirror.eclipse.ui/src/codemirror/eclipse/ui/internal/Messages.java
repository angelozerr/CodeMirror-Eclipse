package codemirror.eclipse.ui.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "codemirror.eclipse.ui.internal.Messages";//$NON-NLS-1$

	public static String saveErrorTitle;
	public static String saveErrorMessage;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
