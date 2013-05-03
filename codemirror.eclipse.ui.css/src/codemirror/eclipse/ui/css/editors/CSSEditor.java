package codemirror.eclipse.ui.css.editors;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.ui.editors.CMEditorPart;
import codemirror.eclipse.ui.resources.CMResourcesRegistry;

public class CSSEditor extends CMEditorPart {

	public CSSEditor() {		
		super(CMResourcesRegistry.getRegistry().getURL(
				CMResourcesConstants.CSS_HTML));
	}

	public IValidator getValidator() {
		return null;
	}

}
