package codemirror.eclipse.ui.json.editors;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

public class JsonEditor extends AbstractJsonEditor {

	@Override
	public void init(IEditorSite site, IEditorInput editorInput)
			throws PartInitException {
		/*if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException(
					"Invalid Input: Must be IFileEditorInput");*/
		super.init(site, editorInput);	
	}
}
