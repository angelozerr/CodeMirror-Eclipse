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

import java.util.List;

import addon.CSSLint;
import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.GuttersOptionUpdater;
import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.addon.fold.FoldGutterOption;
import codemirror.eclipse.swt.builder.addon.fold.FoldType;

/**
 * CSS CodeMirror builder.
 * 
 */
public class CMCSSBuilder extends CMBuilder {

	private static final FoldType[] SUPPORTED_FOLDTYPE = new FoldType[] { FoldType.BRACE_FOLD };

	public CMCSSBuilder(String baseURL) {
		super(CSSMode.INSTANCE, baseURL);
		Options options = super.getOptions();
		List<String> gutters = options.getGutters();

		// brackets
		options.setAutoCloseBrackets(true);
		options.setMatchBrackets(true);

		// CodeMirror JSON Lint (JS+CSS)
		options.getLint(CSSLint.INSTANCE).setLint(true);
		gutters.add(GuttersOptionUpdater.LINT);

		// Line numbers
		options.setLineNumbers(true);
		gutters.add(GuttersOptionUpdater.LINENUMBERS);

		// Fold
		super.setSupportedFoldTypes(SUPPORTED_FOLDTYPE);
		gutters.add(GuttersOptionUpdater.FOLDGUTTER);
		FoldGutterOption fold = options.getFoldGutter();
		fold.setRangeFinder(getSupportedFoldTypes());
	}

}
