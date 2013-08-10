package codemirror.eclipse.swt.builder.codemirror;

import codemirror.eclipse.swt.builder.CMBuilder;

public class AbstractOptionUpdater {

	protected void install(CMBuilder builder, String[] scripts, String[] styles) {
		if (scripts != null) {
			for (int i = 0; i < scripts.length; i++) {
				builder.addScript(scripts[i]);
			}
		}
		if (styles != null) {
			for (int i = 0; i < styles.length; i++) {
				builder.addStyle(styles[i]);
			}
		}
	}
}
