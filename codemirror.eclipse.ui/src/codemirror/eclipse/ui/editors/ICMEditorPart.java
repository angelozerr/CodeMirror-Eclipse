package codemirror.eclipse.ui.editors;

import org.eclipse.core.resources.IFile;

import codemirror.eclipse.swt.IValidator;


public interface ICMEditorPart {

	String getURL();

	IFile getFile();

	IValidator getValidator();

	void editorDirtyStateChanged();

}
