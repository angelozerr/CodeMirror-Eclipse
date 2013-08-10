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
package codemirror.eclipse.swt.xquery.builder;

import java.util.List;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.codemirror.ExtraKeysOption;
import codemirror.eclipse.swt.builder.codemirror.GuttersOptionUpdater;

/**
 * XQuery CodeMirror builder.
 * 
 */
public class CMXQueryBuilder extends CMBuilder {

	public CMXQueryBuilder(String baseURL, boolean runMode) {
		super(XQueryMode.INSTANCE, baseURL, runMode);
		Options options = super.getOptions();

		// brackets
		options.setAutoCloseBrackets(true);
		options.setMatchBrackets(true);

		// Line numbers
		options.setLineNumbers(true);
		List<String> gutters = options.getGutters();
		gutters.add(GuttersOptionUpdater.LINENUMBERS);

		// Completion
		ExtraKeysOption extraKeys = options.getExtraKeys();
		extraKeys.addOption("':'", ExtraKeysOption.PASS_AND_HINT);
		extraKeys.addOption("'$'", ExtraKeysOption.PASS_AND_HINT);
		extraKeys.addOption("Ctrl-Space", ExtraKeysOption.AUTOCOMPLETE);
		extraKeys.addOption("Ctrl-Q", ExtraKeysOption.FORMAT);

		installHint();
	}

	@Override
	protected void installHint() {
		super.installHint();
		installXQueryHint();
	}

	protected void installXQueryHint() {
		// <!-- CodeMirror-XQuery -->
		addScript("scripts/codemirror-xquery/addon/hint/xquery/xquery-hint.js");
		addScript("scripts/codemirror-xquery/addon/hint/xquery/system-functions.xml.js");
		addStyle("scripts/codemirror-extension/addon/hint/show-hint-eclipse.css");
	}

}
