package codemirror.eclipse.swt.builder.addon.hyperlink;

import codemirror.eclipse.swt.builder.AbstractOptionUpdater;
import codemirror.eclipse.swt.builder.Options;

public class HyperlinkOptionUpdater extends AbstractOptionUpdater {

	private static final String[] HYPERLINK_JS = { "scripts/codemirror-extension/addon/hyperlink/hyperlink.js" };
	private static final String[] HYPERLINK_CSS = { "scripts/codemirror-extension/addon/hyperlink/hyperlink.css" };

	private static final HyperlinkOptionUpdater INSTANCE = new HyperlinkOptionUpdater();

	public static HyperlinkOptionUpdater getInstance() {
		return INSTANCE;
	}

	public HyperlinkOption getHyperlink(Options options,
			HyperlinkImpl hyperlinkImpl) {
		HyperlinkOption hyperlink = (HyperlinkOption) options.get("hyperlink");
		if (hyperlink == null) {
			// add hyperlink.js + hyperlink.css
			super.install(options.getBuilder(), HYPERLINK_JS, HYPERLINK_CSS);
			// add implementation of hyperlink
			if (hyperlinkImpl != null) {
				hyperlinkImpl.install(options.getBuilder());
			}
			hyperlink = new HyperlinkOption(options.getBuilder());
			options.addOption("hyperlink", hyperlink);
		}
		return hyperlink;
	}

}
