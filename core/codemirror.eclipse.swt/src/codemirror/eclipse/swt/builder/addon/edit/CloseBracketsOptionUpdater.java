package codemirror.eclipse.swt.builder.addon.edit;

import codemirror.eclipse.swt.builder.AbstractOptionUpdater;
import codemirror.eclipse.swt.builder.Options;

public class CloseBracketsOptionUpdater extends AbstractOptionUpdater {

	private static final String[] CLOSEBRACKETS_JS = { "scripts/codemirror/addon/edit/closebrackets.js" };
	
	private static final CloseBracketsOptionUpdater INSTANCE = new CloseBracketsOptionUpdater();

	public static CloseBracketsOptionUpdater getInstance() {
		return INSTANCE;
	}

	public void setAutoCloseBrackets(Options options, boolean autoCloseBrackets) {
		// add closebrackets.js script
		super.install(options.getBuilder(), CLOSEBRACKETS_JS, null);
		// "autoCloseBrackets" : true
		options.addOption("autoCloseBrackets", autoCloseBrackets);
	}

}
