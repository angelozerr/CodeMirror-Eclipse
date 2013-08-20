package codemirror.eclipse.swt.builder;

import java.util.ArrayList;
import java.util.List;

public class GuttersOptionUpdater extends AbstractOptionUpdater {

	public static final String LINT = "CodeMirror-lint-markers";
	public static final String LINENUMBERS = "CodeMirror-linenumbers";
	public static final String FOLDGUTTER = "CodeMirror-foldgutter";

	private static final String GUTTERS_NAME = "gutters";

	private static final GuttersOptionUpdater INSTANCE = new GuttersOptionUpdater();

	public static GuttersOptionUpdater getInstance() {
		return INSTANCE;
	}

	public List<String> getGutters(Options options) {
		List<String> gutters = (List<String>) options.get(GUTTERS_NAME);
		if (gutters == null) {
			gutters = new ArrayList<String>();
			options.addOption(GUTTERS_NAME, gutters);
		}
		return gutters;
	}

}
