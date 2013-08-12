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
package codemirror.eclipse.swt.json.builder;

import java.util.List;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.codemirror.ExtraKeysOption;
import codemirror.eclipse.swt.builder.codemirror.GuttersOptionUpdater;
import codemirror.eclipse.swt.json.builder.codemirror.addon.lint.JsonLint;
import codemirror.eclipse.swt.json.builder.commands.JsonFormatCommand;

/**
 * JSON CodeMirror builder.
 * 
 */
public class CMJsonBuilder extends CMBuilder {

	public CMJsonBuilder(String baseURL) {
		super(JsonMode.INSTANCE, baseURL);

		Options options = super.getOptions();
		List<String> gutters = options.getGutters();

		// brackets
		options.setAutoCloseBrackets(true);
		options.setMatchBrackets(true);

		// CodeMirror JSON Lint (JS+CSS)
		options.getLint(JsonLint.INSTANCE).setLint(true);
		gutters.add(GuttersOptionUpdater.LINT);

		// Formatter
		installFormat(options);

		// Line numbers
		options.setLineNumbers(true);
		gutters.add(GuttersOptionUpdater.LINENUMBERS);
	}

	protected void installFormat(Options options) {
		ExtraKeysOption extraKeys = options.getExtraKeys();
		extraKeys.addOption("Ctrl-Q", JsonFormatCommand.INSTANCE);

		options.getBuilder().addScript("scripts/jsonlint/formatter.js");

	}

}
