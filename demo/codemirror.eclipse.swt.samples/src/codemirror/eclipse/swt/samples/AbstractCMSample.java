package codemirror.eclipse.swt.samples;

import java.io.File;
import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import codemirror.eclipse.resources.CMResourcesInitializer;
import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.IDirtyListener;
import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.commands.AbstractFormatCommand;

public abstract class AbstractCMSample {

	protected void createUI() throws FileNotFoundException {
		CMResourcesInitializer.getInstance()
				.initialize(
						new File("../../core/"
								+ CMResourcesInitializer.getInstance()
										.getResourceId()));
		createUI(getURL(), getBuilder());
	}

	private void createUI(String url, CMBuilder builder)
			throws FileNotFoundException {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(500, 500);
		shell.setText(getTitle());
		shell.setLayout(new GridLayout());

		final CMControl editor = createCMControl(url, builder, shell);
		editor.setText(getInitialText());
		editor.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Menu
		Menu menuBar = createMenu(shell, editor);
		shell.setMenuBar(menuBar);

		shell.open();
		editor.setFocus();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	protected String getTitle() {
		return "CodeMirror SWT Eclipse";
	}

	private Menu createMenu(final Shell shell, final CMControl editor) {
		Menu menuBar = new Menu(shell, SWT.BAR);
		// File menu
		createFileMenu(shell, menuBar, editor);
		// Source menu
		createSourceMenu(shell, menuBar, editor);
		return menuBar;
	}

	private void createFileMenu(final Shell shell, Menu menuBar,
			final CMControl editor) {
		MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("&File");

		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);

		// Save
		final MenuItem fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
		fileSaveItem.setText("&Save");
		fileSaveItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editor.setDirty(false);
			}
		});

		editor.addDirtyListener(new IDirtyListener() {
			public void dirtyChanged(boolean dirty) {
				fileSaveItem.setEnabled(dirty);
			}
		});

		// Exit
		MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
		fileExitItem.setText("E&xit");
		fileExitItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				shell.close();
				// shell.getDisplay().dispose();
			}
		});
	}

	private void createSourceMenu(Shell shell, Menu menuBar,
			final CMControl editor) {
		MenuItem sourceMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		sourceMenuHeader.setText("&Source");

		Menu sourceMenu = new Menu(shell, SWT.DROP_DOWN);
		sourceMenuHeader.setMenu(sourceMenu);

		// Format
		final MenuItem sourceFormatItem = new MenuItem(sourceMenu, SWT.PUSH);
		sourceFormatItem.setText("&Format");
		sourceFormatItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editor.execCommand(AbstractFormatCommand.FORMAT);
			}
		});
	}

	protected CMControl createCMControl(String url, CMBuilder builder,
			Composite parent) {
		return builder != null ? new CMControl(builder, parent, SWT.BORDER)
				: new CMControl(url, parent, SWT.BORDER);
	}

	protected abstract String getURL();

	protected abstract CMBuilder getBuilder();

	protected abstract String getInitialText();
}
