package codemirror.eclipse.swt.builder.addon.edit;

import codemirror.eclipse.swt.builder.AbstractOptionUpdater;
import codemirror.eclipse.swt.builder.Options;

public class MatchBracketsOptionUpdater extends AbstractOptionUpdater {

	private static final String[] CLOSEBRACKETS_JS = { "scripts/codemirror/addon/edit/matchbrackets.js" };

	private static final MatchBracketsOptionUpdater INSTANCE = new MatchBracketsOptionUpdater();

	public static MatchBracketsOptionUpdater getInstance() {
		return INSTANCE;
	}

	public void setMatchBrackets(Options options, boolean matchBrackets) {
		// add matchbrackets.js script
		super.install(options.getBuilder(), CLOSEBRACKETS_JS, null);
		// "matchBrackets" : true
		options.addOption("matchBrackets", matchBrackets);
	}

}
