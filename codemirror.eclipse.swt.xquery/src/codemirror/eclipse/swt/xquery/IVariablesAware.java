package codemirror.eclipse.swt.xquery;

import java.util.Collection;

public interface IVariablesAware {

	public static final IVariablesAware NULL = new IVariablesAware() {

		@Override
		public void removeVariablesListener(IVariablesListener l) {

		}

		@Override
		public Collection<Variable> getVars() {
			return null;
		}

		@Override
		public Variable findVar(String varName) {
			return null;
		}

		@Override
		public void addVariablesListener(IVariablesListener l) {

		}
	};

	void addVariablesListener(IVariablesListener l);

	void removeVariablesListener(IVariablesListener l);

	Variable findVar(String varName);

	Collection<Variable> getVars();

}
