package codemirror.eclipse.swt.samples;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.sql.builder.CMSQLBuilder;
import codemirror.eclipse.swt.resources.CMResourcesManager;

public class SQLSample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new SQLSample().createUI();
	}

	@Override
	protected String getURL() {
		return null;
	}

	@Override
	protected CMBuilder getBuilder() {
		return new CMSQLBuilder(CMResourcesManager.getInstance().getURL(""));
	}

	@Override
	protected String getInitialText() {
		return "SELECT * FROM Websites WHERE URL LIKE 'http://codemirror.net'";
	}

}
