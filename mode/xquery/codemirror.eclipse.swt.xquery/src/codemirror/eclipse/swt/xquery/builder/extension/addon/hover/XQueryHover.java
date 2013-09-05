package codemirror.eclipse.swt.xquery.builder.extension.addon.hover;

import codemirror.eclipse.swt.builder.addon.hover.TextHoverImpl;

public class XQueryHover extends TextHoverImpl {

	public static final TextHoverImpl INSTANCE = new XQueryHover();

	public XQueryHover() {
		super(
				new String[] { "scripts/codemirror-xquery/addon/hover/xquery-hover.js" },
				new String[] { "scripts/codemirror-xquery/addon/hover/xquery-hover.css" });
	}

}
