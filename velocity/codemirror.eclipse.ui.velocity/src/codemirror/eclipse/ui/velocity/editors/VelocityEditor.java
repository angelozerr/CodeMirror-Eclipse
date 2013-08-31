package codemirror.eclipse.ui.velocity.editors;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.IValidator;
import codemirror.eclipse.ui.editors.CMEditorPart;
import codemirror.eclipse.ui.resources.CMResourcesRegistry;
import codemirror.eclipse.ui.velocity.internal.lint.VelocityValidator;

public class VelocityEditor extends CMEditorPart {

	public VelocityEditor() {		
		super(CMResourcesRegistry.getRegistry().getURL(
				CMResourcesConstants.VELOCITY_HTML));
	}

	public IValidator getValidator() {
		return VelocityValidator.getInstance();
	}

}
