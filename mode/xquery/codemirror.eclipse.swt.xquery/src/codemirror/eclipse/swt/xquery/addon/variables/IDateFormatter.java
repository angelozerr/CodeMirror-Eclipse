package codemirror.eclipse.swt.xquery.addon.variables;

import java.util.Date;

public interface IDateFormatter {

	String format(Date date);

	Date format(String date);
	
}
