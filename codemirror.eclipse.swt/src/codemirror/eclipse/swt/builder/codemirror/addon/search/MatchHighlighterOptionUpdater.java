package codemirror.eclipse.swt.builder.codemirror.addon.search;

import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.codemirror.AbstractOptionUpdater;

public class MatchHighlighterOptionUpdater extends AbstractOptionUpdater {

	private static final String[] MATCH_HIGHLIGHTER_JS = { "scripts/codemirror/addon/search/match-highlighter.js" };

	private static final MatchHighlighterOptionUpdater INSTANCE = new MatchHighlighterOptionUpdater();

	public static MatchHighlighterOptionUpdater getInstance() {
		return INSTANCE;
	}

	public MatchHighlighterOption getMatchHighlighter(Options options) {
		MatchHighlighterOption matchHighlighter = (MatchHighlighterOption) options
				.get("highlightSelectionMatches");
		if (matchHighlighter == null) {
			// add match-highlighter.js
			super.install(options.getBuilder(), MATCH_HIGHLIGHTER_JS, null);
			matchHighlighter = new MatchHighlighterOption(options.getBuilder());
			options.addOption("highlightSelectionMatches", matchHighlighter);
		}
		return matchHighlighter;
	}

}
