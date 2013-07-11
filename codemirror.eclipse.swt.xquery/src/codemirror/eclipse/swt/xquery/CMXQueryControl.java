package codemirror.eclipse.swt.xquery;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.xquery.addon.variables.IVariablesAware;
import codemirror.eclipse.swt.xquery.addon.variables.IVariablesListener;
import codemirror.eclipse.swt.xquery.addon.variables.Variable;

public class CMXQueryControl extends CMControl implements IVariablesAware {

	private Map<String, Variable> vars = new LinkedHashMap<String, Variable>();

	private List<IVariablesListener> listeners = new ArrayList<IVariablesListener>();

	public CMXQueryControl(File file, Composite parent, int style) {
		super(file, parent, style, SWT.NONE);
	}

	public CMXQueryControl(String url, Composite parent, int style) {
		super(url, parent, style, SWT.NONE);
	}

	public CMXQueryControl(File file, Composite parent, int style,
			int browserStyle) {
		super(file, parent, style, browserStyle);
	}

	public CMXQueryControl(String url, Composite parent, int style,
			int browserStyle) {
		super(url, parent, style, browserStyle);
	}

	@Override
	protected void createBrowserFunctions() {
		super.createBrowserFunctions();
		new BrowserFunction(browser, "cm_refreshVars") {
			public Object function(Object[] arguments) {
				if (arguments != null) {
					Object[] v = (Object[]) arguments[0];
					Map<String, Variable> oldVars = CMXQueryControl.this.vars;

					Map<String, Variable> newVars = new LinkedHashMap<String, Variable>();
					String currentName = null;
					for (int i = 0; i < v.length; i++) {
						if (currentName == null) {
							currentName = (String) v[i];
							Variable variable = oldVars.get(currentName);
							if (variable == null) {
								variable = new Variable();
								variable.setName(currentName);
							}
							newVars.put(currentName, variable);
						} else {
							Variable variable = newVars.get(currentName);
							variable.setType((String) v[i]);
							currentName = null;
						}
					}
					CMXQueryControl.this.vars = newVars;
					notifyVariablesListeners(getVars());
				}
				return null;
			}
		};
	}

	public void addVariablesListener(IVariablesListener l) {
		listeners.add(l);
	}

	private void notifyVariablesListeners(final Collection<Variable> vars) {
		Display.getCurrent().asyncExec(new Runnable() {
			public void run() {
				for (IVariablesListener l : listeners) {
					l.varsChanged(vars);
				}
			}
		});
	}

	public void removeVariablesListener(IVariablesListener l) {
		listeners.remove(l);
	}

	public Collection<Variable> getVars() {
		return vars.values();
	}

	public Variable findVar(String varName) {
		return vars.get(varName);
	}
}
