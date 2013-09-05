package codemirror.eclipse.ui.sql.editors;

import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.swt.builder.CMBuilderRegistry;
import codemirror.eclipse.swt.sql.builder.SQLMode;
import codemirror.eclipse.ui.editors.CMEditorPart;

public class SQLEditor extends CMEditorPart {

	public SQLEditor() {
		super(CMBuilderRegistry.getInstance().getBuilder(SQLMode.INSTANCE));
	}

	public IValidator getValidator() {
		return null;
	}

}
