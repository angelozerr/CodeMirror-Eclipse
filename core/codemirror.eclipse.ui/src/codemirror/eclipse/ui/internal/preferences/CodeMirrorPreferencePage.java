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
package codemirror.eclipse.ui.internal.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import codemirror.eclipse.ui.internal.Messages;

/**
 * CodeMirror Preferences.
 * 
 */
public class CodeMirrorPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	@Override
	protected Control createContents(Composite parent) {
		return new Composite(parent, SWT.NONE);
	}

	public void init(IWorkbench workbench) {
		setDescription(Messages.CodeMirrorPreferences_description);
	}

}
