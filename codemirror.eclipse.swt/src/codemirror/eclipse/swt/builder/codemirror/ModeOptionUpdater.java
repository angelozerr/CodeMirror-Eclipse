package codemirror.eclipse.swt.builder.codemirror;

import codemirror.eclipse.swt.builder.Mode;
import codemirror.eclipse.swt.builder.Options;

public class ModeOptionUpdater extends AbstractOptionUpdater {

	private static final ModeOptionUpdater INSTANCE = new ModeOptionUpdater();

	public static ModeOptionUpdater getInstance() {
		return INSTANCE;
	}

	public void setMode(Options options, Mode mode) {
		super.install(options, mode.getScripts(), null);
		options.addOption("mode", mode.getMimeType());
	}

}
