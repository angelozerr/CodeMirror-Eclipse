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
import java.io.Writer;
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
import java.util.LinkedHashMap;
import java.util.Map;

import codemirror.eclipse.swt.builder.addon.fold.FoldType;
import codemirror.eclipse.swt.builder.commands.Command;

/**
 * Buider to create HTML content with CodeMirror with textarea.
 * 
 */
public class CMBuilder extends AbstractCMBuilder {

	private final Options options;
	private Map<String, Command> commands;
	private FoldType[] supportedFoldTypes;

	public CMBuilder(Mode mode, String baseURL) {
		super(mode, baseURL);
		this.supportedFoldTypes = FoldType.EMPTY;
		this.options = createOptions();

		installSearchAddon();
		installFullScreenAddon(options);
		
		// <!-- SWT Browser - CodeMirror -->
		addScript("scripts/eclipse/cm-eclipse.js");

		getOptions().setMode(mode);
		getOptions().setStyleActiveLine(true);
		getOptions().setLineWrapping(true);
		getOptions().setShowCursorWhenSelecting(true);

	}

	protected void installSearchAddon() {
		addScript("scripts/codemirror/addon/dialog/dialog.js");
		addScript("scripts/codemirror/addon/search/searchcursor.js");
		addScript("scripts/codemirror/addon/search/search.js");
		addStyle("scripts/codemirror/addon/dialog/dialog.css");
	}

	protected void installFullScreenAddon(Options options) {
		addScript("scripts/codemirror/addon/display/fullscreen.js");
		addStyle("scripts/codemirror/addon/display/fullscreen.css");
		options.addOption("fullScreen", true);
	}
	
	protected void installHint(boolean withContextInfo, boolean withTemplates) {
		// <!-- CodeMirror -->
		addScript("scripts/codemirror/addon/hint/show-hint.js");
		// <!-- CodeMirror-Extension -->
		addStyle("scripts/codemirror-extension/addon/hint/show-hint-eclipse.css");
		if (withContextInfo) {
			installContextInfoHint();
		}
		if (withTemplates) {
			installTemplatesHint();
		}
	}

	protected void installContextInfoHint() {
		addScript("scripts/codemirror-extension/addon/hint/show-context-info.js");
		addStyle("scripts/codemirror-extension/addon/hint/show-context-info.css");
	}

	protected void installTemplatesHint() {
		super.installRunMode();

		addScript("scripts/codemirror-extension/addon/hint/templates-hint.js");
		addStyle("scripts/codemirror-extension/addon/hint/templates-hint.css");
	}

	protected Options createOptions() {
		return new Options(this);
	}

	protected void writeBodyCM(Writer writer) throws IOException {
		write(writer, "<form>");
		write(writer, "<textarea id=\"code\" name=\"code\" ></textarea>");
		write(writer, "</form>");

		write(writer, "<script type=\"text/javascript\" >");
		writeCommands(writer);
		write(writer,
				"var editor = CodeMirror.fromTextArea(document.getElementById(\"code\"), ");
		options.write(writer);
		write(writer, ");");
		write(writer, "CMEclipse.loaded(editor);");
		write(writer, "</script>");
	}

	protected void writeCommands(Writer writer) throws IOException {
		if (commands != null) {
			String name = null;
			String script = null;
			for (Map.Entry<String, Command> commandSet : commands.entrySet()) {
				name = commandSet.getKey();
				script = commandSet.getValue().getScript();
				write(writer, "\nCodeMirror.commands.", false);
				write(writer, name, false);
				write(writer, "= function(cm) {", false);
				write(writer, script);
				write(writer, "}");
			}
		}
	}

	protected void writeHtmlHead(Writer writer) throws IOException {
		super.writeHtmlHead(writer);

		write(writer, "<style type=\"text/css\" >");

		write(writer, " .CodeMirror-focused .cm-matchhighlight {");
		write(writer,
				"background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAACCAYAAABytg0kAAAAFklEQVQI12NgYGBgkKzc8x9CMDAwAAAmhwSbidEoSQAAAABJRU5ErkJggg==);");
		write(writer, "background-position: bottom;");
		write(writer, "background-repeat: repeat-x;");
		write(writer, "}");

		write(writer, "</style>");
	}

	public Options getOptions() {
		return options;
	}

	public void addCommand(Command command) {
		if (commands == null) {
			commands = new LinkedHashMap<String, Command>();
		}
		commands.put(command.getName(), command);
	}

	@Override
	public void setTheme(Theme theme) {
		super.setTheme(theme);
		ThemeOptionUpdater.getInstance().setTheme(this.getOptions(), theme);
	}

	public FoldType[] getSupportedFoldTypes() {
		return supportedFoldTypes;
	}

	public void setSupportedFoldTypes(FoldType[] supportedFoldTypes) {
		this.supportedFoldTypes = supportedFoldTypes;
	}

}
