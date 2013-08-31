package codemirror.eclipse.ui.internal.extensions;

import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.Platform;

public abstract class AbstractRegistry implements IRegistryChangeListener {

	public static final String ID_ATTR = "id";
	public static final String NAME_ATTR = "name";
	public static final String DESCRIPTION_ATTR = "description";
	public static final String CLASS_ATTR = "class";

	private boolean registryListenerIntialized = false;

	public void initialize() {

	}

	public void destroy() {
		Platform.getExtensionRegistry().removeRegistryChangeListener(this);
	}

	private void addRegistryListenerIfNeeded() {
		if (registryListenerIntialized)
			return;

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry != null) {
			registry.addRegistryChangeListener(this, getPluginId());
		}
		registryListenerIntialized = true;
	}

	public void registryChanged(IRegistryChangeEvent event) {
		IExtensionDelta[] deltas = event.getExtensionDeltas(getPluginId(),
				getExtensionPoint());
		if (deltas != null) {
			for (IExtensionDelta delta : deltas)
				handleExtensionDelta(delta);
		}
	}

	protected void loadRegistryIfNedded() {
		if (!isRegistryIntialized()) {
			loadRegistry();
			addRegistryListenerIfNeeded();
		}
	}

	protected boolean isRegistryIntialized() {
		return registryListenerIntialized;
	}

	protected abstract void loadRegistry();

	protected abstract void handleExtensionDelta(IExtensionDelta delta);

	protected abstract String getPluginId();

	protected abstract String getExtensionPoint();
}
