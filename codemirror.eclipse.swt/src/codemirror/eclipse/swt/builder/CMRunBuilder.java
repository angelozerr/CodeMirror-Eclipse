package codemirror.eclipse.swt.builder;

import java.io.IOException;
import java.io.Writer;

public class CMRunBuilder extends AbstractCMBuilder {

	public CMRunBuilder(Mode mode, String baseURL) {
		super(mode, baseURL);
	}

	@Override
	protected void writeScript(Writer writer) throws IOException {
		write(writer, "<pre id=\"code\" name=\"code\" ></pre>");
	}

}
