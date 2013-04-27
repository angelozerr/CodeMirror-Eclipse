package codemirror.eclipse.ui.editors.forms.xquery;

import java.util.Collection;

import org.eclipse.swt.widgets.Composite;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.xquery.CMXQueryControl;
import codemirror.eclipse.swt.xquery.IVariablesListener;
import codemirror.eclipse.swt.xquery.Variable;
import codemirror.eclipse.ui.editors.forms.CMFormEditor;
import codemirror.eclipse.ui.editors.forms.CMFormPage;

public abstract class CMXQueryFormPage extends CMFormPage {

	public CMXQueryFormPage(CMFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	public CMControl createCM(String url, Composite parent, int style) {
		return new CMXQueryControl(url, parent, style);
	}

	public void addVariablesListener(IVariablesListener l) {
		((CMXQueryControl) getCMControl()).addVariablesListener(l);
	}

	public Variable findVar(String varName) {
		return ((CMXQueryControl) getCMControl()).findVar(varName);
	}
	
	public Collection<Variable> getVars() {
		return ((CMXQueryControl) getCMControl()).getVars();
	}
}
