package codemirror.eclipse.ui.xquery.editors.forms;

import java.io.File;
import java.util.Collection;

import org.eclipse.swt.widgets.Composite;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.xquery.CMXQueryControl;
import codemirror.eclipse.swt.xquery.addon.variables.IVariablesAware;
import codemirror.eclipse.swt.xquery.addon.variables.IVariablesListener;
import codemirror.eclipse.swt.xquery.addon.variables.Variable;
import codemirror.eclipse.ui.editors.forms.CMFormEditor;
import codemirror.eclipse.ui.editors.forms.CMFormPage;

public abstract class CMXQueryFormPage extends CMFormPage implements
		IVariablesAware {

	public CMXQueryFormPage(CMFormEditor editor, String id, String title,
			File file) {
		super(editor, id, title, file);
	}

	public CMXQueryFormPage(CMFormEditor editor, String id, String title,
			String url) {
		super(editor, id, title, url);
	}

	@Override
	public CMControl createCM(String url, CMBuilder builder, Composite parent,
			int style) {
		return new CMXQueryControl(url, parent, style);
	}

	public void addVariablesListener(IVariablesListener l) {
		((CMXQueryControl) getCMControl()).addVariablesListener(l);
	}

	public void removeVariablesListener(IVariablesListener l) {
		((CMXQueryControl) getCMControl()).removeVariablesListener(l);
	}

	public Variable findVar(String varName) {
		return ((CMXQueryControl) getCMControl()).findVar(varName);
	}

	public Collection<Variable> getVars() {
		return ((CMXQueryControl) getCMControl()).getVars();
	}

}
