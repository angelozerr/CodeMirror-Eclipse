package codemirror.eclipse.ui.xml.editors;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.ui.editors.CMEditorPart;
import codemirror.eclipse.ui.resources.CMResourcesRegistry;

public class XMLEditor extends CMEditorPart {

	public XMLEditor() {		
		super(CMResourcesRegistry.getRegistry().getURL(
				CMResourcesConstants.XML_HTML));
	}

	public IValidator getValidator() {
		return null;
	}

}
