package codemirror.eclipse.ui.xquery.editors.forms;

import codemirror.eclipse.swt.xquery.addon.variables.IVariablesAware;
import codemirror.eclipse.ui.editors.forms.CMFormEditor;

public abstract class CMXQueryFormEditor extends CMFormEditor {

	private IVariablesAware variablesAware;

	public CMXQueryFormEditor() {
		this.variablesAware = null;
	}

	@Override
	public Object getAdapter(Class adapter) {
		/*if (IContentOutlinePage.class == adapter) {
			return XQueryVariablesOutlinePage.getInstance();
		}*/
		if (IVariablesAware.class == adapter) {
			if (variablesAware == null) {
				Object page = null;
				for (int i = 0; i < pages.size(); i++) {
					page = pages.get(i);
					if (page != null && (page instanceof IVariablesAware)) {
						this.variablesAware = (IVariablesAware) page;
						return variablesAware;
					} else {
						variablesAware = IVariablesAware.NULL;
					}
				}
			} else {
				if (!IVariablesAware.NULL.equals(variablesAware)) {
					return variablesAware;
				}
			}

		}
		return super.getAdapter(adapter);
	}
}
