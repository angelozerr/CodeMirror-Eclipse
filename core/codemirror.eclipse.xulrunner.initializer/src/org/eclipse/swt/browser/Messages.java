package org.eclipse.swt.browser;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.swt.browser.messages"; //$NON-NLS-1$
	public static String XULRunnerInitializer_Bundle_doesnt_contain;
	public static String XULRunnerInitializer_Bundle_is_not_found;
	public static String XULRunnerInitializer_Cannot_get_path_to_XULRunner_from_bundle;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}