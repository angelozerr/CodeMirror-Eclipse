package codemirror.eclipse.swt.samples;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.resources.CMResourcesManager;

public class JsonSample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new JsonSample().createUI();
	}

	@Override
	protected String getURL() {
		return CMResourcesManager.getInstance().getURL(
				CMResourcesConstants.JSON_HTML);
	}

	@Override
	protected String getInitialText() {
		return "{}";
	}
}
