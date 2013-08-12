package codemirror.eclipse.swt.builder.commands;

public class AbstractFormatCommand extends Command {

	public static final String FORMAT = "format";

	public AbstractFormatCommand(String script) {
		super(FORMAT, script);
	}

}
