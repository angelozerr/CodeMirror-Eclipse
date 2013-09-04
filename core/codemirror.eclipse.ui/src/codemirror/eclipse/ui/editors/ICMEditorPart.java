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
import org.eclipse.ui.IWorkbenchPart;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.swt.builder.CMBuilder;

public interface ICMEditorPart {

	String getURL();
	
	CMBuilder getBuilder();

	IValidator getValidator();

	void editorDirtyStateChanged();

	CMControl getCM();

	CMControl createCM(String url, CMBuilder builder, Composite parent, int none);

	void saveCM(String text, IProgressMonitor monitor) throws IOException,
			CoreException;

	String loadCM() throws IOException, CoreException;

	String getLineSeparator();

	IWorkbenchPart getWorkbenchPart();

}
