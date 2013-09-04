package codemirror.eclipse.swt.builder.addon.hover;

import codemirror.eclipse.swt.builder.BaseOptions;
import codemirror.eclipse.swt.builder.CMBuilder;

public class TextHoverOption extends BaseOptions {

	public TextHoverOption(CMBuilder builder) {
		super(builder);
	}

	public void setTextHover(boolean textHover) {
		super.addOption("textHover", textHover);
	}

	public void setDelay(Integer delay) {
		if (delay == null) {
			super.removeOption("delay");
		} else {
			super.addOption("delay", delay);
		}
	}

}
