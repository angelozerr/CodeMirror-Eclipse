package codemirror.eclipse.swt.builder;

import java.io.IOException;
import java.io.Writer;

public interface Writable {

	void write(CMBuilder builder, Writer writer) throws IOException;
}
