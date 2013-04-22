package codemirror.eclipse.swt.samples;

import java.io.File;

import codemirror.eclipse.resources.CMResources;

public class VelocitySample extends AbstractCMSample {

	public static void main(String[] args) throws Exception {
		new VelocitySample().createUI();
	}

	@Override
	protected File getCMFile() {
		return CMResources.getVelocityResource();
	}

	@Override
	protected String getInitialText() {
		return "<HTML>\n<BODY>\nHello $customer.Name!\n<table>\n"
				+ "#foreach( $mud in $mudsOnSpecial )\n\t#"
				+ "if ( $customer.hasPurchased($mud) )\n\t\t<tr>\n\t\t\t<td>\n\t\t\t\t"
				+ "$flogger.getPromo( $mud )\n\t\t\t</td>\n\t\t</tr>\n\t#end\n#end\n</table>";
	}
}
