package codemirror.eclipse.swt.xquery;

public class ValueHolder {

	private String value;	
	private final Variable variable;
	
	public ValueHolder(Variable variable) {
		this.variable = variable;
	}
		
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Variable getVariable() {
		return variable;
	}

}
