package codemirror.eclipse.swt.samples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.CMRunModeBuilder;
import codemirror.eclipse.swt.builder.Mode;
import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.swt.resources.CMResourcesManager;
import codemirror.eclipse.swt.xquery.CMXQueryControl;
import codemirror.eclipse.swt.xquery.builder.CMXQueryRunModeBuilder;

public class XQuerySampleRunMode extends AbstractCMSampleRunMode {

	public static void main(String[] args) throws Exception {
		new XQuerySampleRunMode().createUI();
	}

	@Override
	protected String getURL() {
		return null;
	}

	@Override
	protected Mode getMode() {
		return null;
	}

	@Override
	protected CMRunModeBuilder getBuilder() {
		CMRunModeBuilder builder = new CMXQueryRunModeBuilder(CMResourcesManager
				.getInstance().getURL(""));
		builder.setTheme(Theme.XQ_LIGHT);
		return builder;
	}

	@Override
	protected String getInitialText() {
		return "let $a := 1\nif (true) then \nelse \n()\nreturn $a";
	}

	protected CMControl createCMControl(String url, CMBuilder builder,
			Composite parent) {
		return builder != null ? new CMXQueryControl(builder, parent,
				SWT.BORDER) : new CMXQueryControl(url, parent, SWT.BORDER);
	}

}
