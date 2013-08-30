package codemirror.eclipse.swt.search;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.LegacyActionTools;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class FindReplaceDialog extends Dialog {

	private Label fReplaceLabel, fStatusLabel;

	private Combo fFindField, fReplaceField;
	private Button fReplaceSelectionButton, fReplaceFindButton,
			fFindNextButton, fReplaceAllButton;

	private Rectangle fDialogPositionInit;

	private boolean fWrapInit, fCaseInit, fWholeWordInit, fForwardInit,
			fGlobalInit, fIncrementalInit;
	private Button fForwardRadioButton;// , fGlobalRadioButton,
										// fSelectedRangeRadioButton;

	/**
	 * Holds the mnemonic/button pairs for all buttons.
	 * 
	 * @since 3.7
	 */
	private Map fMnemonicButtonMap = new HashMap();

	private IFindReplaceTarget target;

	/**
	 * Creates a new dialog with the given shell as parent.
	 * 
	 * @param parentShell
	 *            the parent shell
	 */
	public FindReplaceDialog(Shell parentShell) {
		super(parentShell);

		fDialogPositionInit = null;
		fForwardInit = true;

		setShellStyle(getShellStyle() ^ SWT.APPLICATION_MODAL | SWT.MODELESS);
		setBlockOnOpen(false);
	}

	/*
	 * @see org.eclipse.jface.dialogs.Dialog#isResizable()
	 * 
	 * @since 3.4
	 */
	protected boolean isResizable() {
		return true;
	}

	/**
	 * Returns this dialog's parent shell.
	 * 
	 * @return the dialog's parent shell
	 */
	public Shell getParentShell() {
		return super.getParentShell();
	}

	/**
	 * Returns <code>true</code> if control can be used.
	 * 
	 * @param control
	 *            the control to be checked
	 * @return <code>true</code> if control can be used
	 */
	private boolean okToUse(Control control) {
		return control != null && !control.isDisposed();
	}

	@Override
	public void create() {

		super.create();

		Shell shell = getShell();
		// shell.addShellListener(fActivationListener);

		// set help context
		/*
		 * PlatformUI .getWorkbench() .getHelpSystem() .setHelp(shell,
		 * IAbstractTextEditorHelpContextIds.FIND_REPLACE_DIALOG);
		 */
		// fill in combo contents
		// fFindField.removeModifyListener(fFindModifyListener);
		// updateCombo(fFindField, fFindHistory);
		// fFindField.addModifyListener(fFindModifyListener);
		// updateCombo(fReplaceField, fReplaceHistory);

		// get find string
		// initFindStringFromSelection();

		// set dialog position
		if (fDialogPositionInit != null)
			shell.setBounds(fDialogPositionInit);

		// shell.setText(EditorMessages.FindReplace_title);
		shell.setText("Find/Replace");
		// shell.setImage(null);
	}

	@Override
	protected Control createContents(Composite parent) {

		Composite panel = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.makeColumnsEqualWidth = true;
		panel.setLayout(layout);
		panel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Composite inputPanel = createInputPanel(panel);
		setGridData(inputPanel, SWT.FILL, true, SWT.TOP, false);

		Composite configPanel = createConfigPanel(panel);
		setGridData(configPanel, SWT.FILL, true, SWT.TOP, true);

		Composite buttonPanelB = createButtonSection(panel);
		setGridData(buttonPanelB, SWT.RIGHT, true, SWT.BOTTOM, false);

		updateButtonState();

		applyDialogFont(panel);

		return panel;

	}

	/**
	 * Creates the options configuration section of the find replace dialog.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the options configuration section
	 */
	private Composite createConfigPanel(Composite parent) {

		Composite panel = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = true;
		panel.setLayout(layout);

		Composite directionGroup = createDirectionGroup(panel);
		setGridData(directionGroup, SWT.FILL, true, SWT.FILL, false);

		/*
		 * Composite scopeGroup= createScopeGroup(panel);
		 * setGridData(scopeGroup, SWT.FILL, true, SWT.FILL, false);
		 * 
		 * Composite optionsGroup= createOptionsGroup(panel);
		 * setGridData(optionsGroup, SWT.FILL, true, SWT.FILL, true);
		 * ((GridData)optionsGroup.getLayoutData()).horizontalSpan= 2;
		 */
		return panel;
	}

	/**
	 * Creates the direction defining part of the options defining section of
	 * the find replace dialog.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the direction defining part
	 */
	private Composite createDirectionGroup(Composite parent) {

		Composite panel = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		panel.setLayout(layout);

		Group group = new Group(panel, SWT.SHADOW_ETCHED_IN);
		// group.setText(EditorMessages.FindReplace_Direction);
		group.setText("Direction");
		GridLayout groupLayout = new GridLayout();
		group.setLayout(groupLayout);
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		SelectionListener selectionListener = new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				// if (isIncrementalSearch() &&
				// !isRegExSearchAvailableAndChecked())
				// initIncrementalBaseLocation();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		};

		fForwardRadioButton = new Button(group, SWT.RADIO | SWT.LEFT);
		// fForwardRadioButton.setText(EditorMessages.FindReplace_ForwardRadioButton_label);
		fForwardRadioButton.setText("F&orward");
		setGridData(fForwardRadioButton, SWT.LEFT, false, SWT.CENTER, false);
		fForwardRadioButton.addSelectionListener(selectionListener);
		storeButtonWithMnemonicInMap(fForwardRadioButton);

		Button backwardRadioButton = new Button(group, SWT.RADIO | SWT.LEFT);
		// backwardRadioButton.setText(EditorMessages.FindReplace_BackwardRadioButton_label);
		backwardRadioButton.setText("&Backward");
		setGridData(backwardRadioButton, SWT.LEFT, false, SWT.CENTER, false);
		backwardRadioButton.addSelectionListener(selectionListener);
		storeButtonWithMnemonicInMap(backwardRadioButton);

		backwardRadioButton.setSelection(!fForwardInit);
		fForwardRadioButton.setSelection(fForwardInit);

		return panel;
	}

	/**
	 * Create the button section of the find/replace dialog.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the button section
	 */
	private Composite createButtonSection(Composite parent) {

		Composite panel = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = -2; // this is intended
		panel.setLayout(layout);

		fFindNextButton = makeButton(panel, "Fi&nd" /*
													 * EditorMessages.
													 * FindReplace_FindNextButton_label
													 */, 102, true,
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						/*
						 * if (isIncrementalSearch() &&
						 * !isRegExSearchAvailableAndChecked())
						 * initIncrementalBaseLocation();
						 * 
						 * fNeedsInitialFindBeforeReplace = false;;
						 */
						performSearch((e.stateMask == SWT.SHIFT)
								^ isForwardSearch());
						// updateFindHistory();
					}
				});
		setGridData(fFindNextButton, SWT.FILL, true, SWT.FILL, false);

		fReplaceFindButton = makeButton(panel, "Replace/Fin&d" /*
																 * EditorMessages.
																 * FindReplace_ReplaceFindButton_label
																 */, 103,
				false, new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						/*
						 * if (fNeedsInitialFindBeforeReplace)
						 * performSearch((e.stateMask == SWT.SHIFT) ^
						 * isForwardSearch()); if (performReplaceSelection())
						 * performSearch((e.stateMask == SWT.SHIFT) ^
						 * isForwardSearch()); updateFindAndReplaceHistory();
						 */
					}
				});
		setGridData(fReplaceFindButton, SWT.FILL, false, SWT.FILL, false);

		fReplaceSelectionButton = makeButton(panel, "&Replace" /*
																 * EditorMessages.
																 * FindReplace_ReplaceSelectionButton_label
																 */, 104,
				false, new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						/*
						 * if (fNeedsInitialFindBeforeReplace) performSearch();
						 * performReplaceSelection(); updateButtonState();
						 * updateFindAndReplaceHistory();
						 */
					}
				});
		setGridData(fReplaceSelectionButton, SWT.FILL, false, SWT.FILL, false);

		fReplaceAllButton = makeButton(panel, "Replace &All"/*
															 * EditorMessages.
															 * FindReplace_ReplaceAllButton_label
															 */, 105, false,
				new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						// performReplaceAll();
						// updateFindAndReplaceHistory();
					}
				});
		setGridData(fReplaceAllButton, SWT.FILL, true, SWT.FILL, false);

		// Make the all the buttons the same size as the Remove Selection
		// button.
		// fReplaceAllButton.setEnabled(isEditable());

		return panel;
	}

	/**
	 * Creates the panel where the user specifies the text to search for and the
	 * optional replacement text.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the input panel
	 */
	private Composite createInputPanel(Composite parent) {

		ModifyListener listener = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateButtonState();
			}
		};

		Composite panel = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		panel.setLayout(layout);

		Label findLabel = new Label(panel, SWT.LEFT);
		// findLabel.setText(EditorMessages.FindReplace_Find_label);
		findLabel.setText("&Find:");
		setGridData(findLabel, SWT.LEFT, false, SWT.CENTER, false);

		// Create the find content assist field
		ComboContentAdapter contentAdapter = new ComboContentAdapter();
		/*
		 * FindReplaceDocumentAdapterContentProposalProvider findProposer = new
		 * FindReplaceDocumentAdapterContentProposalProvider( true);
		 */
		fFindField = new Combo(panel, SWT.DROP_DOWN | SWT.BORDER);
		/*
		 * fContentAssistFindField = new ContentAssistCommandAdapter(fFindField,
		 * contentAdapter, findProposer,
		 * ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, new char[0],
		 * true);
		 */
		setGridData(fFindField, SWT.FILL, true, SWT.CENTER, false);
		addDecorationMargin(fFindField);
		// fFindField.addModifyListener(fFindModifyListener);

		fReplaceLabel = new Label(panel, SWT.LEFT);
		// fReplaceLabel.setText(EditorMessages.FindReplace_Replace_label);
		fReplaceLabel.setText("R&eplace with:");
		setGridData(fReplaceLabel, SWT.LEFT, false, SWT.CENTER, false);

		// Create the replace content assist field
		/*
		 * FindReplaceDocumentAdapterContentProposalProvider replaceProposer =
		 * new FindReplaceDocumentAdapterContentProposalProvider( false);
		 */
		fReplaceField = new Combo(panel, SWT.DROP_DOWN | SWT.BORDER);
		/*
		 * fContentAssistReplaceField = new ContentAssistCommandAdapter(
		 * fReplaceField, contentAdapter, replaceProposer,
		 * ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, new char[0],
		 * true);
		 */
		setGridData(fReplaceField, SWT.FILL, true, SWT.CENTER, false);
		addDecorationMargin(fReplaceField);
		fReplaceField.addModifyListener(listener);

		return panel;
	}

	// ------- UI creation ---------------------------------------

	/**
	 * Attaches the given layout specification to the <code>component</code>.
	 * 
	 * @param component
	 *            the component
	 * @param horizontalAlignment
	 *            horizontal alignment
	 * @param grabExcessHorizontalSpace
	 *            grab excess horizontal space
	 * @param verticalAlignment
	 *            vertical alignment
	 * @param grabExcessVerticalSpace
	 *            grab excess vertical space
	 */
	private void setGridData(Control component, int horizontalAlignment,
			boolean grabExcessHorizontalSpace, int verticalAlignment,
			boolean grabExcessVerticalSpace) {
		GridData gd;
		if (component instanceof Button
				&& (((Button) component).getStyle() & SWT.PUSH) != 0) {
			SWTUtil.setButtonDimensionHint((Button) component);
			gd = (GridData) component.getLayoutData();
		} else {
			gd = new GridData();
			component.setLayoutData(gd);
			gd.horizontalAlignment = horizontalAlignment;
			gd.grabExcessHorizontalSpace = grabExcessHorizontalSpace;
		}
		gd.verticalAlignment = verticalAlignment;
		gd.grabExcessVerticalSpace = grabExcessVerticalSpace;
	}

	/**
	 * Adds enough space in the control's layout data margin for the content
	 * assist decoration.
	 * 
	 * @param control
	 *            the control that needs a margin
	 * @since 3.3
	 */
	private void addDecorationMargin(Control control) {
		Object layoutData = control.getLayoutData();
		if (!(layoutData instanceof GridData))
			return;
		GridData gd = (GridData) layoutData;
		FieldDecoration dec = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(
						FieldDecorationRegistry.DEC_CONTENT_PROPOSAL);
		gd.horizontalIndent = dec.getImage().getBounds().width;
	}

	private void updateButtonState() {
		// TODO Auto-generated method stub

	}

	/**
	 * Creates a button.
	 * 
	 * @param parent
	 *            the parent control
	 * @param label
	 *            the button label
	 * @param id
	 *            the button id
	 * @param dfltButton
	 *            is this button the default button
	 * @param listener
	 *            a button pressed listener
	 * @return the new button
	 */
	private Button makeButton(Composite parent, String label, int id,
			boolean dfltButton, SelectionListener listener) {
		Button button = createButton(parent, id, label, dfltButton);
		button.addSelectionListener(listener);
		storeButtonWithMnemonicInMap(button);
		return button;
	}

	/**
	 * Stores the button and its mnemonic in {@link #fMnemonicButtonMap}.
	 * 
	 * @param button
	 *            button whose mnemonic has to be stored
	 * @since 3.7
	 */
	private void storeButtonWithMnemonicInMap(Button button) {
		char mnemonic = LegacyActionTools.extractMnemonic(button.getText());
		if (mnemonic != LegacyActionTools.MNEMONIC_NONE)
			fMnemonicButtonMap.put(
					new Character(Character.toLowerCase(mnemonic)), button);
	}

	// ------- history ---------------------------------------

	/**
	 * Retrieves and returns the option case sensitivity from the appropriate
	 * check box.
	 * 
	 * @return <code>true</code> if case sensitive
	 */
	/*
	 * private boolean isCaseSensitiveSearch() { if (okToUse(fCaseCheckBox)) {
	 * return fCaseCheckBox.getSelection(); } return fCaseInit; }
	 */

	/**
	 * Retrieves and returns the regEx option from the appropriate check box.
	 * 
	 * @return <code>true</code> if case sensitive
	 * @since 3.0
	 */
	/*
	 * private boolean isRegExSearch() { if (okToUse(fIsRegExCheckBox)) { return
	 * fIsRegExCheckBox.getSelection(); } return fIsRegExInit; }
	 */

	/**
	 * If the target supports regular expressions search retrieves and returns
	 * regEx option from appropriate check box.
	 * 
	 * @return <code>true</code> if regEx is available and checked
	 * @since 3.0
	 */
	/*
	 * private boolean isRegExSearchAvailableAndChecked() { if
	 * (okToUse(fIsRegExCheckBox)) { return fIsTargetSupportingRegEx &&
	 * fIsRegExCheckBox.getSelection(); } return fIsRegExInit; }
	 */

	/**
	 * Retrieves and returns the option search direction from the appropriate
	 * check box.
	 * 
	 * @return <code>true</code> if searching forward
	 */
	private boolean isForwardSearch() {
		if (okToUse(fForwardRadioButton)) {
			return fForwardRadioButton.getSelection();
		}
		return fForwardInit;
	}

	/**
	 * Updates this dialog because of a different target.
	 * 
	 * @param target
	 *            the new target
	 * @param isTargetEditable
	 *            <code>true</code> if the new target can be modified
	 * @param initializeFindString
	 *            <code>true</code> if the find string of this dialog should be
	 *            initialized based on the viewer's selection
	 * @since 2.0
	 */
	public void updateTarget(IFindReplaceTarget target,
			boolean isTargetEditable, boolean initializeFindString) {
		this.target = target;
	}

	/**
	 * Locates the user's findString in the text of the target.
	 */
	private void performSearch() {
		performSearch(isForwardSearch());
	}

	/**
	 * Locates the user's findString in the text of the target.
	 * 
	 * @param forwardSearch
	 *            <code>true</code> if searching forwards, <code>false</code>
	 *            otherwise
	 * @since 3.7
	 */
	private void performSearch(boolean forwardSearch) {
		// performSearch(isIncrementalSearch() &&
		// !isRegExSearchAvailableAndChecked(), true, forwardSearch);
		performSearch(true, true, forwardSearch);
	}

	/**
	 * Locates the user's findString in the text of the target.
	 * 
	 * @param mustInitIncrementalBaseLocation
	 *            <code>true</code> if base location must be initialized
	 * @param beep
	 *            if <code>true</code> beeps when search does not find a match
	 *            or needs to wrap
	 * @param forwardSearch
	 *            the search direction
	 * @since 3.0
	 */
	private void performSearch(boolean mustInitIncrementalBaseLocation,
			boolean beep, boolean forwardSearch) {

		String findString = getFindString();
		target.search(findString, !forwardSearch);
		/*
		 * if (mustInitIncrementalBaseLocation) initIncrementalBaseLocation();
		 * 
		 * String findString= getFindString(); boolean somethingFound= false;
		 * 
		 * if (findString != null && findString.length() > 0) {
		 * 
		 * try { somethingFound= findNext(findString, forwardSearch,
		 * isCaseSensitiveSearch(), isWrapSearch(), isWholeWordSearch(),
		 * isIncrementalSearch() && !isRegExSearchAvailableAndChecked(),
		 * isRegExSearchAvailableAndChecked(), beep); } catch
		 * (PatternSyntaxException ex) { statusError(ex.getLocalizedMessage());
		 * } catch (IllegalStateException ex) { // we don't keep state in this
		 * dialog } } writeSelection(); updateButtonState(!somethingFound);
		 */
	}

	// ------- accessors ---------------------------------------

	/**
	 * Retrieves the string to search for from the appropriate text input field
	 * and returns it.
	 * 
	 * @return the search string
	 */
	private String getFindString() {
		if (okToUse(fFindField)) {
			return fFindField.getText();
		}
		return ""; //$NON-NLS-1$
	}

}
