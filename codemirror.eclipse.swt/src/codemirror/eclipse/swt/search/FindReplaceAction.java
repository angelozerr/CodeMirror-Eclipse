package codemirror.eclipse.swt.search;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Shell;

import codemirror.eclipse.swt.CMControl;

public class FindReplaceAction extends Action {

	/** The action's target */
	private IFindReplaceTarget fTarget;

	/**
	 * The shell to use if the action is created with a shell.
	 * 
	 * @since 3.3
	 */
	private Shell fShell;

	/**
	 * Represents the "global" find/replace dialog. It tracks the active part
	 * and retargets the find/replace dialog accordingly. The find/replace
	 * target is retrieved from the active part using
	 * <code>getAdapter(IFindReplaceTarget.class)</code>.
	 * <p>
	 * The stub has the same life cycle as the find/replace dialog.
	 * </p>
	 * <p>
	 * If no IWorkbenchPart is available a Shell must be provided In this case
	 * the IFindReplaceTarget will never change.
	 * </p>
	 */
	static class FindReplaceDialogStub implements DisposeListener {

		/** The workbench part */
		/*
		 * private IWorkbenchPart fPart; /** The previous workbench part
		 */
		// private IWorkbenchPart fPreviousPart;
		/** The previous find/replace target */
		// private IFindReplaceTarget fPreviousTarget;

		/** The workbench window */
		// private IWorkbenchWindow fWindow;
		/** The find/replace dialog */
		private FindReplaceDialog fDialog;

		/**
		 * Creates a new find/replace dialog accessor anchored at the given part
		 * site.
		 * 
		 * @param site
		 *            the part site
		 */
		/*
		 * public FindReplaceDialogStub(IWorkbenchPartSite site) {
		 * this(site.getShell()); fWindow = site.getWorkbenchWindow();
		 * IPartService service = fWindow.getPartService();
		 * service.addPartListener(this);
		 * partActivated(service.getActivePart()); }
		 */

		/**
		 * Creates a new find/replace dialog accessor anchored at the given
		 * shell.
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

		/**
		 * Returns the find/replace dialog.
		 * 
		 * @return the find/replace dialog
		 */
		public FindReplaceDialog getDialog() {
			return fDialog;
		}

		/*
		 * private void partActivated(IWorkbenchPart part) { IFindReplaceTarget
		 * target = part == null ? null : (IFindReplaceTarget) part
		 * .getAdapter(IFindReplaceTarget.class); fPreviousPart = fPart; fPart =
		 * target == null ? null : part;
		 * 
		 * if (fPreviousTarget != target) { fPreviousTarget = target; if
		 * (fDialog != null) { boolean isEditable = false; if (fPart instanceof
		 * ITextEditorExtension2) { ITextEditorExtension2 extension =
		 * (ITextEditorExtension2) fPart; isEditable =
		 * extension.isEditorInputModifiable(); } else if (target != null)
		 * isEditable = target.isEditable(); fDialog.updateTarget(target,
		 * isEditable, false); } } }
		 */

		/*
		 * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		/*
		 * public void partActivated(IWorkbenchPartReference partRef) {
		 * partActivated(partRef.getPart(true)); }
		 */

		/*
		 * @see
		 * org.eclipse.jface.dialogs.IPageChangedListener#pageChanged(org.eclipse
		 * .jface.dialogs.PageChangedEvent)
		 * 
		 * @since 3.5
		 */
		/*
		 * public void pageChanged(PageChangedEvent event) { if
		 * (event.getSource() instanceof IWorkbenchPart)
		 * partActivated((IWorkbenchPart) event.getSource()); }
		 */

		/*
		 * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		/*
		 * public void partClosed(IWorkbenchPartReference partRef) {
		 * IWorkbenchPart part = partRef.getPart(true); if (part ==
		 * fPreviousPart) { fPreviousPart = null; fPreviousTarget = null; }
		 * 
		 * if (part == fPart) partActivated((IWorkbenchPart) null); }
		 */

		/*
		 * @see DisposeListener#widgetDisposed(DisposeEvent)
		 */
		public void widgetDisposed(DisposeEvent event) {

			if (fgFindReplaceDialogStub == this)
				fgFindReplaceDialogStub = null;

			if (fgFindReplaceDialogStubShell == this)
				fgFindReplaceDialogStubShell = null;

			/*
			 * if (fWindow != null) {
			 * fWindow.getPartService().removePartListener(this); fWindow =
			 * null; }
			 */
			fDialog = null;
			/*
			 * fPart = null; fPreviousPart = null; fPreviousTarget = null;
			 */
		}

		/**
		 * Checks if the dialogs shell is the same as the given
		 * <code>shell</code> and if not clears the stub and closes the dialog.
		 * 
		 * @param shell
		 *            the shell check
		 * @since 3.3
		 */
		public void checkShell(Shell shell) {
			if (fDialog != null && shell != fDialog.getParentShell()) {
				if (fgFindReplaceDialogStub == this)
					fgFindReplaceDialogStub = null;

				if (fgFindReplaceDialogStubShell == this)
					fgFindReplaceDialogStubShell = null;

				fDialog.close();
			}
		}

	}

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

	public FindReplaceAction(Shell shell, IFindReplaceTarget target) {
		// super(bundle, prefix);
		// Assert.isLegal(target != null && shell != null);
		fTarget = target;
		fShell = shell;
		update();
	}

	@Override
	public void run() {
		if (fTarget == null)
			return;

		final FindReplaceDialog dialog;
		final boolean isEditable;

		if (fShell == null) {
			/*if (fgFindReplaceDialogStub != null) {
				Shell shell = fWorkbenchPart.getSite().getShell();
				fgFindReplaceDialogStub.checkShell(shell);
			}
			if (fgFindReplaceDialogStub == null)
				fgFindReplaceDialogStub = new FindReplaceDialogStub(
						fWorkbenchPart.getSite());

			if (fWorkbenchPart instanceof ITextEditorExtension2)
				isEditable = ((ITextEditorExtension2) fWorkbenchPart)
						.isEditorInputModifiable();
			else
				isEditable = fTarget.isEditable();
*/
			dialog = fgFindReplaceDialogStub.getDialog();
			isEditable = false;
		} else {
			if (fgFindReplaceDialogStubShell != null) {
				fgFindReplaceDialogStubShell.checkShell(fShell);
			}
			if (fgFindReplaceDialogStubShell == null)
				fgFindReplaceDialogStubShell = new FindReplaceDialogStub(fShell);

			isEditable = fTarget.isEditable();
			dialog = fgFindReplaceDialogStubShell.getDialog();
		}

		dialog.updateTarget(fTarget, isEditable, true);
		dialog.open();

	}

	private void update() {
		// TODO Auto-generated method stub

	}
}
