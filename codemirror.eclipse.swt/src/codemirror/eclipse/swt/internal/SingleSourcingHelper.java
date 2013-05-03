package codemirror.eclipse.swt.internal;

import org.eclipse.swt.SWT;

public class SingleSourcingHelper {

	private static boolean isRAP = SWT.getPlatform().equals("rap");

	public static boolean isRAP() {
		return isRAP;
	}
}
