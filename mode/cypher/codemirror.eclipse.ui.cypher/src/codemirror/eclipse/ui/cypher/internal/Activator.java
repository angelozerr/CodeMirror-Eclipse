package codemirror.eclipse.ui.cypher.internal;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.builder.CMRunModeBuilderRegistry;
import codemirror.eclipse.swt.cypher.builder.CMCypherBuilder;
import codemirror.eclipse.swt.cypher.builder.CMCypherRunModeBuilder;
import codemirror.eclipse.ui.cypher.internal.Activator;
import codemirror.eclipse.ui.resources.CMResourcesRegistry;

public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "codemirror.eclipse.ui.cypher"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		CMBuilderRegistry.getInstance().register(
				new CMCypherBuilder(CMResourcesRegistry.getRegistry()
						.getURL("")));
		CMRunModeBuilderRegistry.getInstance().register(
				new CMCypherRunModeBuilder(CMResourcesRegistry.getRegistry()
						.getURL("")));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 *
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
