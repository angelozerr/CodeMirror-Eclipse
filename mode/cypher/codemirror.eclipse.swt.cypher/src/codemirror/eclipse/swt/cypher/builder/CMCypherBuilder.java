package codemirror.eclipse.swt.cypher.builder;

import java.util.List;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.GuttersOptionUpdater;
import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.Theme;

public class CMCypherBuilder extends CMBuilder {

	public CMCypherBuilder(String baseURL) {
		super(CypherMode.INSTANCE, baseURL);
		Options options = super.getOptions();
		List<String> gutters = options.getGutters();

		// brackets
		options.setAutoCloseBrackets(true);
		options.setMatchBrackets(true);

		// Line numbers
		options.setLineNumbers(true);
		gutters.add(GuttersOptionUpdater.LINENUMBERS);

		options.setTheme(Theme.NEO);
	}

}
