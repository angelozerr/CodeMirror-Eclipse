package codemirror.eclipse.swt.samples;

import codemirror.eclipse.swt.builder.CMBuilder;
import codemirror.eclipse.swt.resources.CMResourcesManager;
import codemirror.eclipse.swt.velocity.builder.CMVelocityBuilder;

public class VelocitySample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new VelocitySample().createUI();
	}

	@Override
	protected String getURL() {
		return null;
	}

	@Override
	protected CMBuilder getBuilder() {
		CMBuilder builder = new CMVelocityBuilder(CMResourcesManager
				.getInstance().getURL(""), false);
		return builder;
	}


	
	@Override
	protected String getInitialText() {
		return "<HTML>\n<BODY>\nHello $customer.Name!\n<table>\n"
				+ "#foreach( $mud in $mudsOnSpecial )\n\t#"
				+ "if ( $customer.hasPurchased($mud) )\n\t\t<tr>\n\t\t\t<td>\n\t\t\t\t"
				+ "$flogger.getPromo( $mud )\n\t\t\t</td>\n\t\t</tr>\n\t#end\n#end\n</table>";
	}
}
