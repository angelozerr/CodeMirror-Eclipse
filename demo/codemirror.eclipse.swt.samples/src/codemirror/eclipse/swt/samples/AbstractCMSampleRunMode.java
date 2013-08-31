package codemirror.eclipse.swt.samples;

import java.io.File;
import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import codemirror.eclipse.resources.CMResourcesInitializer;
import codemirror.eclipse.swt.CMRunModeControl;
import codemirror.eclipse.swt.builder.CMRunModeBuilder;
import codemirror.eclipse.swt.builder.Mode;

public abstract class AbstractCMSampleRunMode {

	protected void createUI() throws FileNotFoundException {
		CMResourcesInitializer.getInstance()
				.initialize(
						new File("../../core/"
								+ CMResourcesInitializer.getInstance()
										.getResourceId()));
		createUI(getURL(), getMode(), getBuilder());
	}

	private void createUI(String url, Mode mode, CMRunModeBuilder builder)
			throws FileNotFoundException {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(500, 500);
		shell.setText("CodeMirror SWT Eclipse - Run Mode");
		shell.setLayout(new GridLayout());

		final CMRunModeControl editor = createCMControl(url, mode, builder,
				shell);
		editor.setText(getInitialText());
		editor.setLayoutData(new GridData(GridData.FILL_BOTH));

		shell.open();
		editor.setFocus();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	protected CMRunModeControl createCMControl(String url, Mode mode,
			CMRunModeBuilder builder, Composite parent) {
		return builder != null ? new CMRunModeControl(builder, parent,
				SWT.BORDER) : new CMRunModeControl(url, mode, parent,
				SWT.BORDER);
	}

	protected abstract String getURL();

	protected abstract Mode getMode();

	protected abstract CMRunModeBuilder getBuilder();

	protected abstract String getInitialText();
}
