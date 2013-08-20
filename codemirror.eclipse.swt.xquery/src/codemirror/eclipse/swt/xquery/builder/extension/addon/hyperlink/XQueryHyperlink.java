package codemirror.eclipse.swt.xquery.builder.extension.addon.hyperlink;

import codemirror.eclipse.swt.builder.addon.hyperlink.HyperlinkImpl;

public class XQueryHyperlink extends HyperlinkImpl {

	public static final HyperlinkImpl INSTANCE = new XQueryHyperlink();

	public XQueryHyperlink() {
		super(
				new String[] { "scripts/codemirror-xquery/addon/hyperlink/xquery-hyperlink.js" },
				null);
	}

}
