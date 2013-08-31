package codemirror.eclipse.swt.builder;

import java.util.ArrayList;
import java.util.Collection;

public class TokenType {

	private static final Collection<TokenType> tokenTypes = new ArrayList<TokenType>();

	public static final TokenType VARIABLE = new TokenType("variable");
	public static final TokenType PROPERTY = new TokenType("property");
	public static final TokenType DEF = new TokenType("def");

	private final String name;

	public TokenType(String name) {
		this.name = name;
		tokenTypes.add(this);
	}

	public String getName() {
		return name;
	}

	public static Collection<TokenType> getTokenTypes() {
		return tokenTypes;
	}

}
