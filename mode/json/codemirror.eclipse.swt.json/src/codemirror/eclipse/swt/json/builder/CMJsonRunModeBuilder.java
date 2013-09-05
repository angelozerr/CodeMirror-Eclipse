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

import codemirror.eclipse.swt.builder.CMRunModeBuilder;
import codemirror.eclipse.swt.builder.Theme;

/**
 * JSON CodeMirror builder for run mode.
 * 
 */
public class CMJsonRunModeBuilder extends CMRunModeBuilder {

	public CMJsonRunModeBuilder(String baseURL) {
		super(JsonMode.INSTANCE, baseURL);
		setTheme(Theme.ECLIPSE);
	}

}
