package codemirror.eclipse.ui.xquery.internal.editors;

import java.util.Collection;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import codemirror.eclipse.swt.xquery.addon.variables.Variable;

public class VariablesTreeViewer extends TreeViewer {

	private final XQueryVariablesOutlinePage outline;
	public VariablesTreeViewer(Composite parent, int style, XQueryVariablesOutlinePage outline) {
		super(parent, style);
		this.outline = outline;
	}
	
	@Override
	public void update(Object element, String[] properties) {
		super.update(element, properties);
		expandTreeAndRefreshPreview();
	}

	@Override
	public void refresh(Object element) {
		super.refresh(element);
		expandTreeAndRefreshPreview();
	}
	
	public void expandTreeAndRefreshPreview() {
		super.expandAll();
		outline.refreshPreview((Collection<Variable>) super.getInput());
	}

}
