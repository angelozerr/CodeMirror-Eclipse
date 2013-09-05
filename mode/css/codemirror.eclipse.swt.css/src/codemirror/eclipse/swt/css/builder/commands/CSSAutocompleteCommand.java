package codemirror.eclipse.swt.css.builder.commands;

import codemirror.eclipse.swt.builder.commands.AbstractAutocompleteCommand;
import codemirror.eclipse.swt.builder.commands.Command;

public class CSSAutocompleteCommand extends AbstractAutocompleteCommand {

	public static final Command INSTANCE = new CSSAutocompleteCommand();

	public CSSAutocompleteCommand() {
		super("CodeMirror.showHint(cm, CodeMirror.hint.css);");
	}
}
