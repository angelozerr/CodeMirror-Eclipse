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

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.codemirror.GuttersOption;

/**
 * CSS CodeMirror builder.
 * 
 */
public class CMCSSBuilder extends CMBuilder {

	public CMCSSBuilder(String baseURL, boolean runMode) {
		super(CSSMode.INSTANCE, baseURL, runMode);
		Options options = super.getOptions();
		List<String> gutters = options.getGutters().getGutters();

		// Line numbers
		options.setLineNumbers(true);
		gutters.add(GuttersOption.LINENUMBERS);
	}

}
