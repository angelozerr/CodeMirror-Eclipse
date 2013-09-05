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
package codemirror.eclipse.ui.xml.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import codemirror.eclipse.swt.builder.addon.fold.FoldType;
import codemirror.eclipse.swt.xml.builder.XMLMode;
import codemirror.eclipse.ui.preferences.CMPreferenceInitializer;
import codemirror.eclipse.ui.preferences.PreferenceHelper;
import codemirror.eclipse.ui.xml.internal.Activator;

/**
 * XML Preferences initializer.
 * 
 */
public class XMLEditorPreferenceInitializer extends CMPreferenceInitializer {

	public XMLEditorPreferenceInitializer() {
		this(Activator.getDefault().getPreferenceStore());
	}

	public XMLEditorPreferenceInitializer(IPreferenceStore store) {
		super(store, XMLMode.INSTANCE);
	}

	@Override
	protected void initializeDefaultPreferences(IPreferenceStore store) {

		// Folding
		PreferenceHelper.setDefaultFoldType(store, FoldType.XML_FOLD, true);

		// Hover
		// PreferenceHelper.setDefaultHover(store, true);

		// Mark Occurrences
		// PreferenceHelper.setDefaultMarkOccurrences(store,
		// ShowTokenType.VARIABLE, true);
		// PreferenceHelper.setDefaultMarkOccurrences(store,
		// ShowTokenType.VARIABLE_DEF, true);

	}

}
