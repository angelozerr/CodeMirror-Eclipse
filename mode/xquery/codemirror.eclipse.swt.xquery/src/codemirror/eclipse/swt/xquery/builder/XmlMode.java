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
package codemirror.eclipse.swt.xquery.builder;

import codemirror.eclipse.swt.builder.Mode;

/**
 * XML CodeMirror mode.
 * 
 */
public class XmlMode extends Mode {

	public static final Mode INSTANCE = new XmlMode();

	private XmlMode() {
		super("text/xml", new String[] { "scripts/codemirror/mode/xml/xml.js" });
	}
}
