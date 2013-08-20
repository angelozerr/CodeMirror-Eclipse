package codemirror.eclipse.swt.builder.addon.hover;

import codemirror.eclipse.swt.builder.AbstractOptionUpdater;
import codemirror.eclipse.swt.builder.CMBuilder;

public class TextHoverImpl extends AbstractOptionUpdater {

	private final String[] scripts;
	private final String[] styles;

	public TextHoverImpl(String[] scripts, String[] styles) {
		this.scripts = scripts;
		this.styles = styles;
	}

	public void install(CMBuilder builder) {
		super.install(builder, scripts, styles);
	}

}
