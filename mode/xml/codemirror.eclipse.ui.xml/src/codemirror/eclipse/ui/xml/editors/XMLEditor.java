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
package codemirror.eclipse.ui.xml.editors;

import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.xml.builder.XMLMode;
import codemirror.eclipse.ui.editors.CMEditorPart;

/**
 * XML Editor.
 * 
 */
public class XMLEditor extends CMEditorPart {

	public XMLEditor() {
		super(CMBuilderRegistry.getInstance().getBuilder(XMLMode.INSTANCE));
	}

	public IValidator getValidator() {
		return null;
	}

}
