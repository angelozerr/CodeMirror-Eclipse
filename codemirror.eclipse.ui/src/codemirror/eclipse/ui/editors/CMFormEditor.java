package codemirror.eclipse.ui.editors;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

public abstract class CMFormEditor extends FormEditor {

	public void contributeToToolbar(IToolBarManager manager) {

	}

	@Override
	public void init(IEditorSite site, IEditorInput editorInput)
			throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException(
					"Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
		super.setPartName(((IFileEditorInput) editorInput).getFile().getName());
	}
}
