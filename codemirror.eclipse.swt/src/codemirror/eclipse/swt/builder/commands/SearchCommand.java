package codemirror.eclipse.swt.builder.commands;

public class SearchCommand extends Command {

	public static Command INSTANCE = new SearchCommand();

	public SearchCommand() {
		super("customSearch",
				"cm_openSearch();");
	}

}
