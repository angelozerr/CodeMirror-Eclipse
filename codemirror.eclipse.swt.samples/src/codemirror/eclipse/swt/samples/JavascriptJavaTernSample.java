package codemirror.eclipse.swt.samples;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.resources.CMResourcesManager;

public class JavascriptJavaTernSample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new JavascriptJavaTernSample().createUI();
	}

	@Override
	protected String getURL() {
		return CMResourcesManager.getInstance().getURL(
				CMResourcesConstants.JAVASCRIPT_JAVATERN_HTML);
	}

	@Override
	protected String getInitialText() {
		return "var a = [];\na;";
	}
}
