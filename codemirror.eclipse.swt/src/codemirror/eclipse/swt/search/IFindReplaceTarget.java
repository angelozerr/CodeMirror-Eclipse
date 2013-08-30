package codemirror.eclipse.swt.search;

public interface IFindReplaceTarget {

	/**
	 * Returns whether this target can be modified.
	 * 
	 * @return <code>true</code> if target can be modified
	 */
	boolean isEditable();

	void search(String findString, boolean b);

	boolean canPerformFind();

}
