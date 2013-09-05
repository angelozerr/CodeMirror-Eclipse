package codemirror.eclipse.ui.javascript.editors;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.ui.editors.CMEditorPart;
import codemirror.eclipse.ui.resources.CMResourcesRegistry;

public class JavascriptEditor extends CMEditorPart {

	public JavascriptEditor() {		
		super(CMResourcesRegistry.getRegistry().getURL(
				CMResourcesConstants.JAVASCRIPT_HTML));
	}

	public IValidator getValidator() {
		return null;
	}

}
