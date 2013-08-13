package codemirror.eclipse.swt.builder.codemirror.addon.fold;

import codemirror.eclipse.swt.builder.Options;
import codemirror.eclipse.swt.builder.codemirror.AbstractOptionUpdater;

public class FoldGutterOptionUpdater extends AbstractOptionUpdater {

	private static final String[] FOLD_JS = {
			"scripts/codemirror/addon/fold/foldcode.js",
			"scripts/codemirror/addon/fold/foldgutter.js",
			"scripts/codemirror/addon/fold/brace-fold.js",
			"scripts/codemirror/addon/fold/comment-fold.js" };
	private static final String[] FOLD_CSS = { "scripts/codemirror-extension/addon/fold/folding-eclipse.css" };

	private static final String FOLDGUTTER_NAME = "foldGutter";

	private static final FoldGutterOptionUpdater INSTANCE = new FoldGutterOptionUpdater();

	public static FoldGutterOptionUpdater getInstance() {
		return INSTANCE;
	}

	public FoldGutterOption getFoldGutter(Options options) {
		FoldGutterOption foldGutter = (FoldGutterOption) options
				.get(FOLDGUTTER_NAME);
		if (foldGutter == null) {
			// add lint.js + lint.css
			super.install(options.getBuilder(), FOLD_JS, FOLD_CSS);
			foldGutter = new FoldGutterOption(options.getBuilder());
			options.addOption(FOLDGUTTER_NAME, foldGutter);
		}
		return foldGutter;
	}

}
