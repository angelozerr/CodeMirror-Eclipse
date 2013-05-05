package codemirror.eclipse.swt.samples;

import java.io.File;
import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import codemirror.eclipse.resources.CMResourcesInitializer;
import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.IDirtyListener;

public abstract class AbstractCMSample {

	protected void createUI() throws FileNotFoundException {
		CMResourcesInitializer.getInstance()
				.initialize(
						new File("../"
								+ CMResourcesInitializer.getInstance()
										.getResourceId()));
		createUI(getURL());
	}

	private void createUI(String url) throws FileNotFoundException {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(500, 500);
		shell.setText("CodeMirror SWT Eclipse");
		shell.setLayout(new GridLayout());

		final Button saveButton = new Button(shell, SWT.PUSH);
		saveButton.setText("Save");
		saveButton.setEnabled(false);
		saveButton.setLayoutData(new GridData());
		
		final CMControl editor = new CMControl(url, shell, SWT.BORDER);
		editor.setText(getInitialText());
		editor.addDirtyListener(new IDirtyListener() {
			public void dirtyChanged(boolean dirty) {
				saveButton.setEnabled(dirty);
			}
		});
		editor.setLayoutData(new GridData(GridData.FILL_BOTH));

		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editor.setDirty(false);
			}
		});
		shell.open();
		editor.setFocus();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	protected abstract String getURL();

	protected abstract String getInitialText();
}
