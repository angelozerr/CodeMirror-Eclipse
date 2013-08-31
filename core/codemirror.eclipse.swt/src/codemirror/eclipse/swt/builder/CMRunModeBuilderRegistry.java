package codemirror.eclipse.swt.builder;

import java.util.HashMap;
import java.util.Map;

public class CMRunModeBuilderRegistry {

	private static final CMRunModeBuilderRegistry INSTANCE = new CMRunModeBuilderRegistry();

	private final Map<Mode, CMRunModeBuilder> builders;

	public static CMRunModeBuilderRegistry getInstance() {
		return INSTANCE;
	}

	public CMRunModeBuilderRegistry() {
		this.builders = new HashMap<Mode, CMRunModeBuilder>();
	}

	public void register(CMRunModeBuilder builder) {
		builders.put(builder.getMode(), builder);
	}

	public void unregister(CMRunModeBuilder builder) {
		unregister(builder.getMode());
	}

	public void unregister(Mode mode) {
		builders.remove(mode);
	}

	public CMRunModeBuilder getBuilder(Mode mode) {
		return builders.get(mode);
	}

}
