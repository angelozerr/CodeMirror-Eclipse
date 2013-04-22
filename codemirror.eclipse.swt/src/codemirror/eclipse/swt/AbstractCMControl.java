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

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

import codemirror.eclipse.swt.internal.BrowserFactory;

/**
 * Abstract class for CodeMirror control.
 * 
 */
public abstract class AbstractCMControl extends Composite {

	protected final Browser browser;
	private String textToBeSet;
	private boolean loaded;
	private boolean isReady;

	private String mode;

	public AbstractCMControl(File file, Composite parent, int style) {
		this(file, parent, style, SWT.NONE);
	}

	public AbstractCMControl(String url, Composite parent, int style) {
		this(url, parent, style, SWT.NONE);
	}

	public AbstractCMControl(File file, Composite parent, int style,
			int browserStyle) {
		this(toURL(file), parent, style, browserStyle);
	}

	public static String toURL(File file) {
		try {
			return new StringBuilder("file://").append(file.getCanonicalPath()).toString();
		} catch (IOException e) {
			return new StringBuilder("file://").append(file.getPath()).toString();
		}
	}

	public AbstractCMControl(String url, Composite parent, int style,
			int browserStyle) {
		super(parent, style);
		super.setLayout(new FillLayout());
		this.textToBeSet = null;
		// registerServiceHandler();
		browser = BrowserFactory.create(this, browserStyle);
		// String url = hostUrl + URL;
		browser.setUrl(url);
		// createBrowserFunctions();
		createBrowserFunctions();
		browser.addProgressListener(new ProgressListener() {

			public void completed(ProgressEvent event) {
				onLoad();
			}

			public void changed(ProgressEvent event) {
				// not needed
			}
		});
	}

	protected void onLoad() {
		loaded = true;
		if (textToBeSet != null) {
			setText(textToBeSet);
			textToBeSet = null;
		}
		createBrowserFunctions();
	}

	protected void createBrowserFunctions() {
		new BrowserFunction(browser, "ready") {

			public Object function(Object[] arguments) {

				isReady = true;
				if (textToBeSet != null) {
					doSetText(textToBeSet);
					textToBeSet = null;
				}
				return null;
			}
		};
	}

	@Override
	public void setLayout(Layout layout) {
		throw new UnsupportedOperationException(
				"Cannot change internal layout of Editor");
	}

	public void setText(String text) {
		setText(text, false);
	}

	public void setText(String text, boolean force) {
		if (text == null || text.length() == 0) {
			text = "";
		}

		if (isLoaded() || force) {
			doSetText(text);
		} else {
			textToBeSet = text;
		}

	}

	public String getText() {
		if (!isLoaded()) {
			if (textToBeSet != null) {
				return textToBeSet;
			} else {
				throw new IllegalStateException("Editor not loaded");
			}
		}

		return doGetText();
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	@Override
	public boolean setFocus() {
		return browser.setFocus();
	}

	public boolean isLoaded() {
		return loaded;
	}
	
	protected abstract String doGetText();

	protected abstract void doSetText(String text);
}
