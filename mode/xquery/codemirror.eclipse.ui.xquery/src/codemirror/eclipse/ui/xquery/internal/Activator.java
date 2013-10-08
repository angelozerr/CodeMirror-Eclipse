/*******************************************************************************
 * Copyright (c) 2013 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package codemirror.eclipse.ui.xquery.internal;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.builder.CMRunModeBuilder;
import codemirror.eclipse.swt.builder.CMRunModeBuilderRegistry;
import codemirror.eclipse.swt.xquery.builder.CMXQueryBuilder;
import codemirror.eclipse.swt.xquery.builder.CMXQueryRunModeBuilder;
import codemirror.eclipse.swt.xquery.builder.XQueryMode;
import codemirror.eclipse.swt.xquery.builder.XmlMode;
import codemirror.eclipse.ui.resources.CMResourcesRegistry;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "codemirror.eclipse.ui.xquery"; //$NON-NLS-1$

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

		String baseURL = CMResourcesRegistry.getRegistry().getURL("");
		CMBuilderRegistry registry = CMBuilderRegistry.getInstance();
		CMRunModeBuilderRegistry runModeRegistry = CMRunModeBuilderRegistry
				.getInstance();
		// XQuery
		if (registry.getBuilder(XQueryMode.INSTANCE) == null) {
			registry.register(new CMXQueryBuilder(baseURL));
		}
		if (runModeRegistry.getBuilder(XQueryMode.INSTANCE) == null) {
			runModeRegistry.register(new CMXQueryRunModeBuilder(baseURL));
		}
		
		// XML
		if (registry.getBuilder(XmlMode.INSTANCE) == null) {
			registry.register(new CMBuilder(XmlMode.INSTANCE,baseURL));
		}
		if (runModeRegistry.getBuilder(XmlMode.INSTANCE) == null) {
			runModeRegistry.register(new CMRunModeBuilder(XmlMode.INSTANCE,
					baseURL));
		}
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
