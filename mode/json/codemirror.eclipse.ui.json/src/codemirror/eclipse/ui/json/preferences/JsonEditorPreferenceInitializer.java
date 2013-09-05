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

import org.eclipse.jface.preference.IPreferenceStore;

import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.swt.builder.addon.fold.FoldType;
import codemirror.eclipse.swt.json.builder.JsonMode;
import codemirror.eclipse.ui.json.internal.Activator;
import codemirror.eclipse.ui.preferences.CMPreferenceInitializer;
import codemirror.eclipse.ui.preferences.PreferenceHelper;

/**
 * Json Preferences initializer.
 * 
 */
public class JsonEditorPreferenceInitializer extends CMPreferenceInitializer {

	public JsonEditorPreferenceInitializer() {
		this(Activator.getDefault().getPreferenceStore());
	}

	public JsonEditorPreferenceInitializer(IPreferenceStore store) {
		super(store, JsonMode.INSTANCE);
	}

	@Override
	protected void initializeDefaultPreferences(IPreferenceStore store) {

		// Folding
		PreferenceHelper.setDefaultFoldType(store, FoldType.BRACE_FOLD, true);

		// Theme
		PreferenceHelper.setDefaultTheme(store, Theme.ECLIPSE);

		// Hover
		// PreferenceHelper.setDefaultHover(store, true);

		// Mark Occurrences
		// PreferenceHelper.setDefaultMarkOccurrences(store,
		// ShowTokenType.VARIABLE, true);
		// PreferenceHelper.setDefaultMarkOccurrences(store,
		// ShowTokenType.VARIABLE_DEF, true);

	}

}
