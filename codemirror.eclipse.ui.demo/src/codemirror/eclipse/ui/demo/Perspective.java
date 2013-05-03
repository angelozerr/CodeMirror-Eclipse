package codemirror.eclipse.ui.demo;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Configures the perspective layout. This class is contributed through the
 * plugin.xml.
 */
public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		/*
		 * String editorArea = layout.getEditorArea();
		 * layout.setEditorAreaVisible(false); layout.setFixed(true);
		 * 
		 * layout.addStandaloneView(View.ID, false, IPageLayout.LEFT, 1.0f,
		 * editorArea);
		 */

		layout.setEditorAreaVisible(true);
		layout.setFixed(true);

		String editorArea = layout.getEditorArea();
		IFolderLayout leftFolder = layout.createFolder("left",
				IPageLayout.LEFT, 0.22f, editorArea);
		leftFolder.addView(FileExplorer.ID);

	}
}
