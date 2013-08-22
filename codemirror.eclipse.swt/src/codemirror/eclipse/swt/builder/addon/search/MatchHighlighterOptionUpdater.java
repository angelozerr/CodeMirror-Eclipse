package codemirror.eclipse.swt.builder.addon.search;

import codemirror.eclipse.swt.builder.AbstractOptionUpdater;
import codemirror.eclipse.swt.builder.Options;

public class MatchHighlighterOptionUpdater extends AbstractOptionUpdater {

	private static final String[] MATCH_HIGHLIGHTER_JS = { "scripts/codemirror-extension/addon/search/match-highlighter.js" };
	private static final String[] MATCH_HIGHLIGHTER_CSS = { "scripts/codemirror-extension/addon/search/match-highlighter.css" };

	private static final MatchHighlighterOptionUpdater INSTANCE = new MatchHighlighterOptionUpdater();

	public static MatchHighlighterOptionUpdater getInstance() {
		return INSTANCE;
	}

	public MatchHighlighterOption getMatchHighlighter(Options options) {
		MatchHighlighterOption matchHighlighter = (MatchHighlighterOption) options
				.get("highlightSelectionMatches");
		if (matchHighlighter == null) {
			// add match-highlighter.js
			super.install(options.getBuilder(), MATCH_HIGHLIGHTER_JS, MATCH_HIGHLIGHTER_CSS);
			matchHighlighter = new MatchHighlighterOption(options.getBuilder());
			options.addOption("highlightSelectionMatches", matchHighlighter);
		}
		return matchHighlighter;
	}

}
