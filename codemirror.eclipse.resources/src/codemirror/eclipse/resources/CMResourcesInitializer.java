package codemirror.eclipse.resources;

import codemirror.eclipse.swt.resources.AbstractCMResourcesInitializer;

public class CMResourcesInitializer extends
		AbstractCMResourcesInitializer {

	private static final CMResourcesInitializer INSTANCE = new CMResourcesInitializer();

	public static CMResourcesInitializer getInstance() {
		return INSTANCE;
	}

	public CMResourcesInitializer() {
		super("codemirror.eclipse.resources", "cmresources");
	}

}
