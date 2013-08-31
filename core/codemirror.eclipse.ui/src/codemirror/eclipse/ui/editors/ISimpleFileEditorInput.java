package codemirror.eclipse.ui.editors;

import java.io.File;

import org.eclipse.ui.IEditorInput;

public interface ISimpleFileEditorInput extends IEditorInput {

	public File getFile();
}
