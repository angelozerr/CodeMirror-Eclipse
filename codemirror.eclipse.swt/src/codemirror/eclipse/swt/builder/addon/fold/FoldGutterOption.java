package codemirror.eclipse.swt.builder.addon.fold;

import codemirror.eclipse.swt.builder.BaseOptions;
import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Function;

public class FoldGutterOption extends BaseOptions {

	public FoldGutterOption(CMBuilder builder) {
		super(builder);
	}

	public void setRangeFinder(String rangeFinder) {
		super.addOption("rangeFinder", new Function(rangeFinder));
	}

	public void setRangeFinder(FoldType[] foldTypes) {
		StringBuilder rangeFinder = new StringBuilder(
				"new CodeMirror.fold.combine(");
		boolean first = true;
		for (FoldType foldType : foldTypes) {
			super.getBuilder().addScripts(foldType.getScripts());
			if (!first) {
				rangeFinder.append(",");
			}
			rangeFinder.append(foldType.getJS());
			first = false;
		}
		rangeFinder.append(")");
		setRangeFinder(rangeFinder.toString());
	}

	@Override
	protected boolean isOneOption() {
		return false;
	}

}
