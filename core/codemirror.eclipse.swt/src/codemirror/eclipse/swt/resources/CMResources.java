package codemirror.eclipse.swt.resources;

import java.io.File;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.internal.SingleSourcingHelper;

public class CMResources {

	private final String id;

	private final File baseDir;

	private final String alias;

	public CMResources(String id, File baseDir, String alias) {
		this.id = id;
		this.baseDir = baseDir;
		this.alias = alias;
	}

	public String getId() {
		return id;
	}

	public String getURL(String filename) {
		if (alias != null) {
			if (SingleSourcingHelper.isRAP()) {
				return new StringBuilder(alias).append("/").append(filename)
						.toString();
			}
		}
		return CMControl.toURL(new File(baseDir, filename));
	}
}
