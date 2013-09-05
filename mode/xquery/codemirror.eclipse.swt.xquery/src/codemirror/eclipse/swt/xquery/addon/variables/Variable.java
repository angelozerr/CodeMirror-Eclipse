package codemirror.eclipse.swt.xquery.addon.variables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Variable extends ValueHolder {

	private String name;
	private String type;
	private boolean isArray;
	private Collection<ValueHolder> values;
	private boolean isString;
	private boolean isBoolean;

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

	public void setType(String type) {
		this.type = type;
		this.values = null;
		this.isArray = type != null && type.endsWith("*");
		this.isString = type != null && type.startsWith("xs:string");
		this.isBoolean = type != null && type.startsWith("xs:boolean");
	}

	public boolean isArray() {
		return isArray;
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
}
