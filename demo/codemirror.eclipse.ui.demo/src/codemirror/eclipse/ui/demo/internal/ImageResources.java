package codemirror.eclipse.ui.demo.internal;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

import codemirror.eclipse.ui.demo.Activator;

public class ImageResources {

	public final static String ICONS_PATH = "icons/"; //$NON-NLS-1$

	/**
	 * Set of predefined Image Descriptors.
	 */
	public static final String PATH_OBJ_16 = ICONS_PATH + "obj16/"; //$NON-NLS-1$
	public static final String PATH_CLCL_16 = ICONS_PATH + "clcl16/"; //$NON-NLS-1$
	public static final String PATH_DLCL_16 = ICONS_PATH + "dlcl16/"; //$NON-NLS-1$
	public static final String PATH_ELCL_16 = ICONS_PATH + "elcl16/"; //$NON-NLS-1$

	public static final String IMG_OBJ_FOLDER = "folder";

	public static void initialize(ImageRegistry imageRegistry) {
		registerImage(imageRegistry, IMG_OBJ_FOLDER, PATH_OBJ_16 + "folder.gif");
	}

	private static void registerImage(ImageRegistry registry, String key,
			String fileName) {
		try {
			IPath path = new Path(fileName);
			Bundle bundle = Activator.getDefault().getBundle();
			URL url = FileLocator.find(bundle, path, null);
			if (url != null) {
				ImageDescriptor desc = ImageDescriptor.createFromURL(url);
				registry.put(key, desc);
			}
		} catch (Exception e) {
		}
	}

	public static ImageDescriptor getImageDescriptor(String key) {
		ImageRegistry imageRegistry = Activator.getDefault().getImageRegistry();
		ImageDescriptor img = imageRegistry.getDescriptor(key);
		if (img == null) {
			registerImage(imageRegistry, Activator.PLUGIN_ID, key);
			img = imageRegistry.getDescriptor(key);
		}
		return img;
	}

	public static Image getImage(String key) {
		ImageRegistry imageRegistry = Activator.getDefault().getImageRegistry();
		Image img = imageRegistry.get(key);
		if (img == null) {
			registerImage(imageRegistry, Activator.PLUGIN_ID, key);
			img = imageRegistry.get(key);
		}
		return img;
	}

}
