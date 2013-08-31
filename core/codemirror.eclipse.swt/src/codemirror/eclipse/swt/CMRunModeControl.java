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

import java.io.File;

import org.eclipse.swt.widgets.Composite;

import codemirror.eclipse.swt.builder.Mode;
import codemirror.eclipse.swt.internal.org.apache.commons.lang3.StringEscapeUtils;

/**
 * CodeMirror control used with runMode.js.
 * 
 */
public class CMRunModeControl extends AbstractCMControl {

	private final Mode mode;

	public CMRunModeControl(File file, Mode mode, Composite parent, int style) {
		super(file, parent, style);
		this.mode = mode;
	}

	public CMRunModeControl(String url, Mode mode, Composite parent, int style) {
		super(url, parent, style);
		this.mode = mode;
	}

	public CMRunModeControl(ICMHtmlProvider builder, Composite parent, int style) {
		super(builder, parent, style);
		this.mode = builder.getMode();
	}

	@Override
	protected String doGetText() {
		return (String) browser
				.evaluate("document.getElementById('code').innerHTML;");
	}

	@Override
	protected void doSetText(String text) {
		// CodeMirror.runMode('<a>aaa</a>',
		// "application/xml",document.getElementById('code'))
		StringBuilder js = new StringBuilder("CodeMirror.runMode('");
		js.append(StringEscapeUtils.escapeEcmaScript(text));
		js.append("', '");
		js.append(getMode());
		js.append("', document.getElementById('code'));");
		browser.evaluate(js.toString());
	}

	private String getMode() {
		return mode.getMimeType();
	}

}
