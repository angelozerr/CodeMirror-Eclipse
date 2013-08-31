package codemirror.eclipse.swt.samples;

import codemirror.eclipse.resources.CMResourcesConstants;
import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.resources.CMResourcesManager;

public class VelocitySampleFromHTML extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new VelocitySampleFromHTML().createUI();
	}

	@Override
	protected String getURL() {
		return CMResourcesManager.getInstance().getURL(
				CMResourcesConstants.VELOCITY_HTML);
	}

	@Override
	protected CMBuilder getBuilder() {

		return null;
	}

	
	@Override
	protected String getInitialText() {
		return "<HTML>\n<BODY>\nHello $customer.Name!\n<table>\n"
				+ "#foreach( $mud in $mudsOnSpecial )\n\t#"
				+ "if ( $customer.hasPurchased($mud) )\n\t\t<tr>\n\t\t\t<td>\n\t\t\t\t"
				+ "$flogger.getPromo( $mud )\n\t\t\t</td>\n\t\t</tr>\n\t#end\n#end\n</table>";
	}
}
