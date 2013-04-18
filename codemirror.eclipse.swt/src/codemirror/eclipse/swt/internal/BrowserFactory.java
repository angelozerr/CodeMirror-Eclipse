package codemirror.eclipse.swt.internal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

public class BrowserFactory {

	private static Boolean useMozilla = null;

	public static Browser create(Composite parent, int style) {
		if (useMozilla == null) {
			try {
				// Try to use Mozilla because performance are better than IE.
				Browser browser = new Browser(parent, style | SWT.MOZILLA);
				useMozilla = true;
				return browser;
			} catch (Throwable e) {
				useMozilla = false;
			}
		}
		if (useMozilla) {
			// Use FF
			return new Browser(parent, style | SWT.MOZILLA);
		}
		// Use IE
		return new Browser(parent, style);
	}
}
