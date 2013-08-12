package codemirror.eclipse.swt.samples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import codemirror.eclipse.swt.CMControl;
import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Theme;
import codemirror.eclipse.swt.resources.CMResourcesManager;
import codemirror.eclipse.swt.xquery.CMXQueryControl;
import codemirror.eclipse.swt.xquery.builder.CMXQueryBuilder;

public class XQuerySample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new XQuerySample().createUI();
	}

	@Override
	protected String getURL() {
		return null;
	}

	@Override
	protected CMBuilder getBuilder() {
		CMBuilder builder = new CMXQueryBuilder(CMResourcesManager
				.getInstance().getURL(""));
		builder.getOptions().setTheme(Theme.XQ_LIGHT);
		return builder;
	}

	@Override
	protected String getInitialText() {
		return "let $a := 1\nif (true) then \nelse \nend\nreturn $a";
	}

	protected CMControl createCMControl(String url, CMBuilder builder,
			Composite parent) {
		return builder != null ? new CMXQueryControl(builder, parent,
				SWT.BORDER) : new CMXQueryControl(url, parent, SWT.BORDER);
	}
}
