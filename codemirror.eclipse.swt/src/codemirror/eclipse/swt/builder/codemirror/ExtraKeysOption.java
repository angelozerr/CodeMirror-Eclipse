package codemirror.eclipse.swt.builder.codemirror;

import codemirror.eclipse.swt.builder.BaseOptions;
import codemirror.eclipse.swt.builder.CMBuilder;

public class ExtraKeysOption extends BaseOptions {

	public ExtraKeysOption(CMBuilder builder) {
		super(builder);
	}

	@Override
	protected boolean isOneOption() {
		return false;
	}
}
