/*******************************************************************************
 * Copyright (c) 2013 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package codemirror.eclipse.swt.builder;

import java.io.IOException;
import java.io.Writer;

/**
 * Buider to create HTML content with CodeMirror with CM run mode.
 * 
 */
public class CMRunModeBuilder extends AbstractCMBuilder {

	public CMRunModeBuilder(Mode mode, String baseURL) {
		super(mode, baseURL);
		super.installRunMode();
	}

	@Override
	protected void writeBodyCM(Writer writer) throws IOException {
		write(writer, "<pre id=\"code\" name=\"code\" class=\"", false);

		// class
		Theme theme = super.getTheme();
		write(writer, "cm-s-", false);
		if (theme != null) {
			write(writer, theme.getName(), false);
		} else {
			write(writer, "default", false);
		}
		write(writer, "\"></pre>", false);
	}

}
