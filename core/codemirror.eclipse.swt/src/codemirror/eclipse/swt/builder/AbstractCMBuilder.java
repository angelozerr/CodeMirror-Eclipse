/*******************************************************************************
 * Copyright (c) 2013 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package codemirror.eclipse.swt.builder;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import codemirror.eclipse.swt.ICMHtmlProvider;

/**
 * Abstract buider to create HTML content with CodeMirror.
 * 
 */
public abstract class AbstractCMBuilder implements ICMHtmlProvider {

	private final Mode mode;
	private final String baseURL;
	private final List<String> scripts;
	private List<String> styles;

	private Theme theme;

	public AbstractCMBuilder(Mode mode, String baseURL) {
		this.mode = mode;
		if (!(baseURL.endsWith("/") || baseURL.endsWith("\\"))) {
			baseURL += "/";
		}
		this.baseURL = baseURL;
		this.styles = new ArrayList<String>();
		this.scripts = new ArrayList<String>();

		// <!-- CodeMirror -->
		installCodeMirror();
	}

	private void installCodeMirror() {
		// codemirror js + css
		addScript("scripts/codemirror/lib/codemirror.js");
		addStyle("scripts/codemirror/lib/codemirror.css");
		// mode
		ModeOptionUpdater.getInstance().setMode(this, mode);
	}

	protected void installRunMode() {
		addScript("scripts/codemirror/addon/runmode/runmode.js");
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
		ThemeOptionUpdater.getInstance().setTheme(this, theme);
	}

	public Theme getTheme() {
		return theme;
	}

	public Mode getMode() {
		return mode;
	}

	private void insertStyle(Writer writer, String href) throws IOException {
		write(writer, "<link rel=\"stylesheet\" href=\"");
		write(writer, baseURL, false);
		write(writer, href, false);
		write(writer, "\">", false);
	}

	private void insertScript(Writer writer, String src) throws IOException {
		write(writer, "<script src=\"");
		write(writer, baseURL, false);
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

	public String getHtml() {
		StringWriter writer = new StringWriter();
		try {
			write(writer);
		} catch (IOException e) {
			// Should never thrown
		}
		 System.err.println(writer);
		return writer.toString();
	}

	public void addStyles(String[] styles) {
		if (styles != null) {
			for (int i = 0; i < styles.length; i++) {
				this.addStyle(styles[i]);
			}
		}
	}

	public void addStyle(String style) {
		if (!styles.contains(style)) {
			styles.add(style);
		}
	}

	public void removeStyle(String style) {
		styles.remove(style);
	}

	public void addScripts(String[] scripts) {
		if (scripts != null) {
			for (int i = 0; i < scripts.length; i++) {
				this.addScript(scripts[i]);
			}
		}
	}

	public void addScript(String script) {
		if (!scripts.contains(script)) {
			scripts.add(script);
		}
	}

	public void write(Writer writer) throws IOException {
		writeBeforeCM(writer);
		writeBodyCM(writer);
		writeAfterCM(writer);
	}

	protected void writeBeforeCM(Writer writer) throws IOException {
		write(writer, "<!doctype html>", false);
		write(writer, "<html>");
		write(writer, "<head>");
		writeHtmlHead(writer);
		write(writer, "</head>");
		write(writer, "<body>", false);
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

	protected void writeAfterCM(Writer writer) throws IOException {
		write(writer, "</body>");
		write(writer, "</html>");
	}

	@Override
	public String toString() {
		return getHtml();
	}

	protected abstract void writeBodyCM(Writer writer) throws IOException;

}
