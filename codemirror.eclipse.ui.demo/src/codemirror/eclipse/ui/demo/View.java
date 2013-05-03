package codemirror.eclipse.ui.demo;

import java.io.File;
import java.io.IOException;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import codemirror.eclipse.ui.editors.SimpleFileEditorInput;

public class View extends ViewPart implements IDoubleClickListener {
	public static final String ID = "codemirror.eclipse.ui.demo.view";

	private TreeViewer viewer;

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			return ((File) parent).listFiles();
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			return ((File) parentElement).listFiles();
		}

		@Override
		public Object getParent(Object element) {
			return ((File) element).getParentFile();
		}

		@Override
		public boolean hasChildren(Object element) {
			return ((File) element).isDirectory();
		}
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		@Override
		public String getText(Object element) {
			return ((File) element).getName();
		}

		public String getColumnText(Object obj, int index) {
			return ((File) obj).getName();
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			IEditorDescriptor descriptor = getDescriptor((File)obj);
			if (descriptor != null) {
				return descriptor.getImageDescriptor().createImage();
			}			
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		int style = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL;
		viewer = new TreeViewer(parent, style);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		try {
			viewer.setInput(Activator.getDefault().getResourceBaseDir());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		viewer.addDoubleClickListener(this);
		viewer.expandAll();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@Override
	public void doubleClick(DoubleClickEvent event) {
		ISelection selection = event.getSelection();
		if (selection != null && selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			File element = (File) structuredSelection.getFirstElement();
			if (element.isFile()) {

				IEditorInput editorInput = new SimpleFileEditorInput(element);
				IWorkbenchWindow window = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow();
				IWorkbenchPage page = window.getActivePage();

				IEditorDescriptor descriptor = getDescriptor(element);
				if (descriptor != null) {
					try {
						page.openEditor(editorInput, descriptor.getId(), true);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
	}

	private IEditorDescriptor getDescriptor(File file) {
		IEditorDescriptor[] descriptors = PlatformUI.getWorkbench()
				.getEditorRegistry().getEditors(file.getName());
		if (descriptors != null && descriptors.length > 0) {
			return descriptors[0];
		}
		return null;
	}

}