package codemirror.eclipse.swt.internal.search;

import org.eclipse.swt.SWT;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

public class FindReplaceDialog extends Dialog {

	/**
	 * Creates a new dialog with the given shell as parent.
	 * 
	 * @param parentShell
	 *            the parent shell
	 */
	public FindReplaceDialog(Shell parentShell) {
		super(parentShell);

		setShellStyle(getShellStyle() ^ SWT.APPLICATION_MODAL | SWT.MODELESS);
		setBlockOnOpen(false);
	}

}
