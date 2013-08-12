package codemirror.eclipse.swt.builder.commands;

public class PassAndHintCommand extends Command {

	public static Command INSTANCE = new PassAndHintCommand();

	public PassAndHintCommand() {
		super("passAndHint",
				"setTimeout(function() {cm.execCommand(\"autocomplete\");}, 100);\n"
						+ "return CodeMirror.Pass;");
	}
}
