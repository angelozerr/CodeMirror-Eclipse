package codemirror.eclipse.swt.builder;

import java.util.HashMap;
import java.util.Map;

public class CMBuilderRegistry {

	private static final CMBuilderRegistry INSTANCE = new CMBuilderRegistry();

	private final Map<Mode, CMBuilder> builders;

	public static CMBuilderRegistry getInstance() {
		return INSTANCE;
	}

	public CMBuilderRegistry() {
		this.builders = new HashMap<Mode, CMBuilder>();
	}

	public void register(CMBuilder builder) {
		builders.put(builder.getMode(), builder);
	}

	public void unregister(CMBuilder builder) {
		unregister(builder.getMode());
	}

	public void unregister(Mode mode) {
		builders.remove(mode);
	}

	public CMBuilder getBuilder(Mode mode) {
		return builders.get(mode);
	}

}
