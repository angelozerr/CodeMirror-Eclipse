package codemirror.eclipse.swt.builder;

import java.io.IOException;
import java.io.Writer;

public class Function implements Writable {

	private final String script;

	public Function(String script) {
		this.script = script;
	}

	public String getScript() {
		return script;
	}

	@Override
	public void write(CMBuilder builder, Writer writer) throws IOException {
		builder.write(writer, this.getScript(), false);
	}
}
