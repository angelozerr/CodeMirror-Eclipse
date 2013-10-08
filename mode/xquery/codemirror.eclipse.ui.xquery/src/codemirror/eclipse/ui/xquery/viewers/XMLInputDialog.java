package codemirror.eclipse.ui.xquery.viewers;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import codemirror.eclipse.ui.xquery.internal.Messages;

public class XMLInputDialog extends
		AbstractTextAndDialogCellEditor<XMLEditLookupDialog> {

	private String dialogMessage;
	private String dialogTitle;

	public XMLInputDialog(Composite parent) {
		super(parent);
		setDialogMessage(Messages.XMLInputDialog_message);
		setDialogTitle(Messages.XMLInputDialog_title);
	}

	public void setDialogMessage(String dialogMessage) {
		this.dialogMessage = dialogMessage;
	}

	public void setDialogTitle(String dialogTitle) {
		this.dialogTitle = dialogTitle;
	}

	@Override
	protected XMLEditLookupDialog createDialog(Shell shell, String intitialValue) {
		return new XMLEditLookupDialog(shell, dialogTitle, 
				intitialValue, getButton().toDisplay(0, -32));
	}

	@Override
	protected String getValue(XMLEditLookupDialog dialog) {
		return dialog.getText();
	}

}
