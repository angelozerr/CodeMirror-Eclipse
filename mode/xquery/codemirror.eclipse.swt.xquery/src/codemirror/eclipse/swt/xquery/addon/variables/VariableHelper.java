package codemirror.eclipse.swt.xquery.addon.variables;

import java.util.Collection;

import codemirror.eclipse.swt.utils.StringUtils;

public class VariableHelper {

	public static void toLet(Variable variable, StringBuilder result) {
		result.append("let ");
		result.append(variable.getName());
		result.append(":=");
		if (!setValue(variable, result)) {
			result.append("()");
		}
	}

	private static boolean setValue(Variable variable, StringBuilder result) {
		boolean hasValue = variable.isString()
				|| StringUtils.isNotEmpty(variable.getValue());
		String type = variable.getType();
		if (!StringUtils.isEmpty(type)) {
			boolean isArray = variable.isArray();
			if (isArray) {
				hasValue = true;
				result.append("(");
				Collection<ValueHolder> values = variable.getValues();
				if (values != null) {
					int i = 0;
					for (ValueHolder value : values) {
						if (i > 0)
							result.append(",");
						addValue(value.getValue(), variable.isString(), result);
						i++;
					}
				}
				result.append(")");
			} else {
				addValue(variable.getValue(), variable.isString(), result);
			}

		} else {
			addValue(variable.getValue(), false, result);
		}
		return hasValue;
	}

	private static void addValue(String value, boolean isString,
			StringBuilder result) {
		if (isString) {
			result.append("'");
			result.append(getValue(value));
			result.append("'");
		} else {
			result.append(getValue(value));
		}
	}

	private static String getValue(String value) {
		if (StringUtils.isNotEmpty(value)) {
			return value;
		}
		return "";
	}

	public static String toLet(Variable variable) {
		StringBuilder result = new StringBuilder();
		toLet(variable, result);
		return result.toString();
	}
}
