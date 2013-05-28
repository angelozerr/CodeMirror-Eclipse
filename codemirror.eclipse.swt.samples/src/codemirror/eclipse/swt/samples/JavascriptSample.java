package codemirror.eclipse.swt.samples;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.resources.CMResourcesManager;

public class JavascriptSample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new JavascriptSample().createUI();
	}

	@Override
	protected String getURL() {
		return CMResourcesManager.getInstance().getURL(
				CMResourcesConstants.JAVASCRIPT_HTML);
	}

	@Override
	protected String getInitialText() {
		return "var a = [];\na;";
	}
}
