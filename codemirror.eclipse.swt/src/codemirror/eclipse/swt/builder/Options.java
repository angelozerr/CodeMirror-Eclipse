package codemirror.eclipse.swt.builder;

import java.util.List;

import codemirror.eclipse.swt.builder.codemirror.ExtraKeysOption;
import codemirror.eclipse.swt.builder.codemirror.ExtraKeysOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.GuttersOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.ModeOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.ThemeOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.addon.edit.CloseBracketsOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.addon.edit.MatchBracketsOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.addon.fold.FoldGutterOption;
import codemirror.eclipse.swt.builder.codemirror.addon.fold.FoldGutterOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.addon.lint.LintImpl;
import codemirror.eclipse.swt.builder.codemirror.addon.lint.LintOption;
import codemirror.eclipse.swt.builder.codemirror.addon.lint.LintOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.addon.search.MatchHighlighterOption;
import codemirror.eclipse.swt.builder.codemirror.addon.search.MatchHighlighterOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.addon.selection.ActiveLineOptionUpdater;

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

	// ----------------------------- Theme

	/**
	 * 
	 * @param theme
	 */
	public void setTheme(Theme theme) {
		ThemeOptionUpdater.getInstance().setTheme(this, theme);
	}

}
