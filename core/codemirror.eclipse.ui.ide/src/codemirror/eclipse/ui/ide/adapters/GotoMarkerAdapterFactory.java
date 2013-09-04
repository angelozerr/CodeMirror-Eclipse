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
package codemirror.eclipse.ui.ide.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.ide.IGotoMarker;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.ICMControlProvider;

/**
 * 
 * Adapter factory used to retrieved the {@link IGotoMarker} implementation of
 * the CodeMirror editor.
 * 
 */
public class GotoMarkerAdapterFactory implements IAdapterFactory {

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Object paramObject, Class paramClass) {
		CMControl cm = ((ICMControlProvider) paramObject).getCM();
		if (cm != null) {
			return CMGotoMarker.getGotoMarker(cm);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Class[] getAdapterList() {
		return new Class[] { IGotoMarker.class };
	}

}
