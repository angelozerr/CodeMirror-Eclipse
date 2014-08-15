package codemirror.eclipse.swt.samples;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.cypher.builder.CMCypherBuilder;
import codemirror.eclipse.swt.resources.CMResourcesManager;

public class CypherSample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new CypherSample().createUI();
	}

	@Override
	protected String getURL() {
		return null;
	}

	@Override
	protected CMBuilder getBuilder() {
		return new CMCypherBuilder(CMResourcesManager.getInstance().getURL(""));
	}

	@Override
	protected String getInitialText() {
		return "match n return n;";
	}

	
}
