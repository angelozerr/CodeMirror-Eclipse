package codemirror.eclipse.ui.xquery.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import codemirror.eclipse.swt.xquery.addon.variables.ValueHolder;
import codemirror.eclipse.swt.xquery.addon.variables.Variable;

public class VariableValueEditingSupport extends EditingSupport {

	private final TextCellEditor editor;

	private final ComboBoxViewerCellEditor booleanEditor;

	public VariableValueEditingSupport(ColumnViewer viewer) {
		super(viewer);
		this.editor = new TextCellEditor((Composite) viewer.getControl());
		this.booleanEditor = new ComboBoxViewerCellEditor(
				(Composite) viewer.getControl(), SWT.READ_ONLY);
		booleanEditor.setContenProvider(ArrayContentProvider.getInstance());
		booleanEditor.setLabelProvider(new LabelProvider());
		booleanEditor.setInput(new String[] {"", "false()", "true()"});
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		if (element instanceof ValueHolder) {
			Variable variable = ((ValueHolder) element).getVariable();
			if (variable.isBoolean()) {
				return booleanEditor;
			}
		}
		return editor;
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		ValueHolder valueHolder = ((ValueHolder) element);
		String value = valueHolder.getValue();
		/*
		 * if (valueHolder.getVariable().isBoolean()) { return
		 * "true()".equals(value); }
		 */
		return value != null ? value : "";
	}

	@Override
	protected void setValue(Object element, Object value) {
		ValueHolder valueHolder = ((ValueHolder) element);
		valueHolder.setValue(String.valueOf(value));
		/*
		 * if (valueHolder.getVariable().isBoolean() && value instanceof
		 * Boolean) { if ((Boolean)value) { valueHolder.setValue("true()"); }
		 * else { valueHolder.setValue("false()"); } } else {
		 * valueHolder.setValue(String.valueOf(value)); }
		 */
		getViewer().update(element, null);
	}

}
