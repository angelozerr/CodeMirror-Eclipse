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
package addon;

import codemirror.eclipse.swt.builder.addon.lint.LintImpl;

/**
 * CSS Lint Implementation.
 * 
 */
public class CSSLint extends LintImpl {

	public static final LintImpl INSTANCE = new CSSLint();

	public CSSLint() {
		super(new String[] { "scripts/csslint/csslint.js",
				"scripts/codemirror/addon/lint/css-lint.js" }, null);
	}
}
