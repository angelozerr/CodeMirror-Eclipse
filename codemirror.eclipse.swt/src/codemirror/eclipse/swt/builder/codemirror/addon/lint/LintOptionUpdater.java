package codemirror.eclipse.swt.builder.codemirror.addon.lint;

import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.codemirror.AbstractOptionUpdater;

public class LintOptionUpdater extends AbstractOptionUpdater {

	private static final String[] LINT_JS = { "scripts/codemirror/addon/lint/lint.js" };
	private static final String[] LINT_CSS = { "scripts/codemirror/addon/lint/lint.css" };

	private static final LintOptionUpdater INSTANCE = new LintOptionUpdater();

	public static LintOptionUpdater getInstance() {
		return INSTANCE;
	}

	public LintOption getLint(Options options) {
		LintOption lintWith = (LintOption) options.get("lintWith");
		if (lintWith == null) {
			// add lint.js + lint.css
			super.activate(options, LINT_JS, LINT_CSS);
			lintWith = new LintOption();
			options.addOption("lintWith", lintWith);
		}
		return lintWith;
	}

}
