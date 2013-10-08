package codemirror.eclipse.ui.xquery.viewers;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A CellEditor that is a blending of DialogCellEditor and TextCellEditor. The
 * user can either type directly into the Text or use the button to open a
 * Dialog for editing the cell's value.
 * 
 */
public class TextAndDialogCellEditor extends AbstractTextAndDialogCellEditor {

	private String dialogMessage;
	private String dialogTitle;

	public TextAndDialogCellEditor(Composite parent) {
		super(parent);
	}

	public void setDialogMessage(String dialogMessage) {
		this.dialogMessage = dialogMessage;
	}

	public void setDialogTitle(String dialogTitle) {
		this.dialogTitle = dialogTitle;
	}

	class MyDialolg extends InputDialog {

		public MyDialolg(Shell parentShell, String dialogTitle,
				String dialogMessage, String initialValue,
				IInputValidator validator) {
			super(parentShell, dialogTitle, dialogMessage, initialValue,
					validator);
			setShellStyle(getShellStyle() ^ SWT.APPLICATION_MODAL
					| SWT.MODELESS);
			// setBlockOnOpen(false);
		}

		@Override
		protected int getInputTextStyle() {
			return SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL;
		}
	};

	@Override
	protected Dialog createDialog(Shell shell, String intitialValue) {
		return new MyDialolg(shell, dialogTitle, dialogMessage, intitialValue,
				null);
	}

	@Override
	protected String getValue(Dialog dialog) {
		return ((MyDialolg) dialog).getValue();
	}
}
