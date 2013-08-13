package org.eclipse.swt.browser;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

/**
 * 
 * @author azerr
 * @see http://www.eclipse.org/swt/faq.php#specifyxulrunner
 */
public class XULRunnerInitializer {

	private static final String XULRUNNER_BASENAME = "codemirror.eclipse.xulrunner";
	private static final String XULRUNNER_PATH = "org.eclipse.swt.browser.XULRunnerPath"; //$NON-NLS-1$
	private static final String XULRUNNER_ENTRY = "/xulrunner"; //$NON-NLS-1$

	static {
		initializeXULRunner();
	}

	private static void initializeXULRunner() {
		// is XULRunnerPath is setted with systemproperty
		String xulrunnerPath = System.getProperty(XULRUNNER_PATH);
		if (xulrunnerPath != null) {
			File xulrunnerFile = new File(xulrunnerPath);
			if (!xulrunnerFile.exists()) {
				xulrunnerPath = null;
			}
		}
		if (xulrunnerPath == null) {
			String XULRUNNER_BUNDLE = (new StringBuilder(XULRUNNER_BASENAME))
					//$NON-NLS-1$
					.append(".") //$NON-NLS-1$
					.append(Platform.getWS())
					.append(".") //$NON-NLS-1$
					.append(Platform.getOS())
					.append(Platform.OS_MACOSX.equals(Platform.getOS()) ? "" : (new StringBuilder(".")).append(Platform.getOSArch()).toString()) //$NON-NLS-1$ //$NON-NLS-2$
					.toString();
			Bundle xulRunnerBundle = Platform.getBundle(XULRUNNER_BUNDLE);
			if (xulRunnerBundle == null) {
				System.out.println(NLS.bind(
						Messages.XULRunnerInitializer_Bundle_is_not_found,
						XULRUNNER_BUNDLE));
			} else {
				URL url = xulRunnerBundle.getEntry(XULRUNNER_ENTRY);
				if (url == null) {
					System.out
							.println(NLS
									.bind(Messages.XULRunnerInitializer_Bundle_doesnt_contain,
											new Object[] { XULRUNNER_BUNDLE,
													XULRUNNER_ENTRY }));
				} else {
					File xulrunnerFile;
					try {
						URL url1 = FileLocator.resolve(url);
						xulrunnerFile = new File(FileLocator.toFileURL(url1)
								.getFile());
						xulrunnerPath = xulrunnerFile.getAbsolutePath();
						System.setProperty(XULRUNNER_PATH, xulrunnerPath);
					} catch (IOException ioe) {
						System.out
								.println(NLS
										.bind(Messages.XULRunnerInitializer_Cannot_get_path_to_XULRunner_from_bundle,
												XULRUNNER_BUNDLE));
						ioe.printStackTrace();
					}
				}
			}
		}
	}
}
