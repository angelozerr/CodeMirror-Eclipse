package codemirror.eclipse.ui.operations;

import org.eclipse.ui.IEditorInput;

public abstract class AbstractCMOperation implements ICMOperation {

	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator"); //$NON-NLS-1$

	public String getLineSeparator(IEditorInput editorInput) {
		return LINE_SEPARATOR;
	}
}
