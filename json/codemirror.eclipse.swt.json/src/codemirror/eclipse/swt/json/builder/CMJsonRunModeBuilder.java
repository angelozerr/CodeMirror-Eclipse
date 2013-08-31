package codemirror.eclipse.swt.json.builder;

import codemirror.eclipse.swt.builder.CMRunModeBuilder;
import codemirror.eclipse.swt.builder.Theme;

public class CMJsonRunModeBuilder extends CMRunModeBuilder {

	public CMJsonRunModeBuilder(String baseURL) {
		super(JsonMode.INSTANCE, baseURL);
		setTheme(Theme.ECLIPSE);
	}

}
