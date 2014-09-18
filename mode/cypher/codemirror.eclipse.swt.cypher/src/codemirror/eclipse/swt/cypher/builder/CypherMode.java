package codemirror.eclipse.swt.cypher.builder;

import codemirror.eclipse.swt.builder.Mode;

public class CypherMode extends Mode {

	public static final Mode INSTANCE = new CypherMode();

	private CypherMode() {
		super("application/x-cypher-query",
				new String[] { "scripts/codemirror/mode/cypher/cypher.js" });
	}


}
