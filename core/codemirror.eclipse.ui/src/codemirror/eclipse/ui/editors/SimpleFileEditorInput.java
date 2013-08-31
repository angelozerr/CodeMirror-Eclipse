package codemirror.eclipse.ui.editors;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;

public class SimpleFileEditorInput implements ISimpleFileEditorInput,
		IPathEditorInput {

	private final File file;

	public SimpleFileEditorInput(File file) {
		if (file == null)
			throw new IllegalArgumentException("file must not be null!"); //$NON-NLS-1$

		this.file = file;
	}

	public boolean exists() {
		return file.exists();
	}

	public File getFile() {
		return file;
	}

	public String getName() {
		return file.getName();
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public IPersistableElement getPersistable() {
		return null; // no restore!
	}

	public String getToolTipText() {
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

	public IPath getPath() {
		try {
			String path = file.getCanonicalPath();
			return new Path(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int hashCode() {
		return file.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o instanceof SimpleFileEditorInput) {
			SimpleFileEditorInput input = (SimpleFileEditorInput) o;
			return file.equals(input.getFile());
		}

		return false;
	}

	protected boolean saved = true;

	public boolean isSaved() {
		if (!exists())
			return false;

		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}
}
