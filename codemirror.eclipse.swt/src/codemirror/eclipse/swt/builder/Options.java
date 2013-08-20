package codemirror.eclipse.swt.builder;

import java.util.List;

import codemirror.eclipse.swt.builder.addon.edit.CloseBracketsOptionUpdater;
import codemirror.eclipse.swt.builder.addon.edit.MatchBracketsOptionUpdater;
import codemirror.eclipse.swt.builder.addon.fold.FoldGutterOption;
import codemirror.eclipse.swt.builder.addon.fold.FoldGutterOptionUpdater;
import codemirror.eclipse.swt.builder.addon.hover.TextHoverImpl;
import codemirror.eclipse.swt.builder.addon.hover.TextHoverOption;
import codemirror.eclipse.swt.builder.addon.hover.TextHoverOptionUpdater;
import codemirror.eclipse.swt.builder.addon.hyperlink.HyperlinkImpl;
import codemirror.eclipse.swt.builder.addon.hyperlink.HyperlinkOption;
import codemirror.eclipse.swt.builder.addon.hyperlink.HyperlinkOptionUpdater;
import codemirror.eclipse.swt.builder.addon.lint.LintImpl;
import codemirror.eclipse.swt.builder.addon.lint.LintOption;
import codemirror.eclipse.swt.builder.addon.lint.LintOptionUpdater;
import codemirror.eclipse.swt.builder.addon.search.MatchHighlighterOption;
import codemirror.eclipse.swt.builder.addon.search.MatchHighlighterOptionUpdater;
import codemirror.eclipse.swt.builder.addon.selection.ActiveLineOptionUpdater;

public class Options extends BaseOptions {

	public Options(CMBuilder builder) {
		super(builder);
	}

	// --------------------------- codemirror.js

	/**
	 * 
	 * @param mode
	 */
	public void setMode(Mode mode) {
		ModeOptionUpdater.getInstance().setMode(this, mode);
	}

	public void setLineWrapping(boolean lineWrapping) {
		addOption("lineWrapping", lineWrapping);
	}

	public void setLineNumbers(boolean lineNumbers) {
		addOption("lineNumbers", lineNumbers);
	}

	public void setShowCursorWhenSelecting(boolean showCursorWhenSelecting) {
		addOption("showCursorWhenSelecting", showCursorWhenSelecting);
	}

	// --------------------------- codemirror/addon/selection/active-line.js

	/**
	 * 
	 * @param styleActiveLine
	 */
	public void setStyleActiveLine(boolean styleActiveLine) {
		ActiveLineOptionUpdater.getInstance().setStyleActiveLine(this,
				styleActiveLine);
	}

	// --------------------------- codemirror/addon/edit/closebrackets.js

	/**
	 * 
	 * @param autoCloseBrackets
	 */
	public void setAutoCloseBrackets(boolean autoCloseBrackets) {
		CloseBracketsOptionUpdater.getInstance().setAutoCloseBrackets(this,
				autoCloseBrackets);
	}

	public void setMatchBrackets(boolean matchBrackets) {
		MatchBracketsOptionUpdater.getInstance().setMatchBrackets(this,
				matchBrackets);
	}

	public List<String> getGutters() {
		return GuttersOptionUpdater.getInstance().getGutters(this);
	}

	public LintOption getLint(LintImpl lintImpl) {
		return LintOptionUpdater.getInstance().getLint(this, lintImpl);
	}

	public ExtraKeysOption getExtraKeys() {
		return ExtraKeysOptionUpdater.getInstance().getExtraKeys(this);
	}

	public FoldGutterOption getFoldGutter() {
		return FoldGutterOptionUpdater.getInstance().getFoldGutter(this);
	}

	public MatchHighlighterOption getMatchHighlighter() {
		return MatchHighlighterOptionUpdater.getInstance().getMatchHighlighter(
				this);
	}

	public TextHoverOption getTextHover(TextHoverImpl textHover) {
		return TextHoverOptionUpdater.getInstance().getTextHover(this,
				textHover);
	}

	public HyperlinkOption getHyperlink(HyperlinkImpl hyperlink) {
		return HyperlinkOptionUpdater.getInstance().getHyperlink(this,
				hyperlink);
	}

	// ----------------------------- Theme

	/**
	 * 
	 * @param theme
	 */
	public void setTheme(Theme theme) {
		ThemeOptionUpdater.getInstance().setTheme(this, theme);
	}

}
