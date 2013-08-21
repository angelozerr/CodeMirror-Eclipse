package codemirror.eclipse.swt.builder;

public class AbstractOptionUpdater {

	protected void install(AbstractCMBuilder builder, String[] scripts,
			String[] styles) {
		builder.addScripts(scripts);
		builder.addStyles(styles);
	}
}
