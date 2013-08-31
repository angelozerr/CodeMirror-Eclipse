package codemirror.eclipse.swt.xquery.addon.hint;

import java.io.Writer;
import java.util.List;

import codemirror.eclipse.swt.utils.JsonUtils;

public class CodeMirrorModuleHandler implements ModuleHandler {

	private final Writer writer;
	private final String resource;
	private int nbFunctions;

	public CodeMirrorModuleHandler(Writer writer, String resource) {
		this.writer = writer;
		this.resource = resource;
	}

	public void startModule(String prefix, String namespaceURI, String location)
			throws Exception {
		startModule(prefix, null, namespaceURI, location);
	}

	public void startModule(String prefix, Boolean prefixRequired,
			String namespaceURI, String location) throws Exception {
		this.nbFunctions = 0;
		writer.write("CodeMirror.XQuery.defineModule(");
		JsonUtils.beginJsonObject(writer);
		boolean hasPrefix = prefix != null && prefix.length() > 0;
		if (hasPrefix) {
			JsonUtils.addJsonField("prefix", prefix, true, true, writer);
			if (prefixRequired != null && !prefixRequired) {
				JsonUtils.addJsonField("prefixRequired", false, false, true,
						writer);
			}
		}
		JsonUtils.addJsonField("namespace", namespaceURI, !hasPrefix, true,
				writer);
		if (!(location == null || location.length() < 1)) {
			JsonUtils.addJsonField("location", location, false, true, writer);
		}
		if (resource != null) {
			JsonUtils.addJsonField("resource", resource, false, true, writer);
		}
		JsonUtils.addString(",\"functions\":", false, writer);
		JsonUtils.beginJsonArray(writer);
	}

	public void endModule() throws Exception {
		JsonUtils.endJsonArray(writer);
		JsonUtils.endJsonObject(writer);
		writer.write(");\n");
	}

	public void addFunction(String funcName, List<Parameter> parameters,
			String returnType, String doc) throws Exception {
		if (nbFunctions > 0) {
			JsonUtils.addFieldsSeparator(writer);
		}
		JsonUtils.beginJsonObject(writer);
		JsonUtils.addJsonField("name", funcName.trim(), true, true, writer);
		String as = returnType;
		if (as != null && as.length() > 0) {
			JsonUtils.addJsonField("as", as, false, true, writer);
		}
		if (parameters != null) {
			int nbParams = 0;
			JsonUtils.addString(",\"params\":", false, writer);
			JsonUtils.beginJsonArray(writer);
			for (Parameter parameter : parameters) {
				if (nbParams > 0) {
					JsonUtils.addFieldsSeparator(writer);
				}
				JsonUtils.beginJsonObject(writer);
				JsonUtils.addJsonField("name", parameter.getName(), true, true,
						writer);
				String type = parameter.getType();
				if (type != null) {
					JsonUtils.addJsonField("as", type, false, true, writer);
				}
				JsonUtils.endJsonObject(writer);
				nbParams++;
			}
			JsonUtils.endJsonArray(writer);
		}
		if (doc != null && doc.length() > 0) {
			JsonUtils.addJsonField("doc", doc, false, true, writer);
		}
		JsonUtils.endJsonObject(writer);
		this.nbFunctions++;

	}
}
