/*******************************************************************************
 * Copyright (c) 2011 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package codemirror.eclipse.swt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import codemirror.eclipse.swt.internal.org.apache.commons.lang3.StringEscapeUtils;
import codemirror.eclipse.swt.internal.org.apache.commons.lang3.StringUtils;

/**
 * CodeMirror control.
 * 
 */
public class CMControl extends AbstractCMControl {

	private static final String CM_REFOCUS_JS = "editor.focus();";
	private boolean dirty = false;
	private List<IDirtyListener> listeners = new ArrayList<IDirtyListener>();

	private IValidator validator;
	private boolean focusToBeSet;

	public CMControl(File file, Composite parent, int style) {
		super(file, parent, style, SWT.NONE);
		this.focusToBeSet = false;
	}

	public CMControl(String url, Composite parent, int style) {
		super(url, parent, style, SWT.NONE);
		this.focusToBeSet = false;
	}

	public CMControl(File file, Composite parent, int style, int browserStyle) {
		super(file, parent, style, browserStyle);
		this.focusToBeSet = false;
	}

	public CMControl(String url, Composite parent, int style, int browserStyle) {
		super(url, parent, style, browserStyle);
		this.focusToBeSet = false;
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		if (focusToBeSet) {
			browser.evaluate(CM_REFOCUS_JS);
			focusToBeSet = false;
		}
	}

	protected void doSetText(String text) {
		String js = new StringBuilder(
				" cmIsDirtyFired=true; try { editor.setValue( \"")
				.append(StringEscapeUtils.escapeEcmaScript(text))
				.append("\" ); } catch(e){alert(e)}; cmIsDirtyFired=false;return null;")
				.toString();
		browser.evaluate(js);
		dirty = false;
	}

	@Override
	public boolean setFocus() {
		boolean result = super.setFocus();
		if (result) {
			if (isLoaded()) {
				browser.evaluate(CM_REFOCUS_JS);
			} else {
				focusToBeSet = true;
			}
		}
		return result;
	}

	public boolean isDirty() {
		return dirty;
	}

	@Override
	protected String doGetText() {
		return (String) browser.evaluate("return editor.getValue();");
	}

	@Override
	protected void createBrowserFunctions() {
		super.createBrowserFunctions();
		new BrowserFunction(browser, "cm_dirty") {
			public Object function(Object[] arguments) {

				dirty = true;
				notifyDirtyListeners();
				return null;
			}
		};

		new BrowserFunction(browser, "cm_validate") {
			public Object function(Object[] arguments) {
				final IValidator validator = getValidator();
				if (validator != null) {
					final String code = getText();
					if (validator.isAsync()) {
						final Display display = getShell().getDisplay();
						try {
							new Thread() {
								public void run() {
									String json = validator.validate(code);
									if (StringUtils.isNotEmpty(json)) {
										final String js = "f(" + json + ")";
										display.asyncExec(new Runnable() {
											public void run() {
												browser.evaluate(js);
											}
										});
									}

								};
							}.start();
						} catch (Throwable e) {
							e.printStackTrace();
						}
					} else {
						String json = validator.validate(code);
						if (StringUtils.isNotEmpty(json)) {
							String js = "f(" + json + ")";
							browser.evaluate(js);
						}
					}
				}
				return null;
			}
		};

	}

	public void addDirtyListener(IDirtyListener l) {
		listeners.add(l);
	}

	private void notifyDirtyListeners() {
		Display.getCurrent().asyncExec(new Runnable() {
			public void run() {
				for (IDirtyListener l : listeners) {
					l.dirtyChanged(isDirty());
				}
			}
		});

	}

	public void setDirty(boolean b) {
		dirty = b;
		browser.evaluate(" cmIsDirtyFired=false");
		notifyDirtyListeners();
	}

	public void setValidator(IValidator validator) {
		this.validator = validator;
	}

	public IValidator getValidator() {
		return validator;
	}
}
