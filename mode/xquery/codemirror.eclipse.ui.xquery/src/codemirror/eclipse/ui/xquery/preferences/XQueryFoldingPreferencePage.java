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
package codemirror.eclipse.ui.xquery.preferences;

import codemirror.eclipse.swt.xquery.builder.XQueryMode;
import codemirror.eclipse.ui.preferences.FoldingPreferencePage;
import codemirror.eclipse.ui.xquery.internal.Activator;

/**
 * XQuery folding preference page.
 * 
 */
public class XQueryFoldingPreferencePage extends FoldingPreferencePage {

	public XQueryFoldingPreferencePage() {
		super(Activator.getDefault().getPreferenceStore(), XQueryMode.INSTANCE);
	}
}
