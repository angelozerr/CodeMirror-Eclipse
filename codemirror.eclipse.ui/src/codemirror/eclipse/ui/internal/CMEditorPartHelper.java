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
package codemirror.eclipse.ui.internal;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.IDirtyListener;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.ui.editors.ICMEditorPart;
import codemirror.eclipse.ui.internal.extensions.CMOperationsRegistry;
import codemirror.eclipse.ui.operations.ICMOperation;

/**
 * Helper to load and save content with {@link CMControl}.
 * 
 */
public class CMEditorPartHelper {

	public static CMControl createCM(final ICMEditorPart part, String text,
			Composite parent) {
		CMControl cm = part.createCM(part.getURL(), parent, SWT.NONE);
		cm.setText(text);
		cm.setLineSeparator(part.getLineSeparator());
		IValidator validator = part.getValidator();
		if (validator != null) {
			cm.setValidator(validator);
		}
		cm.setLayoutData(new GridData(GridData.FILL_BOTH));
		cm.addDirtyListener(new IDirtyListener() {
			public void dirtyChanged(boolean dirty) {
				part.editorDirtyStateChanged();
			}
		});
		return cm;
	}

	public static void saveCM(ICMEditorPart part, IProgressMonitor monitor)
			throws IOException, CoreException {
		CMControl cm = part.getCMControl();
		part.saveCM(cm.getText(), monitor);
		cm.setDirty(false);
	}

	public static ICMOperation getOperation(IEditorInput editorInput) {
		ICMOperation operation = CMOperationsRegistry.getRegistry()
				.getOperation(editorInput);
		if (operation == null) {
			throw new RuntimeException(
					"Imposible to load/save CM Editor. Cannot find CM operation");
		}
		return operation;
	}

	/**
	 * Presents an error dialog to the user when a problem happens during save.
	 */
	public static void openSaveErrorDialog(Shell shell, String title,
			String message, CoreException exception) {
		ErrorDialog.openError(shell, title, message, exception.getStatus());
	}

	/**
	 * Presents an error dialog to the user when a problem happens during save.
	 */
	public static void openSaveErrorDialog(Shell shell, Exception e) {
		CoreException coreException = null;
		if (e instanceof CoreException) {
			coreException = (CoreException) e;
		} else {
			IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.saveErrorMessage,
					e);
			coreException = new CoreException(status);
		}
		openSaveErrorDialog(shell, Messages.saveErrorTitle, Messages.saveErrorMessage, coreException);
	}

}
