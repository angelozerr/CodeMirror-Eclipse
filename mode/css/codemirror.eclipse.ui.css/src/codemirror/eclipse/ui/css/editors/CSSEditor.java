package codemirror.eclipse.ui.css.editors;

import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.css.builder.CSSMode;
import codemirror.eclipse.ui.editors.CMEditorPart;

public class CSSEditor extends CMEditorPart {

	public CSSEditor() {
		/*
		 * super(CMResourcesRegistry.getRegistry().getURL(
		 * CMResourcesConstants.CSS_HTML));
		 */
		super(CMBuilderRegistry.getInstance().getBuilder(CSSMode.INSTANCE));
	}

	public IValidator getValidator() {
		return null;
	}

}
