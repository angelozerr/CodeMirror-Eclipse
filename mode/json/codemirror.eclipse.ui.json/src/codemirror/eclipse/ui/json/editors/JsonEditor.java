package codemirror.eclipse.ui.json.editors;

import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.json.builder.JsonMode;
import codemirror.eclipse.ui.editors.CMEditorPart;

public class JsonEditor extends CMEditorPart {

	public JsonEditor() {
		// super(CMResourcesRegistry.getRegistry().getURL(
		// CMResourcesConstants.JSON_HTML));
		super(CMBuilderRegistry.getInstance().getBuilder(JsonMode.INSTANCE));
	}

	public IValidator getValidator() {
		return null;
	}

}
