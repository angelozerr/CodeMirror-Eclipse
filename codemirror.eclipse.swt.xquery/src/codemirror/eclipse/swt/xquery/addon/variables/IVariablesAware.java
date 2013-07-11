package codemirror.eclipse.swt.xquery.addon.variables;

import java.util.Collection;

public interface IVariablesAware {

	public static final IVariablesAware NULL = new IVariablesAware() {
		
		public void removeVariablesListener(IVariablesListener l) {

		}

		public Collection<Variable> getVars() {
			return null;
		}

		public Variable findVar(String varName) {
			return null;
		}

		public void addVariablesListener(IVariablesListener l) {

		}
	};

	void addVariablesListener(IVariablesListener l);

	void removeVariablesListener(IVariablesListener l);

	Variable findVar(String varName);

	Collection<Variable> getVars();

}
