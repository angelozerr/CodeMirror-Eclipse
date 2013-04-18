package codemirror.eclipse.ui.internal;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.DirtyListener;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.ui.editors.ICMEditorPart;
import codemirror.eclipse.ui.internal.org.apache.commons.io.IOUtils;

public class CMEditorPartHelper {

	public static CMControl createCM(final ICMEditorPart part, Composite parent) {
		CMControl cm = new CMControl(part.getURL(), parent, SWT.NONE);
		try {
			String text = IOUtils.toString(part.getFile().getContents());
			cm.setText(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IValidator validator = part.getValidator();
		if (validator != null) {
			cm.setValidator(validator);
		}
		cm.setLayoutData(new GridData(GridData.FILL_BOTH));
		cm.addDirtyListener(new DirtyListener() {
			public void dirtyChanged(boolean dirty) {
				part.editorDirtyStateChanged();
			}
		});
		return cm;
	}

	public static void saveCM(ICMEditorPart part, IProgressMonitor monitor) {
		CMControl cm = part.getCMControl();
		IFile file = part.getFile();
		try {
			file.setContents(IOUtils.toInputStream(cm.getText()), true, false,
					monitor);
		} catch (CoreException e) {
			// TODoO Auto-generated catch block
			e.printStackTrace();
		}

		part.editorDirtyStateChanged();
		cm.setDirty(false);
	}

}
