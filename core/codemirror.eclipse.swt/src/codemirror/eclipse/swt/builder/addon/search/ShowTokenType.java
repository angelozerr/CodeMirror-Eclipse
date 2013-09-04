package codemirror.eclipse.swt.builder.addon.search;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.builder.TokenType;
import codemirror.eclipse.swt.builder.Writable;

public class ShowTokenType implements Writable {

	private static final Collection<ShowTokenType> tokenTypes = new ArrayList<ShowTokenType>();

	public static final ShowTokenType VARIABLE = new ShowTokenType(
			"matchhighlight-variable", TokenType.VARIABLE);

	public static final ShowTokenType PROPERTY = new ShowTokenType(
			"matchhighlight-property", TokenType.PROPERTY);

	public static final ShowTokenType DEF = new ShowTokenType(
			"matchhighlight-def", TokenType.DEF);

	public static final ShowTokenType VARIABLE_DEF = new ShowTokenType(
			"matchhighlight-def", TokenType.VARIABLE, TokenType.DEF);

	private final String type;
	private final String style;

	public ShowTokenType(TokenType... tokenType) {
		this(null, tokenType);
	}

	public ShowTokenType(String style, TokenType... tokenTypes) {
		this.type = getType(tokenTypes);
		this.style = style;
		ShowTokenType.tokenTypes.add(this);
	}

	private String getType(TokenType[] tokenTypes) {
		StringBuilder type = new StringBuilder();
		for (int i = 0; i < tokenTypes.length; i++) {
			if (i > 0) {
				type.append(" ");
			}
			type.append(tokenTypes[i].getName());
		}
		return type.toString();
	}

	public String getType() {
		return type;
	}

	public String getStyle() {
		return style;
	}

	public static Collection<ShowTokenType> getAll() {
		return tokenTypes;
	}

	public void write(CMBuilder builder, Writer writer) throws IOException {
		if (getStyle() == null) {
			builder.write(writer, "\"", false);
			builder.write(writer, getType(), false);
			builder.write(writer, "\"", false);
		} else {
			builder.write(writer, "{type:\"", false);
			builder.write(writer, getType(), false);
			builder.write(writer, "\"", false);
			builder.write(writer, ",style:\"", false);
			builder.write(writer, getStyle(), false);
			builder.write(writer, "\"}", false);
		}
	}

}
