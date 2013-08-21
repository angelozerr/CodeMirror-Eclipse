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
package codemirror.eclipse.swt;

import org.eclipse.swt.SWT;

/**
 * Web Browser type.
 * 
 */
public enum WebBrowserType {

	Mozilla(SWT.MOZILLA), WebKit(SWT.WEBKIT), Default(SWT.NONE);

	private final int style;

	private WebBrowserType(int style) {
		this.style = style;
	}

	public int getStyle() {
		return style;
	}

	public String getName() {
		return name();
	}

	public static WebBrowserType getWebBrowserType(String name) {
		for (WebBrowserType browserType : values()) {
			if (browserType.getName().equals(name)) {
				return browserType;
			}
		}
		return WebBrowserType.Default;
	}
}
