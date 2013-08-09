package codemirror.eclipse.swt.builder;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import codemirror.eclipse.swt.builder.codemirror.GuttersOption;
import codemirror.eclipse.swt.builder.codemirror.GuttersOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.ModeOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.ThemeOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.addon.edit.CloseBracketsOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.addon.edit.MatchBracketsOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.addon.lint.LintOption;
import codemirror.eclipse.swt.builder.codemirror.addon.lint.LintOptionUpdater;
import codemirror.eclipse.swt.builder.codemirror.addon.selection.ActiveLineOptionUpdater;

public class Options {

	private final Map<String, Object> options;
	private final CMBuilder builder;

	public Options(CMBuilder builder) {
		this.builder = builder;
		this.options = new LinkedHashMap<String, Object>();
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

	public GuttersOption getGutters() {
		return GuttersOptionUpdater.getInstance().getGutters(this);
	}

	public LintOption getLint() {
		return LintOptionUpdater.getInstance().getLint(this);
	}

	// ----------------------------- Theme

	/**
	 * 
	 * @param theme
	 */
	public void setTheme(Theme theme) {
		ThemeOptionUpdater.getInstance().setTheme(this, theme);
	}

	public void addOption(String key, Object value) {
		options.put(key, value);
	}

	public CMBuilder getBuilder() {
		return builder;
	}

	public void write(Writer writer) throws IOException {
		boolean first = true;
		Set<Entry<String, Object>> entries = options.entrySet();
		for (Entry<String, Object> entry : entries) {
			if (!first) {
				builder.write(writer, ",");
			}
			writeOption(writer, entry.getKey(), entry.getValue());
			first = false;
		}
	}

	public void writeOption(Writer writer, String key, Object value)
			throws IOException {
		builder.write(writer, "\"", false);
		builder.write(writer, key, false);
		builder.write(writer, "\"", false);
		builder.write(writer, ":", false);
		if (value instanceof Option) {
			((Option) value).write(this, writer);
		} else if (value instanceof Boolean) {
			builder.write(writer, (Boolean) value ? "true" : "false", false);
		} else {
			builder.write(writer, "\"", false);
			builder.write(writer, value.toString(), false);
			builder.write(writer, "\"", false);
		}
	}

	public Object get(String key) {
		return options.get(key);
	}

}
