package codemirror.eclipse.swt.addon.lint;

import java.io.IOException;
import java.io.Writer;

public class StreamLintHandler implements LintHandler {

	private final Writer writer;
	private int nbAnnotations;

	public StreamLintHandler(Writer writer) {
		this.writer = writer;
		this.nbAnnotations = 0;
	}

	@Override
	public void startAnnotations() {
		try {
			JsonUtils.beginJsonArray(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endAnnotations() {
		try {
			JsonUtils.endJsonArray(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void addAnnotation(int startLine, int startChar, int endLine,
			int endChar, String message) {
		try {
			if (nbAnnotations > 0) {
				writer.write(",");
			}
			JsonUtils.beginJsonObject(writer);
			JsonUtils.addJsonField("startLine", startLine, true, true, writer);
			JsonUtils.addJsonField("startChar", startChar, false, true, writer);
			JsonUtils.addJsonField("endLine", endLine, false, true, writer);
			JsonUtils.addJsonField("endChar", endChar, false, true, writer);
			JsonUtils.addJsonField("message", message, false, true, writer);
			JsonUtils.endJsonObject(writer);
			nbAnnotations++;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
