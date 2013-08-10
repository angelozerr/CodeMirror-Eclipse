package codemirror.eclipse.swt.builder.codemirror;

import codemirror.eclipse.swt.builder.Options;

public class AbstractOptionUpdater {

	protected void install(Options options, String[] scripts, String[] styles) {
		if (scripts != null) {
			for (int i = 0; i < scripts.length; i++) {
				options.getBuilder().addScript(scripts[i]);
			}
		}
		if (styles != null) {
			for (int i = 0; i < styles.length; i++) {
				options.getBuilder().addStyle(styles[i]);
			}
		}
	}
}
