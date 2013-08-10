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

import java.util.List;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.codemirror.GuttersOptionUpdater;

/**
 * Javascript CodeMirror builder.
 * 
 */
public class CMJavascriptBuilder extends CMBuilder {

	public CMJavascriptBuilder(String baseURL, boolean runMode) {
		super(JavascriptMode.INSTANCE, baseURL, runMode);
		Options options = super.getOptions();
		List<String> gutters = options.getGutters();

		// brackets
		options.setAutoCloseBrackets(true);
		options.setMatchBrackets(true);
		
		// CodeMirror Lint (JS+CSS)
		options.getLint().setLint(true);
		// JSON Lint
		super.addScript("scripts/jshint/jshint-r12-80277ef.js");
		super.addScript("scripts/codemirror/addon/lint/javascript-lint.js");
		gutters.add(GuttersOptionUpdater.LINT);

		// Line numbers
		options.setLineNumbers(true);
		gutters.add(GuttersOptionUpdater.LINENUMBERS);
	}

}
