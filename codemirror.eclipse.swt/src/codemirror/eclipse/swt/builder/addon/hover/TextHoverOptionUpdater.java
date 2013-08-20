package codemirror.eclipse.swt.builder.addon.hover;

import codemirror.eclipse.swt.builder.AbstractOptionUpdater;
import codemirror.eclipse.swt.builder.Options;

public class TextHoverOptionUpdater extends AbstractOptionUpdater {

	private static final String[] TEXTHOVER_JS = { "scripts/codemirror-extension/addon/hover/text-hover.js" };
	private static final String[] TEXTHOVER_CSS = { "scripts/codemirror-extension/addon/hover/text-hover.css" };

	private static final TextHoverOptionUpdater INSTANCE = new TextHoverOptionUpdater();

	public static TextHoverOptionUpdater getInstance() {
		return INSTANCE;
	}

	public TextHoverOption getTextHover(Options options,
			TextHoverImpl textHoverImpl) {
		TextHoverOption textHover = (TextHoverOption) options.get("textHover");
		if (textHover == null) {
			// add text-hover.js + text-hover.css
			super.install(options.getBuilder(), TEXTHOVER_JS, TEXTHOVER_CSS);
			// add implementation of textHover
			if (textHoverImpl != null) {
				textHoverImpl.install(options.getBuilder());
			}
			textHover = new TextHoverOption(options.getBuilder());
			options.addOption("textHover", textHover);
		}
		return textHover;
	}

}
