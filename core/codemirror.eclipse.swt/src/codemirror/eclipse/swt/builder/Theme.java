package codemirror.eclipse.swt.builder;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Theme {

	private static final Map<String, Theme> themes = new LinkedHashMap<String, Theme>();

	public static final Theme _3024_DAY = createDefaultTheme("3024-day");

	public static final Theme _3024_NIGHT = createDefaultTheme("3024-night");

	public static final Theme AMBIANCE = createDefaultTheme("ambiance");

	public static final Theme BASE16_DARK = createDefaultTheme("base16-dark");

	public static final Theme BASE16_LIGHT = createDefaultTheme("base16-light");

	public static final Theme BLACKBOARD = createDefaultTheme("blackboard");

	public static final Theme COBALT = createDefaultTheme("cobalt");

	public static final Theme ECLIPSE = createDefaultTheme("eclipse");

	public static final Theme ELEGANT = createDefaultTheme("elegant");

	public static final Theme ERLANG_DARK = createDefaultTheme("erlang-dark");

	public static final Theme LESSER_DARK = createDefaultTheme("lesser-dark");

	public static final Theme MIDNIGHT = createDefaultTheme("midnight");

	public static final Theme MONOKAI = createDefaultTheme("monokai");

	public static final Theme NEAT = createDefaultTheme("neat");

	public static final Theme NEO = createDefaultTheme("neo");

	public static final Theme NIGHT = createDefaultTheme("night");

	public static final Theme PARAISO_DARK = createDefaultTheme("paraiso-dark");

	public static final Theme PARAISO_LIGHT = createDefaultTheme("paraiso-light");

	public static final Theme RUBYBLUE = createDefaultTheme("rubyblue");

	public static final Theme SOLARIZED_DARK = createDefaultTheme("solarized dark");

	public static final Theme SOLARIZED_LIGHT = createDefaultTheme("solarized light");

	public static final Theme THE_MATRIX = createDefaultTheme("the-matrix");

	public static final Theme TOMORROW_NIGHT_EIGHTIES = createDefaultTheme("tomorrow-night-eighties");

	public static final Theme TWILIGHT = createDefaultTheme("twilight");

	public static final Theme VIBRANT_INK = createDefaultTheme("vibrant-ink");

	public static final Theme XQ_DARK = createDefaultTheme("xq-dark");

	public static final Theme XQ_LIGHT = createDefaultTheme("xq-light");

	private final String name;
	private final String style;

	public Theme(String name, String style) {
		this.name = name;
		this.style = style;
		registerTheme(this);
	}

	public String getName() {
		return name;
	}

	public String getStyle() {
		return style;
	}

	public static void registerTheme(Theme theme) {
		themes.put(theme.getName(), theme);
	}

	public static void unregisterTheme(Theme theme) {
		themes.remove(theme);
	}

	public static Collection<Theme> getAll() {
		return themes.values();
	}

	public static Theme getTheme(String name) {
		return themes.get(name);
	}

	private static Theme createDefaultTheme(String name) {
		return new Theme(name, new StringBuilder("scripts/codemirror/theme/")
				.append(name).append(".css").toString());
	}
}
