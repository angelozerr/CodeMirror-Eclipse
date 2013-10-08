package codemirror.eclipse.ui.xquery.viewers;

import java.util.HashMap;
import java.util.Map;

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

	private final CellEditor booleanEditor;

	private final CellEditor dateTimeEditor;

	private final CellEditor xmlEditor;

	private final Map<String, CellEditor> editorsMap;

	public VariableValueEditingSupport(ColumnViewer viewer) {
		super(viewer);
		this.editorsMap = new HashMap<String, CellEditor>();
		Composite parent = (Composite) viewer.getControl();
		this.editor = new TextCellEditor(parent);
		this.booleanEditor = createBooleanEditor(parent);
		this.dateTimeEditor = createDateTimeEditor(parent);
		this.xmlEditor = createXMLEditor(parent);
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		if (element instanceof ValueHolder) {
			Variable variable = ((ValueHolder) element).getVariable();
			if (variable.isBoolean()) {
				return booleanEditor;
			} else if (variable.isDate()) {
				return dateTimeEditor;
			} else if (variable.isDOMType()) {
				return xmlEditor;
			}
			String varName = variable.getName();
			CellEditor editor = editorsMap.get(varName);
			if (editor == null) {
				editor = createEditor((Composite) getViewer().getControl(),
						varName);
				if (editor != null) {
					editorsMap.put(varName, editor);
				}
			}
			if (editor != null) {
				return editor;
			}
		}
		return editor;
	}

	protected CellEditor createEditor(Composite parent, String varName) {
		return new StringComboBoxCellEditor(parent, new String[] {});
	}

	@Override
	protected boolean canEdit(Object element) {
		if (element instanceof Variable) {
			// first line of array variable is not editable.
			return !((Variable) element).isArray();
		}
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
		getViewer().update(element, null);
	}

	private ComboBoxViewerCellEditor createBooleanEditor(Composite parent) {
		ComboBoxViewerCellEditor booleanEditor = new ComboBoxViewerCellEditor(
				parent, SWT.READ_ONLY);
		booleanEditor.setContenProvider(ArrayContentProvider.getInstance());
		booleanEditor.setLabelProvider(new LabelProvider());
		booleanEditor.setInput(new String[] { "", "false()", "true()" });
		return booleanEditor;
	}

	private XMLInputDialog createXMLEditor(Composite parent) {
		return new XMLInputDialog(parent);
	}

	private CellEditor createDateTimeEditor(Composite parent) {
		return new DateTimeCellEditor(parent);
	}

	protected void registerEditor(CellEditor editor, String... varNames) {
		for (int i = 0; i < varNames.length; i++) {
			editorsMap.put(varNames[i], editor);
		}
	}
}
