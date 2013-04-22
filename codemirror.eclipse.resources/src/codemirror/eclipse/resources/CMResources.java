package codemirror.eclipse.resources;

import java.io.File;

public class CMResources {

	private static File baseDir;

	public static void setBaseDir(File baseDir) {
		CMResources.baseDir = baseDir;
	}

	public static File getXQueryResource() {
		return getResource("html/xquery.html");
	}

	public static File getVelocityResource() {
		return getResource("html/velocity.html");
	}

	private static File getResource(String path) {
		if (baseDir == null) {
			throw new RuntimeException(
					"CMResources#setBaseDir(File baseDir) should be initialized.");
		}
		return new File(baseDir, path);
	}
}
