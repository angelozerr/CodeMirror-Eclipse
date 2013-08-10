package codemirror.eclipse.swt.builder.codemirror;

import codemirror.eclipse.swt.builder.Options;

public class ExtraKeysOptionUpdater extends AbstractOptionUpdater {

	private static final String EXTRAKEYS_NAME = "extraKeys";

	private static final ExtraKeysOptionUpdater INSTANCE = new ExtraKeysOptionUpdater();

	public static ExtraKeysOptionUpdater getInstance() {
		return INSTANCE;
	}

	public ExtraKeysOption getExtraKeys(Options options) {
		ExtraKeysOption extraKeys = (ExtraKeysOption) options
				.get(EXTRAKEYS_NAME);
		if (extraKeys == null) {
			extraKeys = new ExtraKeysOption(options.getBuilder());
			options.addOption(EXTRAKEYS_NAME, extraKeys);
		}
		return extraKeys;
	}

}
