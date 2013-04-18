package codemirror.eclipse.swt.samples;

import java.io.File;

import codemirror.eclipse.resources.CMResources;

public class XQuerySample extends AbstractCMSample {

	public static void main(String[] args) {
		new XQuerySample().createUI();
	}

	@Override
	protected File getCMFile() {
		return CMResources.getXQueryResource();
	}
	
	@Override
	protected String getInitialText() {
		return "let $a := 1\nreturn $a";
	}
}
