package codemirror.eclipse.swt.addon.lint;

public interface LintProcessor {

	String getMode();
	
	void lint(String code, LintHandler handler, Object context);
	
}
