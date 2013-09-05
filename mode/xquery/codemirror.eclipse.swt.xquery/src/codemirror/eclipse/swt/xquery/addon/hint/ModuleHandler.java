package codemirror.eclipse.swt.xquery.addon.hint;

import java.util.List;

public interface ModuleHandler {

	public class Parameter {
		private String name;
		private String type;

		public Parameter(String name, String type) {
			this.name = name;
			this.type = type;
		}

		public Parameter() {
			this(null, null);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}

	void startModule(String prefix, String namespaceURI, String location)
			throws Exception;

	void startModule(String prefix, Boolean prefixRequired,
			String namespaceURI, String location) throws Exception;

	void endModule() throws Exception;

	void addFunction(String funcName, List<Parameter> parameters,
			String returnType, String doc) throws Exception;
}
