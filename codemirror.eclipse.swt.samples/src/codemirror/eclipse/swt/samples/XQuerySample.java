package codemirror.eclipse.swt.samples;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.resources.CMResourcesManager;

public class XQuerySample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new XQuerySample().createUI();
	}

	@Override
	protected String getURL() {
		return CMResourcesManager.getInstance().getURL(
				CMResourcesConstants.XQUERY_HTML);
	}

	@Override
	protected String getInitialText() {
		return "let $a := 1\nreturn $a";
	}
}
