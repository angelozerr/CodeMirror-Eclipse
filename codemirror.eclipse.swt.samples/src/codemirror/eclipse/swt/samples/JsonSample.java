package codemirror.eclipse.swt.samples;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.swt.json.builder.CMJsonBuilder;
import codemirror.eclipse.swt.resources.CMResourcesManager;

public class JsonSample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new JsonSample().createUI();
	}

	@Override
	protected String getURL() {
		return null;
	}

	@Override
	protected CMBuilder getBuilder() {
		CMBuilder builder = new CMJsonBuilder(CMResourcesManager.getInstance()
				.getURL(""), false);
		builder.getOptions().setTheme(Theme.ECLIPSE);
		return builder;
	}

	@Override
	protected String getInitialText() {
		return "{\"key\": \"value\"}";
	}
}
