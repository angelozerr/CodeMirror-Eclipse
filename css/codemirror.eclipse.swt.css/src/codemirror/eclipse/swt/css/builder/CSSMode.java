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
package codemirror.eclipse.swt.css.builder;

import codemirror.eclipse.swt.builder.Mode;

/**
 * CSS CodeMirror mode.
 * 
 */
public class CSSMode extends Mode {

	public static final Mode INSTANCE = new CSSMode();

	private CSSMode() {
		super(
				"css",
				new String[] { "scripts/codemirror/mode/css/css.js" });
	}
}
