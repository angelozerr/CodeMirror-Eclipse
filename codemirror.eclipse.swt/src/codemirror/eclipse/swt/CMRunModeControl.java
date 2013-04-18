package codemirror.eclipse.swt;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import codemirror.eclipse.swt.internal.org.apache.commons.lang3.StringEscapeUtils;

public class CMRunModeControl extends AbstractCMControl {

	public CMRunModeControl(File file, Composite parent, int style) {
		super(file, parent, style, SWT.NONE);
	}

	public CMRunModeControl(String url, Composite parent, int style) {
		super(url, parent, style, SWT.NONE);
	}

	public CMRunModeControl(File file, Composite parent, int style,
			int browserStyle) {
		super(file, parent, style, browserStyle);
	}

	public CMRunModeControl(String url, Composite parent, int style,
			int browserStyle) {
		super(url, parent, style, browserStyle);
	}

	@Override
	protected String doGetText() {
		return (String)browser.evaluate("document.getElementById('code').innerHTML;");
	}

	@Override
	protected void doSetText(String text) {
		// CodeMirror.runMode('<a>aaa</a>',
		// "application/xml",document.getElementById('code'))
		StringBuilder js = new StringBuilder("CodeMirror.runMode('");
		js.append(StringEscapeUtils.escapeEcmaScript(text));
		js.append("', '");
		js.append(getMode());
		js.append("', document.getElementById('code'));");
		browser.evaluate(js.toString());
	}

}
