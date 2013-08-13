package codemirror.eclipse.swt.builder.extension.addon.hover;

import codemirror.eclipse.swt.builder.BaseOptions;
import codemirror.eclipse.swt.builder.CMBuilder;

public class TextHoverOption extends BaseOptions {

	public TextHoverOption(CMBuilder builder) {
		super(builder);
	}

	public void setTextHover(boolean textHover) {
		super.addOption("textHover", textHover);
	}

}
