package codemirror.eclipse.swt.builder;

import java.io.IOException;
import java.io.Writer;

public abstract class Option {

	public abstract void write(Options options, Writer writer)
			throws IOException;

}
