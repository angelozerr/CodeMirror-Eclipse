package codemirror.eclipse.ui.xquery.viewers;

import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import codemirror.eclipse.swt.xquery.addon.variables.Variable;

public class VariableContentProvider implements ITreeContentProvider {

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	public Object[] getElements(Object inputElement) {
		return ((Collection<Variable>) inputElement).toArray();
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Variable) {
			Variable variable = (Variable) parentElement;
			if (variable.isArray()) {
				return variable.getValues().toArray();
			}
		}
		return null;
	}

	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof Variable) {
			return ((Variable) element).isArray();
		}
		return false;
	}

}
