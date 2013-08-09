package codemirror.eclipse.swt.builder;

public class Mode {

	private final String mimeType;
	private final String[] scripts;

	public Mode(String mimeType, String[] scripts) {
		this.mimeType = mimeType;
		this.scripts = scripts;
	}

	public String getMimeType() {
		return mimeType;
	}

	public String[] getScripts() {
		return scripts;
	}

}
