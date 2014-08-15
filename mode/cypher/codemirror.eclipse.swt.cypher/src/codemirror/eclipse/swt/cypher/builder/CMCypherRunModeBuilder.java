package codemirror.eclipse.swt.cypher.builder;

import codemirror.eclipse.swt.builder.CMRunModeBuilder;
import codemirror.eclipse.swt.builder.Theme;

public class CMCypherRunModeBuilder extends CMRunModeBuilder {

	public CMCypherRunModeBuilder(String baseURL) {
		super(CypherMode.INSTANCE, baseURL);
		setTheme(Theme.NEO);
	}

}
