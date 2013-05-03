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
package codemirror.eclipse.ui.editors;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.IValidator;


public interface ICMEditorPart {

	String getURL();

	//IFile getFile();

	IValidator getValidator();

	void editorDirtyStateChanged();

	CMControl getCMControl();

	CMControl createCM(String url, Composite parent, int none);

	void saveCM(String text, IProgressMonitor monitor) throws IOException, CoreException;

	String loadCM() throws IOException, CoreException;

}
