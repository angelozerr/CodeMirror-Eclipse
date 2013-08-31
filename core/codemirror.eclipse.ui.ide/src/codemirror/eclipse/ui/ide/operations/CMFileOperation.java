package codemirror.eclipse.ui.ide.operations;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

import codemirror.eclipse.ui.operations.AbstractCMOperation;
import codemirror.eclipse.ui.utils.IOUtils;

public class CMFileOperation extends AbstractCMOperation {

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

	@Override
	public String getLineSeparator(IEditorInput editorInput) {
		if (Platform.isRunning()) {
			String lineSeparator = null;
			IFile file = ((IFileEditorInput) editorInput).getFile();
			IProject project = file.getProject();
			// line delimiter in project preference
			IScopeContext[] scopeContext;
			if (project != null) {
				scopeContext = new IScopeContext[] { new ProjectScope(project) };
				lineSeparator = Platform.getPreferencesService().getString(
						Platform.PI_RUNTIME, Platform.PREF_LINE_SEPARATOR,
						null, scopeContext);
				if (lineSeparator != null)
					return lineSeparator;
			}

			// line delimiter in workspace preference
			scopeContext = new IScopeContext[] { InstanceScope.INSTANCE };
			lineSeparator = Platform.getPreferencesService().getString(
					Platform.PI_RUNTIME, Platform.PREF_LINE_SEPARATOR, null,
					scopeContext);
			if (lineSeparator != null)
				return lineSeparator;
		}
		return super.getLineSeparator(editorInput);
	}

}
