package codemirror.eclipse.ui.resources;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import codemirror.eclipse.swt.resources.CMResourcesManager;
import codemirror.eclipse.ui.internal.Activator;
import codemirror.eclipse.ui.internal.extensions.AbstractRegistry;

public class CMResourcesRegistry extends AbstractRegistry {

	private static final String RESOURCE_ELT = "resource";
	private static final String CM_RESOURCES_EXTENSION_POINT = "cmResources";
	private static final CMResourcesRegistry INSTANCE = new CMResourcesRegistry();

	public static CMResourcesRegistry getRegistry() {
		return INSTANCE;
	}

	public String getURL(String filename) {
		loadRegistryIfNedded();
		return CMResourcesManager.getInstance().getURL(filename);
	}

	@Override
	protected void loadRegistry() {
		if (isRegistryIntialized()) {
			return;
		}
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry != null) {
			IConfigurationElement[] cf = registry.getConfigurationElementsFor(
					getPluginId(), getExtensionPoint());
			parseShellRunnerManagers(cf);
		}
	}

	private void parseShellRunnerManagers(IConfigurationElement[] cf) {
		for (IConfigurationElement ce : cf) {
			if (RESOURCE_ELT.equals(ce.getName())) {
				String pluginID = ce.getContributor().getName();
				String resourceId = ce.getAttribute(ID_ATTR);
				if (resourceId == null) {
					resourceId = pluginID;
				}
				try {
					String alias = ce.getAttribute("base-name");
					File baseDir = FileLocator.getBundleFile(Platform
							.getBundle(pluginID));
					CMResourcesManager.getInstance().register(resourceId,
							"/" + alias, new File(baseDir, alias));
				} catch (IOException e) {
				}
			}
		}

	}

	@Override
	protected void handleExtensionDelta(IExtensionDelta delta) {
		if (delta.getKind() == IExtensionDelta.ADDED) {
			IConfigurationElement[] cf = delta.getExtension()
					.getConfigurationElements();
			parseShellRunnerManagers(cf);
		} else {
			// TODO : remove references
		}

	}

	@Override
	protected String getPluginId() {
		return Activator.PLUGIN_ID;
	}

	@Override
	protected String getExtensionPoint() {
		return CM_RESOURCES_EXTENSION_POINT;
	}

}
