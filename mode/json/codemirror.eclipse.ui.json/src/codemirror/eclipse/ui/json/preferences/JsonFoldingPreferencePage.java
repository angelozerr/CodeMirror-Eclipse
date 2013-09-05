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
package codemirror.eclipse.ui.json.preferences;

import codemirror.eclipse.swt.json.builder.JsonMode;
import codemirror.eclipse.ui.json.internal.Activator;
import codemirror.eclipse.ui.preferences.FoldingPreferencePage;

/**
 * JSON folding preference page.
 * 
 */
public class JsonFoldingPreferencePage extends FoldingPreferencePage {

	public JsonFoldingPreferencePage() {
		super(Activator.getDefault().getPreferenceStore(), JsonMode.INSTANCE);
	}
}
