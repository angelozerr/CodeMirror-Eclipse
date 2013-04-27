package codemirror.eclipse.ui.viewers.query;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import codemirror.eclipse.swt.xquery.Variable;

public class VariableValueEditingSupport extends EditingSupport {

	private final TextCellEditor editor;

	public VariableValueEditingSupport(ColumnViewer viewer) {
		super(viewer);
		this.editor = new TextCellEditor((Composite) viewer.getControl());
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		String value = ((Variable) element).getValue();
		return value != null ? value : "";
	}

	@Override
	protected void setValue(Object element, Object value) {
		((Variable) element).setValue(String.valueOf(value));
		getViewer().update(element, null);
	}

}
