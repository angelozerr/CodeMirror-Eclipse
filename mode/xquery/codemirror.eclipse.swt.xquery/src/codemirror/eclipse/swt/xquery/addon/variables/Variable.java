package codemirror.eclipse.swt.xquery.addon.variables;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

import codemirror.eclipse.swt.utils.StringUtils;

public class Variable extends ValueHolder {

	private String name;
	private String type;
	private String typeWithoutOccurence;
	private boolean isArray;
	private Collection<ValueHolder> values;
	private boolean isString;
	private boolean isBoolean;
	private boolean isOptionnal;
	private boolean isXSType;
	private boolean isNumeric;
	private boolean isDate;
	private boolean isDOMType;

	public Variable() {
		super(null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public String getTypeWithoutOccurence() {
		return typeWithoutOccurence;
	}

	public void setType(String type) {
		boolean typeHasChanged = (type != null && !type.equals(this.type));
		this.type = type;
		this.typeWithoutOccurence = type;
		this.values = null;
		this.isString = false;
		this.isBoolean = false;
		this.isArray = false;
		this.isOptionnal = false;
		this.isXSType = false;
		this.isNumeric = false;
		this.isDate = false;
		if (type != null) {
			this.isXSType = type.startsWith("xs:");
			this.isDOMType = !isXSType;
			this.isString = type.startsWith("xs:string");
			this.isBoolean = type.startsWith("xs:boolean");
			this.isNumeric = type.startsWith("xs:float")
					|| type.startsWith("xs:double")
					|| type.startsWith("xs:integer")
					|| type.startsWith("numeric");
			this.isDate = type.startsWith("xs:date")
					|| type.startsWith("xs:time");
			if (type.endsWith("?")) {
				// zero or one items
				this.isOptionnal = true;
				this.typeWithoutOccurence = typeWithoutOccurence.substring(0,
						type.length() - 1);
			} else if (type.endsWith("+")) {
				// one or more items
				this.isArray = true;
				this.typeWithoutOccurence = typeWithoutOccurence.substring(0,
						type.length() - 1);
			} else if (type.endsWith("*")) {
				// any number of items (zero or more)
				this.isArray = true;
				this.isOptionnal = true;
				this.typeWithoutOccurence = typeWithoutOccurence.substring(0,
						type.length() - 1);
			}
		}
		if (typeHasChanged && isEmptyValue() && !isOptionnal) {
			// set default value
			if (isArray) {
				addValue(new ValueHolder(this));
			} else {
				this.setValue(getDefaultValue(this));
			}

		}
	}

	private static String getDefaultValue(ValueHolder valueHolder) {
		Variable variable = valueHolder.getVariable();
		if (variable.isString()) {
			return "";
		} else if (variable.isNumeric()) {
			return "0";
		} else if (variable.isBoolean()) {
			return "false()";
		} else if (variable.isDate()) {
			return XMLDateTimeFormatter.getInstance().format(
					Calendar.getInstance().getTime());
		}
		return null;
	}

	public boolean isXSType() {
		return isXSType;
	}

	public boolean isDOMType() {
		return isDOMType;
	}

	public boolean isOptionnal() {
		return isOptionnal;
	}

	public boolean isArray() {
		return isArray;
	}

	public boolean isNumeric() {
		return isNumeric;
	}

	public Collection<ValueHolder> getValues() {
		if (values == null) {
			return Collections.emptyList();
		}
		return values;
	}

	public void addValue(ValueHolder value) {
		if (values == null) {
			values = new ArrayList<ValueHolder>();
		}
		if (StringUtils.isEmpty(value.getValue())) {
			String defaultValue = getDefaultValue(value);
			value.setValue(defaultValue);
		}
		values.add(value);
	}

	public void removeValue(ValueHolder value) {
		if (values != null) {
			values.remove(value);
		}
	}

	public boolean isString() {
		return isString;
	}

	@Override
	public Variable getVariable() {
		return this;
	}

	public boolean isBoolean() {
		return isBoolean;
	}

	public boolean isDate() {
		return isDate;
	}

	public boolean isEmptyValue() {
		return StringUtils.isEmpty(getValue()) && values == null;
	}
}
