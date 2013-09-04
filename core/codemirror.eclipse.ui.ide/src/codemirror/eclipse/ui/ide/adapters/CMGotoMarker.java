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

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.ide.IGotoMarker;

import codemirror.eclipse.swt.CMControl;

/**
 * Implementation of {@link IGotoMarker} for CodeMirror editor.
 * 
 */
public class CMGotoMarker implements IGotoMarker {

	private final CMControl cm;

	public CMGotoMarker(CMControl cm) {
		this.cm = cm;
		cm.setData(CMGotoMarker.class.getName());
	}

	public static CMGotoMarker getGotoMarker(CMControl cm) {
		CMGotoMarker gotoMarker = (CMGotoMarker) cm.getData(CMGotoMarker.class
				.getName());
		if (gotoMarker == null) {
			gotoMarker = new CMGotoMarker(cm);
		}
		return gotoMarker;
	}

	public void gotoMarker(IMarker marker) {
		try {
			Integer charStart = (Integer) marker
					.getAttribute(IMarker.CHAR_START);
			Integer charEnd = (Integer) marker.getAttribute(IMarker.CHAR_END);
			if (charStart != null && charEnd != null) {
				cm.setSelection(charStart, charEnd);
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
