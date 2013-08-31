package codemirror.eclipse.swt.builder.commands;

import java.io.IOException;
import java.io.Writer;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Writable;

public class Command implements Writable {

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

	public void write(CMBuilder builder, Writer writer) throws IOException {
		builder.write(writer, "\"", false);
		builder.write(writer, this.getName(), false);
		builder.write(writer, "\"", false);
	}
}
