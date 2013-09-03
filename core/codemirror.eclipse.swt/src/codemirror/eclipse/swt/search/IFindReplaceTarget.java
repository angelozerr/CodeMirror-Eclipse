package codemirror.eclipse.swt.search;

public interface IFindReplaceTarget {

	/**
	 * Returns whether this target can be modified.
	 * 
	 * @return <code>true</code> if target can be modified
	 */
	boolean isEditable();

	boolean findNext(String findString, boolean forwardSearch,
			boolean withOverlay);

	int replaceAll(String query, String text);

	boolean canPerformFind();

	String getSelectionText();

	void replaceSelection(String replaceString);

	String getLineSeparator();
}
