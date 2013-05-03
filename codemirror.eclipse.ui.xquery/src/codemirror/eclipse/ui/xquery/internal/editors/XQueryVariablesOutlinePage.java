package codemirror.eclipse.ui.xquery.internal.editors;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.CMRunModeControl;
import codemirror.eclipse.swt.xquery.IVariablesAware;
import codemirror.eclipse.swt.xquery.IVariablesListener;
import codemirror.eclipse.swt.xquery.ValueHolder;
import codemirror.eclipse.swt.xquery.Variable;
import codemirror.eclipse.swt.xquery.VariableHelper;
import codemirror.eclipse.ui.resources.CMResourcesRegistry;
import codemirror.eclipse.ui.xquery.viewers.VariableContentProvider;
import codemirror.eclipse.ui.xquery.viewers.VariableValueEditingSupport;

public class XQueryVariablesOutlinePage extends Page implements
		IContentOutlinePage, ISelectionListener, IVariablesListener {

	private static final XQueryVariablesOutlinePage INSTANCE = new XQueryVariablesOutlinePage();

	private VariablesTreeViewer viewer;
	private CMRunModeControl previewLabel;
	private SashForm sashForm;

	private IVariablesAware currentFreemarkerEditorPart;

	public static XQueryVariablesOutlinePage getInstance() {
		return INSTANCE;
	}

	@Override
	public void createControl(Composite parent) {

		sashForm = new SashForm(parent, SWT.VERTICAL);
		viewer = new VariablesTreeViewer(sashForm, SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.MULTI, this);

		viewer.setContentProvider(new VariableContentProvider());
		viewer.setLabelProvider(new LabelProvider());

		Tree table = viewer.getTree();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		// 3) Create Tree columns with sort of paginated list.
		createColumns(viewer);

		previewLabel = new CMRunModeControl(CMResourcesRegistry.getRegistry()
				.getURL(CMResourcesConstants.XQUERY_RUNMODE_HTML), sashForm,
				SWT.NONE);
		previewLabel.setMode("application/xquery");

		sashForm.setWeights(new int[] { 2, 1 });

		getSite().getPage().addPostSelectionListener(this);

		/*
		 * Action addValue = new AddVariableValueAction(viewer); Action
		 * removeValue = new RemoveVariableValueAction(viewer);
		 * getViewSite().getActionBars().getToolBarManager().add(addValue);
		 * getViewSite().getActionBars().getToolBarManager().add(removeValue);
		 */
	}

	private static void createColumns(final TreeViewer viewer) {
		TreeViewerColumn col = createTreeViewerColumn(viewer, "Name", 150,
				SWT.READ_ONLY);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Variable) {
					return ((Variable) element).getName();
				}
				return "";
			}

			@Override
			public Color getBackground(Object element) {
				return viewer.getTree().getDisplay()
						.getSystemColor(SWT.COLOR_GRAY);
			}
		});

		col = createTreeViewerColumn(viewer, "Value", 150, SWT.NONE);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof ValueHolder) {
					return ((ValueHolder) element).getValue();
				}
				return "";
			}

		});
		col.setEditingSupport(new VariableValueEditingSupport(viewer));

		col = createTreeViewerColumn(viewer, "Type", 150, SWT.READ_ONLY);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Variable) {
					return ((Variable) element).getType();
				}
				return "";
			}

			@Override
			public Color getBackground(Object element) {
				return viewer.getTree().getDisplay()
						.getSystemColor(SWT.COLOR_GRAY);
			}
		});
	}

	private static TreeViewerColumn createTreeViewerColumn(TreeViewer viewer,
			String title, int bound, int style) {
		final TreeViewerColumn viewerColumn = new TreeViewerColumn(viewer,
				style);
		final TreeColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	@Override
	public Control getControl() {
		return sashForm;
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part.equals(this) || part.equals(currentFreemarkerEditorPart))
			return;
		computeAndDoSetInput(part);
	}

	@Override
	public void dispose() {
		super.dispose();
		removeFreemarkerSettingsChangedListenerIfNeeded();
		getSite().getPage().removePostSelectionListener(this);
	}

	private void computeAndDoSetInput(final IWorkbenchPart part) {
		Assert.isLegal(part != null);
		IWorkbenchPartSite site = part.getSite();
		IVariablesAware variablesAware = (IVariablesAware) part
				.getAdapter(IVariablesAware.class);
		if (variablesAware == null) {
			removeFreemarkerSettingsChangedListenerIfNeeded();
			Collection<Variable> emptyVars = Collections.emptyList();
			varsChanged(emptyVars);
			previewLabel.setText("");
			return;
		}

		varsChanged(variablesAware.getVars());
		// super.setContentDescription(getStatusText(templateEditor));

		addFreemarkerSettingsChangedListenerIfNeeded(part);
		currentFreemarkerEditorPart = variablesAware;

	}

	private void removeFreemarkerSettingsChangedListenerIfNeeded() {
		if (currentFreemarkerEditorPart != null) {
			// Remove template settings listener changed
			currentFreemarkerEditorPart.removeVariablesListener(this);

			currentFreemarkerEditorPart = null;
		}
	}

	/**
	 * Add listener to observe changed of the template settings. As soon as
	 * template settings, changed, the Preview is refreshed.
	 * 
	 * @param currentPart
	 */
	private void addFreemarkerSettingsChangedListenerIfNeeded(
			IWorkbenchPart currentPart) {
		if (!currentPart.equals(currentFreemarkerEditorPart)) {

			// Remove template+project settings listener changed
			removeFreemarkerSettingsChangedListenerIfNeeded();

			// Add template settings listener changed
			IVariablesAware variablesAware = (IVariablesAware) currentPart
					.getAdapter(IVariablesAware.class);
			variablesAware.addVariablesListener(this);
		}
	}

	public void varsChanged(Collection<Variable> vars) {
		viewer.setInput(vars);
		viewer.expandTreeAndRefreshPreview();
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {

	}

	public ISelection getSelection() {

		return null;
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {

	}

	public void setSelection(ISelection selection) {

	}

	protected void refreshPreview(Collection<Variable> vars) {
		if (previewLabel == null || vars == null) {
			return;
		}
		StringBuilder result = new StringBuilder();
		int i = 0;
		for (Variable variable : vars) {
			if (i > 0) {
				result.append("\n");
			}
			VariableHelper.toLet(variable, result);
			i++;
		}
		previewLabel.setText(result.toString());
	}

}
