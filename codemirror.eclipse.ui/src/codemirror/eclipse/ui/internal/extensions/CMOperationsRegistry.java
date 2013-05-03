package codemirror.eclipse.ui.internal.extensions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IEditorInput;

import codemirror.eclipse.ui.internal.Activator;
import codemirror.eclipse.ui.operations.ICMOperation;

/**
 * Registry which holds instance of {@link ICMOperation} created by the
 * fr.opensagres.mongodb.ide.core.shellRunners" extension point.
 * 
 */
public class CMOperationsRegistry extends AbstractRegistry {

	private static final String OPERATION_ELT = "operation";
	private static final String CM_OPERATIONS_EXTENSION_POINT = "cmOperations";
	private static final CMOperationsRegistry INSTANCE = new CMOperationsRegistry();

	private Map<String, ICMOperation> operations = new HashMap<String, ICMOperation>();

	public static CMOperationsRegistry getRegistry() {
		return INSTANCE;
	}

	public ICMOperation getOperation(IEditorInput editorInput) {
		if (editorInput == null) {
			throw new IllegalArgumentException();
		}
		loadRegistryIfNedded();
		for (ICMOperation operation : operations.values()) {
			if (operation.isAvailable(editorInput)) {
				return operation;
			}
		}
		return null;
	}

	/**
	 * Return the {@link ICMOperation} retrieved by the given id.
	 * 
	 * @param id
	 * @return
	 */
	public ICMOperation getOperation(String id) {
		if (id == null) {
			throw new IllegalArgumentException();
		}
		loadRegistryIfNedded();
		return operations.get(id);
	}

	/**
	 * Return the list of the {@link ICMOperation}.
	 * 
	 * @return
	 */
	public Collection<ICMOperation> getOperations() {
		loadRegistryIfNedded();
		return operations.values();
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

	protected synchronized void loadRegistry() {
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

	/**
	 * Parse elements of the extension poit and create for each runner element
	 * an instance of {@link ICMOperation}.
	 * 
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param cf
	 */
	private void parseShellRunnerManagers(IConfigurationElement[] cf) {
		for (IConfigurationElement ce : cf) {
			String id = null;
			if (OPERATION_ELT.equals(ce.getName())) {
				id = ce.getAttribute(ID_ATTR);
				try {
					ICMOperation operation = (ICMOperation) ce
							.createExecutableExtension(CLASS_ATTR);
					operations.put(id, operation);
				} catch (CoreException e) {
					// Trace.trace(Trace.STRING_SEVERE,
					// "Error while loading shell runtimes manager.", e);
				}
			}
		}

	}

	@Override
	protected String getPluginId() {
		return Activator.PLUGIN_ID;
	}

	@Override
	protected String getExtensionPoint() {
		return CM_OPERATIONS_EXTENSION_POINT;
	}

}
