/*******************************************************************************
 * Copyright (c) 2011 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package codemirror.eclipse.ui.json.internal;

import org.eclipse.osgi.util.NLS;

/**
 * Messages.
 * 
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "codemirror.eclipse.ui.json.internal.Messages";//$NON-NLS-1$

	// ------------- Preferences

	public static String JsonEditorPreferencePage_description;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
