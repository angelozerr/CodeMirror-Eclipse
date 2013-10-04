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
		boolean isArray = variable.isArray();
		if (isArray) {
			result.append("(");
			Collection<ValueHolder> values = variable.getValues();
			if (values != null) {
				int i = 0;
				for (ValueHolder value : values) {
					if (i > 0)
						result.append(",");
					addValue(value, result);
					i++;
				}
			}
			result.append(")");
			return true;
		}
		return addValue(variable, result);
	}

	private static boolean addValue(ValueHolder valueHolder,
			StringBuilder result) {
		String value = valueHolder.getValue();
		Variable variable = valueHolder.getVariable();
		boolean isOptionnal = !variable.isArray() && StringUtils.isEmpty(value)
				&& variable.isOptionnal();
		if (!isOptionnal) {
			if (variable.isString()) {
				result.append("'");
				result.append(getValue(value));
				result.append("'");
				return true;
			} else if (variable.isNumeric()) {
				result.append(variable.getTypeWithoutOccurence());
				result.append("(");
				result.append(getValue(value));
				result.append(")");
				return true;
			} else if (variable.isDate()) {
				result.append(variable.getTypeWithoutOccurence());
				result.append("('");
				result.append(getValue(value));
				result.append("')");
				return true;
			}
		}
		result.append(getValue(value));
		return StringUtils.isNotEmpty(value);
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
