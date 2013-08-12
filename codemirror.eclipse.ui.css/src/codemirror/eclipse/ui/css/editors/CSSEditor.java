package codemirror.eclipse.ui.css.editors;

import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.swt.css.builder.CMCSSBuilder;
import codemirror.eclipse.ui.editors.CMEditorPart;
import codemirror.eclipse.ui.resources.CMResourcesRegistry;

public class CSSEditor extends CMEditorPart {

	public CSSEditor() {
		/*
		 * super(CMResourcesRegistry.getRegistry().getURL(
		 * CMResourcesConstants.CSS_HTML));
		 */
		super(new CMCSSBuilder(CMResourcesRegistry.getRegistry().getURL("")));
	}

	public IValidator getValidator() {
		return null;
	}

}
