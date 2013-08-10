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
package codemirror.eclipse.swt.json.builder.codemirror.addon.lint;

import codemirror.eclipse.swt.builder.codemirror.addon.lint.LintImpl;

/**
 * JSON Lint Implementation.
 * 
 */
public class JsonLint extends LintImpl {

	public static final LintImpl INSTANCE = new JsonLint();

	public JsonLint() {
		super(new String[] { "scripts/jsonlint/jsonlint.js",
				"scripts/codemirror/addon/lint/json-lint.js" }, null);
	}
}
