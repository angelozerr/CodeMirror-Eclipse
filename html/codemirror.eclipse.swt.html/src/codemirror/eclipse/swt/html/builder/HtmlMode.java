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
package codemirror.eclipse.swt.html.builder;

import codemirror.eclipse.swt.builder.Mode;

/**
 * HTML CodeMirror mode.
 * 
 */
public class HtmlMode extends Mode {

	public static final Mode INSTANCE = new HtmlMode();

	private HtmlMode() {
		super("text/html", new String[] {
				"scripts/codemirror/mode/xml/xml.js",
				"scripts/codemirror/mode/javascript/javascript.js",
				"scripts/codemirror/mode/css/css.js",
				"scripts/codemirror/mode/htmlmixed/htmlmixed.js" });
	}
}
