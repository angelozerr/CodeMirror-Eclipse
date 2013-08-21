/*******************************************************************************
 * Copyright (c) 2011 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package codemirror.eclipse.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

/**
 * SWT {@link Browser} factory.
 * 
 */
public class BrowserFactory {

	private static int defaultBrowserStyle = SWT.NONE;

	public static void setDefaultBrowserStyle(int defaultBrowserStyle) {
		BrowserFactory.defaultBrowserStyle = defaultBrowserStyle;
	}

	public static int getDefaultBrowserStyle() {
		return defaultBrowserStyle;
	}

	public static Browser create(Composite parent, Integer style) {
		if (style == null) {
			style = getDefaultBrowserStyle();
		}
		// RAP doesn't support MOZILLA
		/*
		 * if (!SingleSourcingHelper.isRAP()) { if (useMozilla == null) { try {
		 * // Try to use Mozilla because performance are better than // IE.
		 * Browser browser = new Browser(parent, style | SWT.MOZILLA);
		 * useMozilla = true; return browser; } catch (Throwable e) { useMozilla
		 * = false; } } if (useMozilla) { // Use FF return new Browser(parent,
		 * style | SWT.MOZILLA); } }
		 */
		return new Browser(parent, style);
	}
}
