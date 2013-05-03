package codemirror.eclipse.swt.resources;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CMResourcesManager {

	public static final CMResourcesManager INSTANCE = new CMResourcesManager();

	public static CMResourcesManager getInstance() {
		return INSTANCE;
	}

	private final Map<String, CMResources> resourcesMap;

	public CMResourcesManager() {
		this.resourcesMap = new LinkedHashMap<String, CMResources>();
	}

	public CMResources register(CMResources resources) {
		this.resourcesMap.put(resources.getId(), resources);
		return resources;
	}

	public CMResources register(String resourceId, String alias, File baseDir) {
		return register(new CMResources(resourceId, baseDir, alias));
	}

	public void unregister(CMResources resources) {
		this.resourcesMap.remove(resources.getId());
	}

	public String getURL(String filename) {
		Collection<CMResources> r = this.resourcesMap.values();
		for (CMResources resources : r) {
			return resources.getURL(filename);
		}
		return null;
	}

	public String getURL(String filename, String resourcesId) {
		CMResources resources = this.resourcesMap.get(resourcesId);
		if (resources != null) {
			return resources.getURL(filename);
		}
		return null;
	}
}
