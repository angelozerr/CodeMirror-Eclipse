package codemirror.eclipse.swt.builder;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCMBuilder {

	private final String baseURL;
	private final List<String> scripts;
	private List<String> styles;

	public AbstractCMBuilder(Mode mode, String baseURL) {
		this.baseURL = baseURL;
		this.styles = new ArrayList<String>();
		this.scripts = new ArrayList<String>();
	}

	private void insertStyle(Writer writer, String href) throws IOException {
		write(writer, "<link rel=\"stylesheet\" href=\"");
		write(writer, baseURL, false);
		write(writer, "/", false);
		write(writer, href, false);
		write(writer, "\">", false);
	}

	private void insertScript(Writer writer, String src) throws IOException {
		write(writer, "<script src=\"");
		write(writer, baseURL, false);
		write(writer, "/", false);
		write(writer, src, false);
		write(writer, "\"> </script>", false);
	}

	public void write(Writer writer, String content) throws IOException {
		write(writer, content, true);
	}

	public void write(Writer writer, String content, boolean lineBreak)
			throws IOException {
		if (lineBreak) {
			writer.write("\n");
		}
		writer.write(content);
	}

	public String getText() {
		StringWriter writer = new StringWriter();
		try {
			write(writer);
		} catch (IOException e) {
			// Should never thrown
		}
		// System.err.println(writer);
		return writer.toString();
	}

	public void addStyle(String style) {
		if (!styles.contains(style)) {
			styles.add(style);
		}
	}

	public void addScript(String script) {
		if (!scripts.contains(script)) {
			scripts.add(script);
		}
	}

	public void write(Writer writer) throws IOException {
		writeBefore(writer);
		writeScript(writer);
		writeAfter(writer);
	}

	protected abstract void writeScript(Writer writer) throws IOException;

	protected void writeBefore(Writer writer) throws IOException {
		write(writer, "<!doctype html>", false);
		write(writer, "<html>");
		write(writer, "<head>");
		writeHtmlHead(writer);
		write(writer, "</head>");
		write(writer, "<body");
		String onload = getOnLoadBody();
		if (onload != null) {
			write(writer, " onload=\"");
			write(writer, onload);
			write(writer, "\"");
		}
		write(writer, ">");
	}

	protected String getOnLoadBody() {
		return null;
	}

	protected void writeHtmlHead(Writer writer) throws IOException {

		// tells Internet Explorer to display a webpage in IE9 mode, if
		// possible.
		write(writer,
				"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EDGE\" />");
		// <base href="http://www.myhomeurl.com/"/>
		/*
		 * write(writer, "<base href=\"", false); write(writer, baseURL, false);
		 * write(writer, "/", false); write(writer, "\" />");
		 */
		// <link rel="stylesheet" href="scripts/codemirror/lib/codemirror.css">
		for (String href : styles) {
			insertStyle(writer, href);
		}
		// <script src="scripts/codemirror/lib/codemirror.js"></script>
		for (String src : scripts) {
			insertScript(writer, src);
		}

	}

	protected void writeAfter(Writer writer) throws IOException {
		write(writer, "</body>");
		write(writer, "</html>");
	}
}
