package codemirror.eclipse.swt.css.builder;

import codemirror.eclipse.swt.builder.CMRunModeBuilder;
import codemirror.eclipse.swt.builder.Theme;

public class CMCSSRunModeBuilder extends CMRunModeBuilder {

	public CMCSSRunModeBuilder(String baseURL) {
		super(CSSMode.INSTANCE, baseURL);
		setTheme(Theme.ECLIPSE);
	}

}
