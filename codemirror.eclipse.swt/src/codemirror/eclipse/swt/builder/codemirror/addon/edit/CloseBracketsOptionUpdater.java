package codemirror.eclipse.swt.builder.codemirror.addon.edit;

import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.codemirror.AbstractOptionUpdater;

public class CloseBracketsOptionUpdater extends AbstractOptionUpdater {

	private static final String[] CLOSEBRACKETS_JS = { "scripts/codemirror/addon/edit/closebrackets.js" };
	
	private static final CloseBracketsOptionUpdater INSTANCE = new CloseBracketsOptionUpdater();

	public static CloseBracketsOptionUpdater getInstance() {
		return INSTANCE;
	}

	public void setAutoCloseBrackets(Options options, boolean autoCloseBrackets) {
		// add closebrackets.js script
		super.install(options, CLOSEBRACKETS_JS, null);
		// "autoCloseBrackets" : true
		options.addOption("autoCloseBrackets", autoCloseBrackets);
	}

}
