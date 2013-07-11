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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.ui.internal.CMEditorPartHelper;

public abstract class CMEditorPart extends EditorPart implements ICMEditorPart {

	private CMControl cm;
	private final String url;
	private Control statusError;

	public CMEditorPart(File file) {
		this(CMControl.toURL(file));
	}

	public CMEditorPart(String url) {
		this.url = url;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		try {
			CMEditorPartHelper.saveCM(this, monitor);
		} catch (Exception e) {
			handleSaveError(e);
		}
	}

	protected void handleSaveError(Exception e) {
		CMEditorPartHelper.openSaveErrorDialog(getSite().getShell(), e);
	}

	@Override
	public void doSaveAs() {
		// Do nothing
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		setPartName(input.getName());
	}

	@Override
	public boolean isDirty() {
		if (cm != null) {
			return cm.isDirty();
		}
		return false;
	}

	@Override
	public void setFocus() {
		if (cm != null) {
			cm.setFocus();
		} else if (statusError != null) {
			statusError.setFocus();
		}
	}

	@Override
	public void createPartControl(Composite parent) {

		try {
			String text = loadCM();
			this.cm = CMEditorPartHelper.createCM(this, text, parent);
		} catch (IOException e) {
			handleError(e, parent);
		} catch (CoreException e) {
			handleError(e, parent);
		}
	}

	private void handleError(Exception e, Composite parent) {
		displayError(e, parent);
	}

	protected void displayError(Exception e, Composite parent) {
		this.statusError = createStatusError(e, parent);
	}

	protected Text createStatusError(Exception e, Composite parent) {
		Text statusError = new Text(parent, SWT.READ_ONLY);
		StringWriter s = new StringWriter();
		PrintWriter writer = new PrintWriter(s);
		e.printStackTrace(writer);
		statusError.setText(s.toString());
		return statusError;
	}

	public void editorDirtyStateChanged() {
		firePropertyChange(PROP_DIRTY);
	}

	public CMControl getCMControl() {
		return cm;
	}

	public CMControl createCM(String url, Composite parent, int style) {
		return new CMControl(url, parent, style);
	}

	public String getURL() {
		return url;
	}

	public String loadCM() throws IOException, CoreException {
		return CMEditorPartHelper.getOperation(getEditorInput()).loadCM(
				getEditorInput());
	}

	public void saveCM(String text, IProgressMonitor monitor)
			throws IOException, CoreException {
		CMEditorPartHelper.getOperation(getEditorInput()).saveCM(text,
				getEditorInput(), monitor);
	}

	public String getLineSeparator() {
		return CMEditorPartHelper.getOperation(getEditorInput())
				.getLineSeparator(getEditorInput());
	}

}
