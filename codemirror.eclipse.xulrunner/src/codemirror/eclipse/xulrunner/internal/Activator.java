package codemirror.eclipse.xulrunner.internal;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		// Try to configure SWT Browser with MOZILLA by setting the path of
		// XULRunner.
		Bundle bundle = bundleContext.getBundle();

		URL resourceUrl = bundle.getResource("xulrunner"); //$NON-NLS-1$
		if (resourceUrl != null) {
			try {
				URL fileUrl = FileLocator.toFileURL(resourceUrl);
				File file = new File(fileUrl.toURI());
				System.setProperty(
						"org.eclipse.swt.browser.XULRunnerPath", file.getAbsolutePath()); //$NON-NLS-1$
			} catch (IOException e) {
				// log the exception
			} catch (URISyntaxException e) {
				// log the exception
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
