package codemirror.eclipse.swt.builder.codemirror.addon.lint;

import codemirror.eclipse.swt.builder.BaseOptions;
import codemirror.eclipse.swt.builder.CMBuilder;

public class LintOption extends BaseOptions {

	public LintOption(CMBuilder builder) {
		super(builder);
	}

	public void setLint(boolean lint) {
		super.addOption("lint", lint);
	}

	public void setGetAnnotations(String getAnnotations) {
		super.addOption("getAnnotations", getAnnotations);
	}

	public void setAsync(boolean async) {
		super.addOption("async", async);
	}

}