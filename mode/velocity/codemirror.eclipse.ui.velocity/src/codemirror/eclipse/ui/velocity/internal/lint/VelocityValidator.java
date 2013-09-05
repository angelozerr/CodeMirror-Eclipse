package codemirror.eclipse.ui.velocity.internal.lint;

import java.io.StringWriter;

import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.swt.addon.lint.StreamLintHandler;

public class VelocityValidator implements IValidator {

	private static final IValidator INSTANCE = new VelocityValidator();

	public static IValidator getInstance() {
		return INSTANCE;
	}

	public String validate(String code) {
		StringWriter writer = new StringWriter();
		VelocityLintProcessor.getInstance().lint(code,
				new StreamLintHandler(writer), null);
		return writer.toString();
	}

	public boolean isAsync() {
		return true;
	}

}
