package codemirror.eclipse.swt.samples;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.resources.CMResourcesManager;

public class JsonSampleFromHTML extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new JsonSampleFromHTML().createUI();
	}

	@Override
	protected String getURL() {
		return CMResourcesManager.getInstance().getURL(
				CMResourcesConstants.JSON_HTML);
	}

	@Override
	protected CMBuilder getBuilder() {
		return null;
	}

	@Override
	protected String getInitialText() {
		return "{\"key\": \"value\"}";
	}
}
