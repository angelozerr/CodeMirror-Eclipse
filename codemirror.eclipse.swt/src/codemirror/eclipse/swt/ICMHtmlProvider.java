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
package codemirror.eclipse.swt;

import codemirror.eclipse.swt.builder.Mode;

/**
 * Provider for HTML CodeMirror.
 * 
 */
public interface ICMHtmlProvider {

	/**
	 * Returns the Html content which contains a textarea wrapped with
	 * CodeMirror.
	 * 
	 * @return
	 */
	String getHtml();

	/**
	 * Retrurns the mode of CodeMirror to use.
	 * 
	 * @return
	 */
	Mode getMode();

}
