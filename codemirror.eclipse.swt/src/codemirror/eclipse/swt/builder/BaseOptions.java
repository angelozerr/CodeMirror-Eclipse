package codemirror.eclipse.swt.builder;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import codemirror.eclipse.swt.builder.commands.Command;

public class BaseOptions {

	private final Map<String, Object> options;
	private final CMBuilder builder;

	public BaseOptions(CMBuilder builder) {
		this.builder = builder;
		this.options = new LinkedHashMap<String, Object>();
	}

	public void addOption(String key, Object value) {
		if (value instanceof Command) {
			builder.addCommand((Command) value);
		}
		options.put(key, value);
	}

	public CMBuilder getBuilder() {
		return builder;
	}

	public void write(Writer writer) throws IOException {
		boolean oneOption = isOneOption();
		if (!oneOption) {
			builder.write(writer, "{");
		}
		boolean first = true;
		Set<Entry<String, Object>> entries = options.entrySet();
		for (Entry<String, Object> entry : entries) {
			if (!first) {
				builder.write(writer, ",");
			}
			if (!oneOption) {
				writeOption(writer, entry.getKey(), entry.getValue());
			} else {
				writeValue(writer, entry.getValue());
			}
			first = false;
		}
		if (!oneOption) {
			builder.write(writer, "}");
		}
	}

	protected boolean isOneOption() {
		return options.size() == 1;
	}

	public void writeOption(Writer writer, String key, Object value)
			throws IOException {
		writeKey(writer, key);
		writeValue(writer, value);
	}

	private void writeValue(Writer writer, Object value) throws IOException {
		if (value instanceof BaseOptions) {
			((BaseOptions) value).write(writer);
		} else if (value instanceof Collection) {
			boolean first = true;
			builder.write(writer, "[", false);
			for (Object item : ((Collection) value)) {
				if (!first) {
					builder.write(writer, ",");
				}
				writeValue(writer, item);
				first = false;
			}
			builder.write(writer, "]", false);
		} else if (value instanceof Boolean) {
			builder.write(writer, (Boolean) value ? "true" : "false", false);
		} else if (value instanceof Command) {
			builder.write(writer, "\"", false);
			builder.write(writer, ((Command) value).getName(), false);
			builder.write(writer, "\"", false);
		} else if (value instanceof Function) {
			builder.write(writer, ((Function) value).getScript(), false);
		} else {
			builder.write(writer, "\"", false);
			builder.write(writer, value.toString(), false);
			builder.write(writer, "\"", false);
		}
	}

	private void writeKey(Writer writer, String key) throws IOException {
		builder.write(writer, "\"", false);
		builder.write(writer, key, false);
		builder.write(writer, "\"", false);
		builder.write(writer, ":", false);
	}

	public Object get(String key) {
		return options.get(key);
	}

}
