package codemirror.eclipse.ui.xquery.viewers;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import codemirror.eclipse.swt.xquery.addon.variables.IDateFormatter;
import codemirror.eclipse.swt.xquery.addon.variables.XMLDateTimeFormatter;

public class DateTimeCellEditor extends
		AbstractTextAndDialogCellEditor<CalendarDateTimeEditLookupDialog> {

	private IDateFormatter dateFormatter = XMLDateTimeFormatter.getInstance();

	public DateTimeCellEditor(Composite parent) {
		super(parent);
	}

	@Override
	protected CalendarDateTimeEditLookupDialog createDialog(Shell shell,
			String intitialValue) {
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = dateFormatter.format((String) getValue());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if (date != null)
			cal.setTime(date);

		CalendarDateTimeEditLookupDialog dialog = new CalendarDateTimeEditLookupDialog(
				shell, getButton().toDisplay(0, -32));
		dialog.setInitialDate(cal);
		return dialog;
	}

	@Override
	protected String getValue(CalendarDateTimeEditLookupDialog dialog) {
		Calendar date = dialog.getDate();
		if (date != null) {
			return dateFormatter.format(date.getTime());
		}
		return "";
	}

	public void setDateFormatter(IDateFormatter dateFormatter) {
		this.dateFormatter = dateFormatter;
	}

	public IDateFormatter getDateFormatter() {
		return dateFormatter;
	}
}
