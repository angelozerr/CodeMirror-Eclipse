package codemirror.eclipse.swt.xquery;

import java.util.Collection;

import codemirror.eclipse.swt.xquery.internal.org.apache.commons.lang3.StringUtils;

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
		boolean hasValue = variable.isString() || StringUtils.isNotEmpty(variable.getValue());
		String type = variable.getType();		
		if (!StringUtils.isEmpty(type)) {
			boolean isArray = variable.isArray();
			if (isArray) {
				hasValue = true;
				result.append("(");				
			}
			
			addValue(variable.getValue(), variable.isString(), result);
			
			if (isArray) {
				Collection<ValueHolder> values = variable.getValues();
				if (values != null){
					for (ValueHolder value : values) {
						result.append(",");
						addValue(value.getValue(), variable.isString(), result);
					}
				}
				
				result.append(")");
			}
		} else {
			addValue(variable.getValue(), false, result);
		}
		return hasValue;
	}

	private static void addValue(String value, boolean isString, StringBuilder result) {
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
