package codemirror.eclipse.swt.builder;

public class Theme {

	public static final Theme ECLIPSE = new Theme("eclipse",
			"scripts/codemirror/theme/eclipse.css");
	
	public static final Theme XQ_LIGHT = new Theme("xq-light",
			"scripts/codemirror/theme/xq-light.css");

	private final String name;
	private final String style;

	public Theme(String name, String style) {
		this.name = name;
		this.style = style;
	}

	public String getName() {
		return name;
	}

	public String getStyle() {
		return style;
	}
}
