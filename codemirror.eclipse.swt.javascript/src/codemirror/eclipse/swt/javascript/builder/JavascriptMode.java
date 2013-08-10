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
package codemirror.eclipse.swt.javascript.builder;

import codemirror.eclipse.swt.builder.Mode;

/**
 * Javascript CodeMirror mode.
 * 
 */
public class JavascriptMode extends Mode {

	public static final Mode INSTANCE = new JavascriptMode();

	private JavascriptMode() {
		super(
				"application/javascript",
				new String[] { "scripts/codemirror/mode/javascript/javascript.js" });
	}
}
