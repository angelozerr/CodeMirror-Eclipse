package codemirror.eclipse.ui.xquery.viewers;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.xquery.builder.XmlMode;

public class XMLEditLookupDialog extends Dialog {

	/** The dialog title. */
	private static final String DIALOG_TITLE = "Text Editor";

	/** The initial value. */
	private String initialValue;

	/** The return value. */
	private String returnValue;

	/** The text area. */
	private CMControl text;

	private Point initialLocation;

	private final String dialogTitle;

	/**
	 * Creates a new instance of TextDialog.
	 * 
	 * @param parentShell
	 *            the parent shell
	 * @param initialValue
	 *            the initial value
	 */
	public XMLEditLookupDialog(Shell parentShell, String dialogTitle,
			String initialValue, Point initialLocation) {
		super(parentShell);
		super.setShellStyle(super.getShellStyle() | SWT.RESIZE);
		this.initialValue = initialValue;
		this.returnValue = null;
		this.initialLocation = initialLocation;
		this.dialogTitle = dialogTitle;
	}

	/**
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(dialogTitle);
		// shell.setImage( BrowserCommonActivator.getDefault().getImage(
		// BrowserCommonConstants.IMG_TEXTEDITOR ) );
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				false);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		returnValue = text.getText();
		super.okPressed();
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		// create composite
		Composite composite = (Composite) super.createDialogArea(parent);
		GridData gd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gd);

		// text widget
		text = new CMControl(CMBuilderRegistry.getInstance().getBuilder(
				XmlMode.INSTANCE), parent, SWT.NONE);
		text.setText(this.initialValue);
		// GridData gd = new GridData(GridData.GRAB_HORIZONTAL |
		// GridData.HORIZONTAL_ALIGN_FILL);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
		gd.heightHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH / 2);
		text.setLayoutData(gd);

		applyDialogFont(composite);
		return composite;
	}

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {
		return returnValue;
	}

	@Override
	protected Point getInitialLocation(Point initialSize) {
		if (initialLocation != null)
			return initialLocation;
		return super.getInitialLocation(initialSize);
	}
}
