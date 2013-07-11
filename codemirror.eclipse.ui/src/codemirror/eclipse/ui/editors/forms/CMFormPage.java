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
package codemirror.eclipse.ui.editors.forms;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.ui.editors.ICMEditorPart;
import codemirror.eclipse.ui.internal.CMEditorPartHelper;

public abstract class CMFormPage extends FormPage implements ICMEditorPart {

	private CMControl cm;
	private final String url;
	private Control statusError;

	public CMFormPage(CMFormEditor editor, String id, String title, File file) {
		this(editor, id, title, CMControl.toURL(file));
	}

	public CMFormPage(CMFormEditor editor, String id, String title, String url) {
		super(editor, id, title);
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
	protected void createFormContent(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		toolkit.decorateFormHeading(form.getForm());

		IToolBarManager manager = form.getToolBarManager();
		((CMFormEditor) getEditor()).contributeToToolbar(manager);
		form.updateToolBar();
		String titleText = getFormTitleText();
		if (titleText != null) {
			form.setText(titleText);
		}
		Image titleImage = getFormTitleImage();
		if (titleImage != null) {
			form.setImage(titleImage);
		}
		toolkit.decorateFormHeading(form.getForm());
		createUI(managedForm, toolkit);
	}

	protected Image getFormTitleImage() {
		return null;
	}

	protected String getFormTitleText() {
		return null;
	}

	private void createUI(IManagedForm managedForm, FormToolkit toolkit) {
		Composite body = managedForm.getForm().getBody();
		body.setLayout(new GridLayout());
		try {
			String text = loadCM();
			this.cm = CMEditorPartHelper.createCM(this, text, body);
		} catch (IOException e) {
			handleLoadError(e, body, toolkit);
		} catch (CoreException e) {
			handleLoadError(e, body, toolkit);
		}
	}

	private void handleLoadError(Exception e, Composite parent,
			FormToolkit toolkit) {
		displayError(e, parent, toolkit);
	}

	protected void displayError(Exception e, Composite parent,
			FormToolkit toolkit) {
		this.statusError = createStatusError(e, parent, toolkit);
	}

	protected Text createStatusError(Exception e, Composite parent,
			FormToolkit toolkit) {
		StringWriter s = new StringWriter();
		PrintWriter writer = new PrintWriter(s);
		e.printStackTrace(writer);
		Text statusError = toolkit.createText(parent, s.toString(),
				SWT.READ_ONLY);
		return statusError;
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

	public IValidator getValidator() {
		return null;
	}

	/**
	 * Presents an error dialog to the user when a problem happens during save.
	 * <p>
	 * Subclasses can decide to override the given title and message.
	 * </p>
	 * 
	 * @param title
	 *            the dialog title
	 * @param message
	 *            the message to display
	 * @param exception
	 *            the exception to handle
	 */
	protected void openSaveErrorDialog(String title, String message,
			CoreException exception) {
		ErrorDialog.openError(getSite().getShell(), title, message,
				exception.getStatus());
	}

	public void editorDirtyStateChanged() {
		getEditor().editorDirtyStateChanged();
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
