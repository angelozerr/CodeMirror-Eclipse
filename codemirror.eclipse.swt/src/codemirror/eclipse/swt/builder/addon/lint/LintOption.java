package codemirror.eclipse.swt.builder.addon.lint;

import codemirror.eclipse.swt.builder.BaseOptions;
import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Function;

public class LintOption extends BaseOptions {

	public LintOption(CMBuilder builder) {
		super(builder);
	}

	public void setLint(boolean lint) {
		super.addOption("lint", lint);
	}

	public void setGetAnnotations(String getAnnotations) {
		super.addOption("getAnnotations", new Function(getAnnotations));
	}

	public void setAsync(boolean async) {
		super.addOption("async", async);
	}

}
