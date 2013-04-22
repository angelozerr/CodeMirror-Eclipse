package codemirror.eclipse.swt.samples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import codemirror.eclipse.resources.CMResources;
import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.IDirtyListener;

public abstract class AbstractCMSample {

	protected void createUI() throws FileNotFoundException {
		CMResources.setBaseDir(new File("../codemirror.eclipse.resources"));
		createUI(getCMFile());
	}

	private void createUI(File cmFile) throws FileNotFoundException {
		if (!cmFile.exists()) {
			throw new FileNotFoundException(cmFile.getPath());
		}
		
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(300, 200);
		shell.setText("Button Example");
		shell.setLayout(new GridLayout());

		final Button button = new Button(shell, SWT.PUSH);
		button.setText("Click Me");

		final CMControl editor = new CMControl(cmFile, shell, SWT.BORDER);
		editor.setText(getInitialText());
		editor.addDirtyListener(new IDirtyListener() {
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
		editor.setFocus();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	protected abstract File getCMFile();

	protected abstract String getInitialText();
}
