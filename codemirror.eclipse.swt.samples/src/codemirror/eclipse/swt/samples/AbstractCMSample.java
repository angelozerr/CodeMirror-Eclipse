package codemirror.eclipse.swt.samples;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.DirtyListener;

public abstract class AbstractCMSample {

	protected void createUI() {
		createUI(getCMFile());
	}

	private void createUI(File cmFile) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(300, 200);
		shell.setText("Button Example");
		shell.setLayout(new GridLayout());

		final Button button = new Button(shell, SWT.PUSH);
		button.setText("Click Me");

		final CMControl editor = new CMControl(cmFile, shell, SWT.BORDER);
		editor.setText(getInitialText());
		editor.addDirtyListener(new DirtyListener() {
			public void dirtyChanged(boolean dirty) {
				System.err.println("dirty=" + dirty);
			}
		});
		editor.setLayoutData(new GridData(GridData.FILL_BOTH));

		//
		// final Text text = new Text(shell, SWT.SHADOW_IN);
		//
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent event) {
				// editor.setText("No worries!");
				System.err.println(editor.getText());
			}

			public void widgetDefaultSelected(SelectionEvent event) {
				editor.setText("No worries!");
			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	protected abstract File getCMFile();

	protected abstract String getInitialText();
}
