package codemirror.eclipse.swt.samples;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.css.builder.CMCSSBuilder;
import codemirror.eclipse.swt.resources.CMResourcesManager;

public class CSSSample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new CSSSample().createUI();
	}

	@Override
	protected String getURL() {
		return null;
	}

	@Override
	protected CMBuilder getBuilder() {
		return new CMCSSBuilder(CMResourcesManager.getInstance().getURL(""),
				false);
	}

	@Override
	protected String getInitialText() {
		return "body {color:red;}";
	}
}
