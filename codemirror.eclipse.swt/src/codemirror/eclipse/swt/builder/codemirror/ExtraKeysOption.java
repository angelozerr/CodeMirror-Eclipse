package codemirror.eclipse.swt.builder.codemirror;

import codemirror.eclipse.swt.builder.BaseOptions;
import codemirror.eclipse.swt.builder.CMBuilder;

public class ExtraKeysOption extends BaseOptions {

	public static final String PASS_AND_HINT = "passAndHint";
	public static final String AUTOCOMPLETE = "autocomplete";
	public static final String FORMAT = "format";
	
	public ExtraKeysOption(CMBuilder builder) {
		super(builder);
	}
}
