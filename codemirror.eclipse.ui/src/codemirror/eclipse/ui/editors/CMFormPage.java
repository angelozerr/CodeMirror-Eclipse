package codemirror.eclipse.ui.editors;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
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
import org.osgi.framework.Bundle;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.ui.internal.CMEditorPartHelper;

public abstract class CMFormPage extends FormPage implements ICMEditorPart {

	static {
		Bundle bundle = Platform
				.getBundle("org.mozilla.xulrunner.win32.win32.x86"); //$NON-NLS-1$
		if (bundle != null) {
			URL resourceUrl = bundle.getResource("xulrunner"); //$NON-NLS-1$
			if (resourceUrl != null) {
				try {
					URL fileUrl = FileLocator.toFileURL(resourceUrl);
					File file = new File(fileUrl.toURI());
					System.setProperty(
							"org.eclipse.swt.browser.XULRunnerPath", file.getAbsolutePath()); //$NON-NLS-1$
				} catch (IOException e) {
					// log the exception
				} catch (URISyntaxException e) {
					// log the exception
				}
			}
		}
	}

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
}
