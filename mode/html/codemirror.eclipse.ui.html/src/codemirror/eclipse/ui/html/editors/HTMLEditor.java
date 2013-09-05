package codemirror.eclipse.ui.html.editors;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.ui.editors.CMEditorPart;
import codemirror.eclipse.ui.resources.CMResourcesRegistry;

public class HTMLEditor extends CMEditorPart {

	public HTMLEditor() {		
		super(CMResourcesRegistry.getRegistry().getURL(
				CMResourcesConstants.HTML5_HTML));
	}

	public IValidator getValidator() {
		return null;
	}

}
