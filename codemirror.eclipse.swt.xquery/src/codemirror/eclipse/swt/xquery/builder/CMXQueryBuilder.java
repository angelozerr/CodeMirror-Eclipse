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
import codemirror.eclipse.swt.builder.Function;
import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.swt.builder.codemirror.ExtraKeysOption;
import codemirror.eclipse.swt.builder.codemirror.GuttersOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.addon.fold.FoldGutterOption;
import codemirror.eclipse.swt.builder.commands.PassAndHintCommand;
import codemirror.eclipse.swt.xquery.builder.commands.XQueryAutocompleteCommand;
import codemirror.eclipse.swt.xquery.builder.extension.addon.hover.XQueryHover;

/**
 * XQuery CodeMirror builder.
 * 
 */
public class CMXQueryBuilder extends CMBuilder {

	public CMXQueryBuilder(String baseURL) {
		super(XQueryMode.INSTANCE, baseURL);
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
		extraKeys.addOption("':'", PassAndHintCommand.INSTANCE);
		extraKeys.addOption("'$'", PassAndHintCommand.INSTANCE);
		extraKeys.addOption("Ctrl-Space", XQueryAutocompleteCommand.INSTANCE);

		installHint(true, true);
		installTrackVars(options);

		// Fold
		gutters.add(GuttersOptionUpdater.FOLDGUTTER);

		FoldGutterOption fold = options.getFoldGutter();
		fold.setRangeFinder("new CodeMirror.fold.combine(CodeMirror.fold.brace, CodeMirror.fold.comment)");

		// MatchHighlighterOption matchHighlighter =
		// options.getMatchHighlighter();
		// matchHighlighter.setShowToken("/[\\w|-]/");

		options.getTextHover(XQueryHover.INSTANCE).setTextHover(true);
		
		setTheme(Theme.XQ_LIGHT);
	}

	private void installTrackVars(Options options) {
		// <!-- CodeMirror-XQuery -->
		addScript("scripts/codemirror-xquery/addon/execute/xquery/track-vars.js");
		getOptions().addOption(
				"trackVars",
				new Function("function(globalVars, changed) {\n"
						+ "if (changed) {\n"
						+ "cm_refreshVars(globalVars);\n}\n" + "}"));
	}

	@Override
	protected void installHint(boolean withContextInfo, boolean withTemplates) {
		super.installHint(withContextInfo, withTemplates);
		installXQueryHint();
	}

	protected void installXQueryHint() {
		// <!-- CodeMirror-XQuery -->
		addScript("scripts/codemirror-xquery/addon/hint/xquery/xquery-hint.js");
		addStyle("scripts/codemirror-xquery/addon/hint/xquery/xquery-hint.css");
		addScript("scripts/codemirror-xquery/addon/hint/xquery/system-functions.xml.js");
		// XQuery Templates
		addScript("scripts/codemirror-xquery/addon/hint/xquery/xquery-templates.js");
	}

}
