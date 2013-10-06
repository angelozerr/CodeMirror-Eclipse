package codemirror.eclipse.ui.xquery.viewers;

import java.util.Date;

public interface IDateFormatter {

	String format(Date date);

	Date format(String date);
	
}
