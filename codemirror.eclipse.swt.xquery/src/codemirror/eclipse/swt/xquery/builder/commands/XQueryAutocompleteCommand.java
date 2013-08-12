package codemirror.eclipse.swt.xquery.builder.commands;

import codemirror.eclipse.swt.builder.commands.AbstractAutocompleteCommand;
import codemirror.eclipse.swt.builder.commands.Command;

public class XQueryAutocompleteCommand extends AbstractAutocompleteCommand {

	public static final Command INSTANCE = new XQueryAutocompleteCommand();

	public XQueryAutocompleteCommand() {
		super("CodeMirror.showHint(cm, CodeMirror.xqueryHint);");
	}
}
