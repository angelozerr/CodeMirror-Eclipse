package codemirror.eclipse.ui.json.editors;

import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.swt.json.builder.CMJsonBuilder;
import codemirror.eclipse.ui.editors.CMEditorPart;
import codemirror.eclipse.ui.resources.CMResourcesRegistry;

public class JsonEditor extends CMEditorPart {

	public JsonEditor() {		
		//super(CMResourcesRegistry.getRegistry().getURL(
			//	CMResourcesConstants.JSON_HTML));
		
		super(new CMJsonBuilder(CMResourcesRegistry.getRegistry().getURL(""),
				false));
	}

	public IValidator getValidator() {
		return null;
	}

}
