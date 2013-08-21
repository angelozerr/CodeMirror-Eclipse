package codemirror.eclipse.swt.builder.addon.fold;

public class FoldType {

	public static final FoldType[] EMPTY = new FoldType[0];

	public static final FoldType BRACE_FOLD = new FoldType("Brace",
			"CodeMirror.fold.brace",
			new String[] { "scripts/codemirror/addon/fold/brace-fold.js" });

	public static final FoldType COMMENT_FOLD = new FoldType("Comment",
			"CodeMirror.fold.comment",
			new String[] { "scripts/codemirror/addon/fold/comment-fold.js" });

	public static final FoldType XML_FOLD = new FoldType("XML",
			"CodeMirror.fold.xml",
			new String[] { "scripts/codemirror/addon/fold/xml-fold.js" });

	private final String name;
	private final String js;
	private final String[] scripts;

	public FoldType(String name, String js, String[] scripts) {
		this.name = name;
		this.js = js;
		this.scripts = scripts;
	}

	public String getJS() {
		return js;
	}

	public String getName() {
		return name;
	}

	public String[] getScripts() {
		return scripts;
	}
}
