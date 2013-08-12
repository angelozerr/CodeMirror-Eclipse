package codemirror.eclipse.swt.builder.codemirror;

import codemirror.eclipse.swt.builder.AbstractCMBuilder;
import codemirror.eclipse.swt.builder.Mode;
import codemirror.eclipse.swt.builder.Options;

public class ModeOptionUpdater extends AbstractOptionUpdater {

	private static final ModeOptionUpdater INSTANCE = new ModeOptionUpdater();

	public static ModeOptionUpdater getInstance() {
		return INSTANCE;
	}

	public void setMode(AbstractCMBuilder builder, Mode mode) {
		super.install(builder, mode.getScripts(), null);
	}

	public void setMode(Options options, Mode mode) {
		setMode(options.getBuilder(), mode);
		options.addOption("mode", mode.getMimeType());
	}

}
