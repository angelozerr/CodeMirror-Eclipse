package codemirror.eclipse.swt.builder.codemirror;

import codemirror.eclipse.swt.builder.Options;

public class GuttersOptionUpdater extends AbstractOptionUpdater {

	private static final GuttersOptionUpdater INSTANCE = new GuttersOptionUpdater();

	public static GuttersOptionUpdater getInstance() {
		return INSTANCE;
	}

	public GuttersOption getGutters(Options options) {
		GuttersOption gutters = (GuttersOption) options.get("gutters");
		if (gutters == null) {
			gutters = new GuttersOption();
			options.addOption("gutters", gutters);
		}
		return gutters;
	}

}
