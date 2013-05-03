package codemirror.eclipse.ui.operations;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;

public interface ICMOperation {

	boolean isAvailable(IEditorInput editorInput);
	
	void saveCM(String text, IEditorInput editorInput, IProgressMonitor monitor)
			throws IOException, CoreException;

	String loadCM(IEditorInput editorInput) throws IOException, CoreException;

}
