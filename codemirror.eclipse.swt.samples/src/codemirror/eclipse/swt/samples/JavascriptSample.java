package codemirror.eclipse.swt.samples;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.swt.javascript.builder.CMJavascriptBuilder;
import codemirror.eclipse.swt.resources.CMResourcesManager;

public class JavascriptSample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new JavascriptSample().createUI();
	}

	@Override
	protected String getURL() {
		return null;
	}

	@Override
	protected CMBuilder getBuilder() {
		CMBuilder builder = new CMJavascriptBuilder(CMResourcesManager
				.getInstance().getURL(""), false);
		builder.getOptions().setTheme(Theme.ECLIPSE);
		return builder;
	}

	@Override
	protected String getInitialText() {
		return "var a = [];\na;";
	}
}
