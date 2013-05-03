package codemirror.eclipse.ui.json.editors;

import codemirror.eclipse.resources.CMResources;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.ui.editors.CMEditorPart;

public abstract class AbstractJsonEditor extends CMEditorPart {

	public AbstractJsonEditor() {
		super(CMResources.getJsonResource());
	}

	public IValidator getValidator() {
		return null;
	}

}
