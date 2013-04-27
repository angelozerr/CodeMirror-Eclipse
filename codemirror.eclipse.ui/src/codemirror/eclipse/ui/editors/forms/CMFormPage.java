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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IFileEditorInput;
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

	public CMFormPage(CMFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		CMEditorPartHelper.saveCM(this, monitor);
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
		this.cm = CMEditorPartHelper.createCM(this, body);
	}

	public IFile getFile() {
		return ((IFileEditorInput) getEditorInput()).getFile();
	}

	@Override
	public boolean isDirty() {
		return cm.isDirty();
	}

	@Override
	public void setFocus() {
		cm.setFocus();
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
}
