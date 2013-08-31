package codemirror.eclipse.swt.builder.addon.selection;

import codemirror.eclipse.swt.builder.AbstractOptionUpdater;
import codemirror.eclipse.swt.builder.Options;

public class ActiveLineOptionUpdater extends AbstractOptionUpdater {

	private static final String[] ACTIVE_LINE_JS = { "scripts/codemirror/addon/selection/active-line.js" };
	
	private static final ActiveLineOptionUpdater INSTANCE = new ActiveLineOptionUpdater();

	public static ActiveLineOptionUpdater getInstance() {
		return INSTANCE;
	}

	public void setStyleActiveLine(Options options, boolean styleActiveLine) {
		// add active-line.js script
		super.install(options.getBuilder(), ACTIVE_LINE_JS, null);
		// "styleActiveLine" : true
		options.addOption("styleActiveLine", styleActiveLine);
	}

}
