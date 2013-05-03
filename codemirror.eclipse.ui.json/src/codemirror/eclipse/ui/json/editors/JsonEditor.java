package codemirror.eclipse.ui.json.editors;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.ui.editors.CMEditorPart;
import codemirror.eclipse.ui.resources.CMResourcesRegistry;

public class JsonEditor extends CMEditorPart {

	public JsonEditor() {		
		super(CMResourcesRegistry.getRegistry().getURL(
				CMResourcesConstants.JSON_HTML));
	}

	public IValidator getValidator() {
		return null;
	}

}
