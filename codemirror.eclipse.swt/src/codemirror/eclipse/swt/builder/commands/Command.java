package codemirror.eclipse.swt.builder.commands;

public class Command {

	private final String name;
	private final String script;

	public Command(String name, String script) {
		this.name = name;
		this.script = script;
	}

	public String getName() {
		return name;
	}

	public String getScript() {
		return script;
	}
}
