package codemirror.eclipse.swt.sql.builder;

import codemirror.eclipse.swt.builder.CMRunModeBuilder;
import codemirror.eclipse.swt.builder.Theme;

public class CMSQLRunModeBuilder extends CMRunModeBuilder {

	public CMSQLRunModeBuilder(String baseURL) {
		super(SQLMode.INSTANCE, baseURL);
		setTheme(Theme.ECLIPSE);
	}

}
