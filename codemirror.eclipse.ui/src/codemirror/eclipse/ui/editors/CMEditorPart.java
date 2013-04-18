package codemirror.eclipse.ui.editors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.ui.internal.CMEditorPartHelper;
import codemirror.eclipse.ui.internal.org.apache.commons.io.IOUtils;

public abstract class CMEditorPart extends EditorPart implements ICMEditorPart {

	private CMControl cm;

	@Override
	public void doSave(IProgressMonitor monitor) {
		cm.setDirty(false);

		IFile file = ((IFileEditorInput) getEditorInput()).getFile();
		try {
			file.setContents(IOUtils.toInputStream(cm.getText()), true, false,
					monitor);
		} catch (CoreException e) {
			// TODoO Auto-generated catch block
			e.printStackTrace();
		}

		firePropertyChange(PROP_DIRTY);
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		if (!(input instanceof IFileEditorInput))
			throw new PartInitException(
					"Invalid Input: Must be IFileEditorInput");
		setSite(site);
		setInput(input);
		setPartName(input.getName());
	}

	@Override
	public boolean isDirty() {
		return cm.isDirty();
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		this.cm = CMEditorPartHelper.createCM(this, parent);
	}

	@Override
	public void setFocus() {
		cm.setFocus();
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
	 * @since 3.3
	 */
	protected void openSaveErrorDialog(String title, String message,
			CoreException exception) {
		ErrorDialog.openError(getSite().getShell(), title, message,
				exception.getStatus());
	}

	public void editorDirtyStateChanged() {
		firePropertyChange(PROP_DIRTY);
	}

}
