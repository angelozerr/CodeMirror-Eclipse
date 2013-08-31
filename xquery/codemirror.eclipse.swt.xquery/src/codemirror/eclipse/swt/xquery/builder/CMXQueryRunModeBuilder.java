package codemirror.eclipse.swt.xquery.builder;

import codemirror.eclipse.swt.builder.CMRunModeBuilder;
import codemirror.eclipse.swt.builder.Theme;

public class CMXQueryRunModeBuilder extends CMRunModeBuilder {

	public CMXQueryRunModeBuilder(String baseURL) {
		super(XQueryMode.INSTANCE, baseURL);
		setTheme(Theme.XQ_LIGHT);
	}

}
