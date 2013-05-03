package codemirror.eclipse.swt.resources;

import java.io.File;

public class AbstractCMResourcesInitializer {

	public static final String CMRESOURCES = "cmresources";

	private final String resourceId;
	private final String alias;
	private CMResources resources;

	public AbstractCMResourcesInitializer(String resourceId, String alias) {
		this.resourceId = resourceId;
		this.alias = alias;
	}

	public void initialize(File baseDir) {
		resources = CMResourcesManager.getInstance().register(resourceId,
				alias, new File(baseDir, alias));
	}

	public void destroy() {
		if (resources != null) {
			CMResourcesManager.getInstance().unregister(resources);
		}
	}

	public String getResourceId() {
		return resourceId;
	}

	public String getAlias() {
		return alias;
	}

}
