package codemirror.eclipse.swt.builder;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import codemirror.eclipse.swt.builder.commands.Command;

public class CMBuilder extends AbstractCMBuilder {

	private final Options options;
	private Map<String, Command> commands;

	public CMBuilder(Mode mode, String baseURL) {
		super(mode, baseURL);
		this.options = createOptions();

		// <!-- CodeMirror -->
		installCodeMirror();
		installSearchAddon();

		// <!-- CodeMirror-Extension -->
		addScript("scripts/codemirror-extension/addon/selection/fullscreen.js");
		addStyle("scripts/codemirror-extension/addon/selection/fullscreen.css");

		// <!-- SWT Browser - CodeMirror -->
		addScript("scripts/eclipse/cm-eclipse.js");

		getOptions().setMode(mode);
		getOptions().setStyleActiveLine(true);
		getOptions().setLineWrapping(true);
		getOptions().setShowCursorWhenSelecting(true);

	}

	private void installCodeMirror() {
		addScript("scripts/codemirror/lib/codemirror.js");
		addStyle("scripts/codemirror/lib/codemirror.css");
	}

	protected void installSearchAddon() {
		addScript("scripts/codemirror/addon/dialog/dialog.js");
		addScript("scripts/codemirror/addon/search/searchcursor.js");
		addScript("scripts/codemirror/addon/search/search.js");
		addStyle("scripts/codemirror/addon/dialog/dialog.css");
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
		addScript("scripts/codemirror/addon/runmode/runmode.js");

		addScript("scripts/codemirror-extension/addon/hint/templates-hint.js");
		addStyle("scripts/codemirror-extension/addon/hint/templates-hint.css");
	}

	protected Options createOptions() {
		return new Options(this);
	}

	protected void writeScript(Writer writer) throws IOException {
		write(writer, "<form>");
		write(writer, "<textarea id=\"code\" name=\"code\" ></textarea>");
		write(writer, "</form>");
		
		write(writer, "<script type=\"text/javascript\" >");
		writeCommands(writer);
		write(writer,
				"var editor = CodeMirror.fromTextArea(document.getElementById(\"code\"), ");
		options.write(writer);
		write(writer, ");");
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
	
	@Override
	protected String getOnLoadBody() {
		return "CMEclipse.loaded()";
	}

	protected void writeHtmlHead(Writer writer) throws IOException {
		super.writeHtmlHead(writer);

		write(writer, "<style type=\"text/css\" >");
		write(writer,
				" .CodeMirror-activeline-background {background: #e8f2ff !important;}\n");
		write(writer,
				" .CodeMirror-matchingbracket{outline:1px solid grey; color:black !important;}");
		write(writer, "</style>");

		write(writer, "</head>");
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
}
