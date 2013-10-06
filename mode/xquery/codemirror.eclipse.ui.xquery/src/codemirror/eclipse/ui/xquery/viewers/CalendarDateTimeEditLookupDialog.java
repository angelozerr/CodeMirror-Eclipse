package codemirror.eclipse.ui.xquery.viewers;

import java.text.spi.DateFormatProvider;
import java.util.Calendar;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import codemirror.eclipse.ui.xquery.internal.Messages;

public class CalendarDateTimeEditLookupDialog extends Dialog {
	private long flags;
	private DateTime calendarDateTime;
	private DateTime timeDateTime;
	private DateTime dateDateTime;
	private Calendar date;
	private Point initialLocation;
	private Boolean allowPast;

	/**
	 * Create a new CalendarDateTimeEditLookupDialog instance.
	 * 
	 * @param parentShell
	 *            The parent shell
	 * @param flags
	 *            One of the "FLAGS_"-constants in {@link DateFormatProvider}.
	 */
	public CalendarDateTimeEditLookupDialog(Shell parentShell, long flags) {
		this(parentShell, flags, null);
	}

	/**
	 * Create a new CalendarDateTimeEditLookupDialog instance.
	 * 
	 * @param parentShell
	 *            The parent shell
	 * @param flags
	 *            One of the "FLAGS_"-constants in {@link DateFormatProvider}.
	 * @param initialLocation
	 *            The initial location for this dialog
	 */
	public CalendarDateTimeEditLookupDialog(Shell parentShell, long flags,
			Point initialLocation) {
		this(parentShell, true, flags, null);
	}

	/**
	 * Create a new CalendarDateTimeEditLookupDialog instance.
	 * 
	 * @param parentShell
	 *            The parent shell
	 * @param flags
	 *            One of the "FLAGS_"-constants in {@link DateFormatProvider}.
	 * @param initialLocation
	 *            The initial location for this dialog
	 * @param allowPast
	 *            don't allow the user to select a past date
	 */
	public CalendarDateTimeEditLookupDialog(Shell parentShell,
			boolean allowPast, long flags, Point initialLocation) {
		super(parentShell);
		date = Calendar.getInstance();
		this.flags = flags;
		this.initialLocation = initialLocation;
		this.allowPast = allowPast;
	}

