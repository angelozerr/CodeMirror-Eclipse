package codemirror.eclipse.swt.search;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Shell;

public class FindReplaceDialogStub implements DisposeListener {

	/**
	 * Listener for disabling the dialog on shell close.
	 * <p>
	 * This stub is shared amongst <code>IWorkbenchPart</code>s.
	 * </p>
	 */
	private static FindReplaceDialogStub fgFindReplaceDialogStub;

	/**
	 * Listener for disabling the dialog on shell close.
	 * <p>
	 * This stub is shared amongst <code>Shell</code>s.
	 * </p>
	 * 
	 * @since 3.3
	 */
	private static FindReplaceDialogStub fgFindReplaceDialogStubShell;

	/** The find/replace dialog */
	private FindReplaceDialog fDialog;

	/**
	 * Creates a new find/replace dialog accessor anchored at the given shell.
	 * 
	 * @param shell
	 *            the shell if no site is used
	 * @since 3.3
	 */
	public FindReplaceDialogStub(Shell shell) {
		fDialog = new FindReplaceDialog(shell);
		fDialog.create();
		fDialog.getShell().addDisposeListener(this);
	}

	public void widgetDisposed(DisposeEvent event) {

		if (fgFindReplaceDialogStub == this)
			fgFindReplaceDialogStub = null;

		if (fgFindReplaceDialogStubShell == this)
			fgFindReplaceDialogStubShell = null;

		// if (fWindow != null) {
		// fWindow.getPartService().removePartListener(this);
		// fWindow= null;
		// }
		fDialog = null;
	}

	/**
	 * Returns the find/replace dialog.
	 * 
	 * @return the find/replace dialog
	 */
	public FindReplaceDialog getDialog() {
		return fDialog;
	}
}
