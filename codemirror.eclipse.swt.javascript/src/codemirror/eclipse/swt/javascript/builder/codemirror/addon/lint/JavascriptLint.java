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
package codemirror.eclipse.swt.javascript.builder.codemirror.addon.lint;

import codemirror.eclipse.swt.builder.codemirror.addon.lint.LintImpl;

/**
 * Javascript Lint Implementation.
 * 
 */
public class JavascriptLint extends LintImpl {

	public static final LintImpl INSTANCE = new JavascriptLint();

	public JavascriptLint() {
		super(new String[] { "scripts/jshint/jshint-r12-80277ef.js",
				"scripts/codemirror/addon/lint/javascript-lint.js" }, null);
	}
}
