package codemirror.eclipse.swt.builder.addon.fold;

import codemirror.eclipse.swt.builder.BaseOptions;
import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Function;

public class FoldGutterOption extends BaseOptions {

	public FoldGutterOption(CMBuilder builder) {
		super(builder);
	}

	public void setRangeFinder(String rangeFinder) {
		super.addOption("rangeFinder", new Function(rangeFinder));
	}

	@Override
	protected boolean isOneOption() {
		return false;
	}

}
