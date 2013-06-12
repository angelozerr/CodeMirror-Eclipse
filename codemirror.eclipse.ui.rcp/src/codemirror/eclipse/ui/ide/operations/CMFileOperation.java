package codemirror.eclipse.ui.ide.operations;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

import codemirror.eclipse.ui.operations.ICMOperation;
import codemirror.eclipse.ui.utils.IOUtils;

public class CMFileOperation implements ICMOperation {

	public boolean isAvailable(IEditorInput editorInput) {
		return editorInput instanceof IFileEditorInput;
	}

	public void saveCM(String text, IEditorInput editorInput,
			IProgressMonitor monitor) throws IOException, CoreException {
		IFile file = ((IFileEditorInput) editorInput).getFile();
		saveCM(text, file, monitor);
	}

	public void saveCM(String text, IFile file, IProgressMonitor monitor)
			throws IOException, CoreException {
		file.setContents(IOUtils.toInputStream(text, file.getCharset()), true,
				false, monitor);
	}

	public String loadCM(IEditorInput editorInput) throws IOException,
			CoreException {
		IFile file = ((IFileEditorInput) editorInput).getFile();
		return loadCM(file);
	}

	public String loadCM(IFile file) throws IOException, CoreException {
		InputStream in = file.getContents();
		try {
			return IOUtils.toString(in, file.getCharset());
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
