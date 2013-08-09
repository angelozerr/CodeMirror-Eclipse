package codemirror.eclipse.swt.builder.codemirror;

import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.Theme;

public class ThemeOptionUpdater extends AbstractOptionUpdater {

	private static final ThemeOptionUpdater INSTANCE = new ThemeOptionUpdater();

	public static ThemeOptionUpdater getInstance() {
		return INSTANCE;
	}

	public void setTheme(Options options, Theme theme) {
		// add the CSS
		options.getBuilder().addStyle(theme.getStyle());
		// set theme to the options
		options.addOption("theme", theme.getName());
	}

}
