package codemirror.eclipse.swt.builder.codemirror;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import codemirror.eclipse.swt.builder.Option;
import codemirror.eclipse.swt.builder.Options;

public class GuttersOption extends Option {

	public static final String LINT = "CodeMirror-lint-markers";
	public static final String LINENUMBERS = "CodeMirror-linenumbers";
	public static final String FOLDING = "CodeMirror-folding-markers";
	
	private final List<String> gutters;

	public GuttersOption() {
		gutters = new ArrayList<String>();
	}

	public List<String> getGutters() {
		return gutters;
	}

	public void write(Options options, Writer writer) throws IOException {
		boolean first = true;
		writer.write("[");
		for (String gutter : gutters) {
			if (!first) {
				writer.write(",");
			}
			writer.write("\"");
			writer.write(gutter);
			writer.write("\"");
			first = false;
		}
		writer.write("]");
	}

}
