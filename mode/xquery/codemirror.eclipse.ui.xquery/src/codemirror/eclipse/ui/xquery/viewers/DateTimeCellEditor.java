package codemirror.eclipse.ui.xquery.viewers;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class DateTimeCellEditor extends DialogCellEditor {

	private boolean allowPast = true;
	private long flags;
	private String date;
	private IDateFormatter dateFormatter = XMLDateTimeFormatter.getInstance();

	/**
	 * 
	 * @param parent
	 * @param flags
	 *            One of the "FLAGS_"-constants in {@link DateFormatter}.
	 */
	public DateTimeCellEditor(Composite parent, long flags) {
		this(parent, flags, true);
	}

	/**
	 * 
	 * @param parent
	 * @param flags
	 *            One of the "FLAGS_"-constants in {@link DateFormatter}.
	 */
	public DateTimeCellEditor(Composite parent, long flags, boolean allowPast) {
		super(parent);
		this.flags = flags;
		this.allowPast = allowPast;
	}

	@Override
	protected Object doGetValue() {
		return date;
	}

	private boolean ignoreTextModifyEvents = false;
	private boolean textModified = false;
	private boolean overDefaultButton = false;

	@Override
	protected void doSetValue(Object value) {
		ignoreTextModifyEvents = true;
		try {
			this.date = getDateAsString(value);
			if (text != null && !text.isDisposed()) {
				text.setText(date);
			}
			textModified = false;
		} finally {
			ignoreTextModifyEvents = false;
		}
	}

	private String getDateAsString(Object value) {
		if (value == null) {
			return "";
		}
		if (value instanceof String) {
			return (String) value;
		}
		if (value instanceof Date) {
			return dateFormatter.format((Date) value);
		}
		return "";
	}

	private Button defaultButton;

	@Override
	protected Button createButton(Composite parent) {
		defaultButton = super.createButton(parent);
		defaultButton.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				overDefaultButton = true;
			}

			@Override
			public void mouseExit(MouseEvent e) {
				overDefaultButton = false;
			}
		});
		return defaultButton;
	}

	@Override
	protected void doSetFocus() {
		if (text != null && !text.isDisposed())
			text.setFocus();
		else
			super.doSetFocus();
	}

	private Text text;

	@Override
	protected Control createContents(Composite cell) {
		// return super.createContents(cell);
		if (text != null) {
			text.dispose();
			text = null;
		}

		text = new Text(cell, SWT.BORDER);
		text.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent event) {
				if (ignoreTextModifyEvents)
					return;

				textModified = true;
			}
		});
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent event) {
				if (textModified) {
					String txt = text.getText();
					Date date;
					/*
					 * try { date = DateFormatter.parseDate(txt); } catch
					 * (DateParseException e) { MessageDialog.openError(
					 * text.getShell(), "Invalid date", String.format(
					 * "The text \"%s\" cannot be interpeted as a date!", txt));
					 * return; } setValue(date);
					 */
					// setValue(Calendar.getInstance().getTime());
					setValue(txt);
				}
				if (!overDefaultButton)
					fireApplyEditorValue();
			}
		});
		return text;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		CalendarDateTimeEditLookupDialog dialog = new CalendarDateTimeEditLookupDialog(
				cellEditorWindow.getShell(), allowPast, flags,
				defaultButton.toDisplay(0, -32));
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = dateFormatter.format((String) getValue());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if (date != null)
			cal.setTime(date);

		dialog.setInitialDate(cal);
		if (dialog.open() == Window.OK) {
			date = dialog.getDate().getTime();
			setValue(date);
		}
		return date;
	}

	public long getFlags() {
		return flags;
	}

	public boolean isAllowPast() {
		return allowPast;
	}

	public void setAllowPast(boolean allowPast) {
		this.allowPast = allowPast;
	}
}