	/**
	 * Set the initial date to show in this dialog. This must be called before
	 * {@link #open()} to have any effect. Internally, a copy of the given
	 * {@link Calendar} instance is used. The given instance is never changed.
	 * Call {@link #getDate()} to get the date selected by the user after
	 * {@link #open()} returns.
	 * 
	 * @param initialDate
	 *            The initial date to set
	 */
	public void setInitialDate(Calendar initialDate) {
		date = (Calendar) initialDate.clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.Dialog#getInitialLocation(org.eclipse.swt.graphics
	 * .Point)
	 */
	@Override
	protected Point getInitialLocation(Point initialSize) {
		if (initialLocation != null)
			return initialLocation;
		return super.getInitialLocation(initialSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#create()
	 */
	@Override
	public void create() {
		super.create();
		// getShell()
		// .setText(
		//				Messages.getString("org.nightlabs.base.ui.composite.CalendarDateTimeEditLookupDialog.title")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite page = (Composite) super.createDialogArea(parent);
		int numColumns = 0;

		// if ((DateFormatProvider.DATE & flags) == DateFormatProvider.DATE) {
		++numColumns;
		Composite dateComp = new Composite(page, SWT.NONE);
		GridLayout layout = new GridLayout(numColumns, true);
		dateComp.setLayout(layout);

		calendarDateTime = new DateTime(dateComp, SWT.CALENDAR | SWT.BORDER);
		calendarDateTime.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		calendarDateTime.setDate(date.get(Calendar.YEAR),
				date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
		// DateTimeUtil.setDate(date, calendarDateTime);

		calendarDateTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Calendar cal = Calendar.getInstance();
				// cal.setTime(DateTimeUtil.getDate(calendarDateTime));

				if (getDate().after(cal) && !allowPast) {
					/*
					 * MessageDialog.openError( getShell(), Messages.getString(
					 * "org.nightlabs.base.ui.composite.CalendarDateTimeEditLookupDialog.errorDialog.title"
					 * ), Messages.getString(
					 * "org.nightlabs.base.ui.composite.CalendarDateTimeEditLookupDialog.errorDialog.message"
					 * )); //$NON-NLS-1$ //$NON-NLS-2$
					 * DateTimeUtil.setDate(getDate(), calendarDateTime);
					 */
				} else {
					if (dateDateTime != null) { // TODO that thing is
												// commented out below -
												// shall we remove it
												// completely? Marco.
						dateDateTime.setYear(calendarDateTime.getYear());
						dateDateTime.setMonth(calendarDateTime.getMonth());
						dateDateTime.setDay(calendarDateTime.getDay());
					}
				}
			}
		});

		// if ((DateFormatProvider.TIME & flags) != DateFormatProvider.TIME)
		// createDateDateTime(dateComp);
		// }

		  ++numColumns; 
		  Composite timeComp = new Composite(page, SWT.NONE); //
			GridLayout l = new GridLayout(numColumns, true);
			timeComp.setLayout(l);
		  
		  //new Label(timeComp, SWT.NONE).setText(Messages.getString(
		  //"CalendarDateTimeEditLookupDialog.label.date")); //$NON-NLS-1$ //
		  //createDateDateTime(timeComp);
		  
		  int timeStyle = SWT.TIME;
		  
		  /*if ((DateFormatProvider.TIME_SEC & flags) ==
		  DateFormatProvider.TIME_SEC) timeStyle |= SWT.MEDIUM; else if
		  ((DateFormatProvider.TIME_MSEC & flags) ==
		  DateFormatProvider.TIME_MSEC) timeStyle |= SWT.LONG; else timeStyle
		  |= SWT.SHORT;
		  
		  new Label(timeComp, SWT.NONE).setText(Messages
		  .getString("CalendarDateTimeEditLookupDialog.label.time"));
		  //$NON-NLS-1$ 
		  */
		  timeDateTime = new DateTime(timeComp, timeStyle |
		  SWT.BORDER); 
		  timeDateTime.setLayoutData(new
		  GridData(GridData.FILL_HORIZONTAL)); 
		  timeDateTime.setHours(date.get(Calendar.HOUR_OF_DAY));
		  timeDateTime.setMinutes(date.get(Calendar.MINUTE));
		  timeDateTime.setSeconds(date.get(Calendar.SECOND));
		//DateTimeUtil.setDate(date, timeDateTime); 
		 

		page.setLayout(new GridLayout(numColumns, false));
		return page;
	}

	// private void createDateDateTime(Composite parent) {
	// dateDateTime = new DateTime(parent, SWT.DATE | SWT.BORDER);
	// dateDateTime.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	// DateTimeUtil.setDate(date, dateDateTime);
	// dateDateTime.addSelectionListener(new SelectionAdapter(){
	// @Override
	// public void widgetSelected(SelectionEvent e) {
	//
	//
	// Calendar cal = Calendar.getInstance();
	// cal.setTime(DateTimeUtil.getDate(dateDateTime));
	//
	// if(getDate().after(cal) && !allowPast )
	// {
	// MessageDialog.openError(getShell(), "Past date",
	// "You are not allowed to set a date in the past only future ones");
	// DateTimeUtil.setDate(getDate(), dateDateTime);
	// }
	// else
	// {
	// calendarDateTime.setYear(dateDateTime.getYear());
	// calendarDateTime.setMonth(dateDateTime.getMonth());
	// calendarDateTime.setDay(dateDateTime.getDay());
	// }
	//
	// }
	// });
	// }

	public Calendar getDate() {
		return date;
	}

	@Override
	protected void okPressed() {
		getButton(OK).setFocus();

		if (calendarDateTime != null) {
			date.set(Calendar.YEAR, calendarDateTime.getYear());
			date.set(Calendar.MONTH, calendarDateTime.getMonth());
			date.set(Calendar.DAY_OF_MONTH, calendarDateTime.getDay());
		}

		if (timeDateTime != null) {
			date.set(Calendar.HOUR_OF_DAY, timeDateTime.getHours());
			date.set(Calendar.MINUTE, timeDateTime.getMinutes());
			date.set(Calendar.SECOND, timeDateTime.getSeconds());
		}
		super.okPressed();
	}

	public Boolean getAllowPast() {
		return allowPast;
	}

	public void setAllowPast(Boolean dontAllowPast) {
		this.allowPast = dontAllowPast;
	}

}
