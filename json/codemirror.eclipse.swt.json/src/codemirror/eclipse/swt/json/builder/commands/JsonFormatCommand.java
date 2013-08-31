package codemirror.eclipse.swt.json.builder.commands;

import codemirror.eclipse.swt.builder.commands.AbstractFormatCommand;
import codemirror.eclipse.swt.builder.commands.Command;

public class JsonFormatCommand extends AbstractFormatCommand {

	public static final Command INSTANCE = new JsonFormatCommand();

	public JsonFormatCommand() {
		super("var result= formatter.formatJson(cm.getValue(), ' ');\n"
				+ "cm.setValue(result);");
	}
}
