package codemirror.eclipse.swt.addon.lint;

public interface LintHandler {

	void startAnnotations();
	
	void endAnnotations();
	
	void addAnnotation(int startLine, int startChar, int endLine, int endChar,
			String message);
}
