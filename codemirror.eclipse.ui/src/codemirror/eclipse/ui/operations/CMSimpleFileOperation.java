package codemirror.eclipse.ui.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;


import codemirror.eclipse.ui.editors.ISimpleFileEditorInput;
import codemirror.eclipse.ui.utils.IOUtils;

public class CMSimpleFileOperation implements ICMOperation {

	public boolean isAvailable(IEditorInput editorInput) {
		return editorInput instanceof ISimpleFileEditorInput;
	}

	public void saveCM(String text, IEditorInput editorInput,
			IProgressMonitor monitor) throws IOException, CoreException {
		File file = ((ISimpleFileEditorInput) editorInput).getFile();
		saveCM(text, file, monitor);
	}

	public void saveCM(String text, File file, IProgressMonitor monitor)
			throws FileNotFoundException, IOException {
		IOUtils.write(text, new FileOutputStream(file));
	}

	public String loadCM(IEditorInput editorInput) throws IOException,
			CoreException {
		File file = ((ISimpleFileEditorInput) editorInput).getFile();
		return loadCM(file);
	}

	public String loadCM(File file) throws IOException, FileNotFoundException {
		return IOUtils.toString(new FileInputStream(file));
	}

}
