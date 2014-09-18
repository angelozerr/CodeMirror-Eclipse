package codemirror.eclipse.ui.cypher.editors;

import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.cypher.builder.CypherMode;
import codemirror.eclipse.ui.editors.CMEditorPart;

public class CypherEditor extends CMEditorPart {

	public CypherEditor() {
		super(CMBuilderRegistry.getInstance().getBuilder(CypherMode.INSTANCE));
	}

	public IValidator getValidator() {
		return null;
	}

}
