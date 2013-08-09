package codemirror.eclipse.swt.builder.codemirror.addon.lint;

import java.io.IOException;
import java.io.Writer;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.Option;
import codemirror.eclipse.swt.builder.Options;

public class LintOption extends Option {

	private Boolean lint;

	private String getAnnotations;

	private Boolean async;

	public LintOption() {

	}

	public Boolean getLint() {
		return lint;
	}

	public void setLint(Boolean lint) {
		this.lint = lint;
	}

	public String getGetAnnotations() {
		return getAnnotations;
	}

	public void setGetAnnotations(String getAnnotations) {
		this.getAnnotations = getAnnotations;
	}

	public Boolean getAsync() {
		return async;
	}

	public void setAsync(Boolean async) {
		this.async = async;
	}

	@Override
	public void write(Options options, Writer writer) throws IOException {
		CMBuilder builder = options.getBuilder();
		if (lint != null) {
			builder.write(writer, lint ? "true" : "false", false);
		} else {

		}

	}

}
