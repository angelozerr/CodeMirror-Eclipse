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
package codemirror.eclipse.swt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import codemirror.eclipse.swt.internal.SingleSourcingHelper;
import codemirror.eclipse.swt.internal.org.apache.commons.lang3.StringEscapeUtils;
import codemirror.eclipse.swt.search.FindReplaceAction;
import codemirror.eclipse.swt.search.IFindReplaceTarget;
import codemirror.eclipse.swt.utils.StringUtils;

/**
 * CodeMirror control.
 * 
 */
public class CMControl extends AbstractCMControl implements IFindReplaceTarget {

	private static final String CM_VALIDATE = "cm_validate";

	private static final String CM_DIRTY = "cm_dirty";

	public static final String CM_OPEN_SEARCH = "cm_openSearch";

	private static final String CM_REFOCUS_JS = "editor.focus();";

	private boolean dirty = false;
	private List<IDirtyListener> listeners = new ArrayList<IDirtyListener>();
	private IValidator validator;
	private boolean focusToBeSet;
	private String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$;
	private boolean initialized;

	protected Action findReplaceAction;

	private Integer charStart;
	private Integer charEnd;

	public CMControl(File file, Composite parent, int style) {
		super(file, parent, style);
		this.focusToBeSet = false;
		this.initialized = false;
	}

	public CMControl(String url, Composite parent, int style) {
		super(url, parent, style);
		this.focusToBeSet = false;
		this.initialized = false;
	}

	public CMControl(ICMHtmlProvider builder, Composite parent, int style) {
		super(builder, parent, style);
		this.focusToBeSet = false;
		this.initialized = false;
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		if (focusToBeSet) {
			browser.evaluate(CM_REFOCUS_JS);
			focusToBeSet = false;
		}
		if (charStart != null && charEnd != null) {
			setSelection(charStart, charEnd);
			this.charStart = null;
			this.charEnd = null;
		}
	}

	protected void doSetText(String text) {
		String js = null;
		if (!initialized) {
			js = new StringBuilder(" try { CMEclipse.setValue(editor, \"")
					.append(StringEscapeUtils.escapeEcmaScript(text))
					.append("\" ); } catch(e){alert(e)}; return null;")
					.toString();
		} else {
			js = new StringBuilder(" try { editor.setValue( \"")
					.append(StringEscapeUtils.escapeEcmaScript(text))
					.append("\" ); } catch(e){alert(e)}; return null;")
					.toString();
		}
		browser.evaluate(js);
		initialized = true;
		dirty = !initialized;
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
		String lineSeparator = getLineSeparator();
		StringBuilder js = new StringBuilder("return editor.getValue(");
		if (lineSeparator != null) {
			js.append("\"");
			js.append(lineSeparator);
			js.append("\"");
		}
		js.append(");");
		return (String) browser.evaluate(js.toString());
	}

	@Override
	protected void createBrowserFunctions() {
		super.createBrowserFunctions();
		new BrowserFunction(browser, CM_DIRTY) {
			public Object function(Object[] arguments) {
				dirty = true;
				notifyDirtyListeners();
				return null;
			}
		};

		new BrowserFunction(browser, CM_VALIDATE) {
			public Object function(Object[] arguments) {
				final IValidator validator = getValidator();
				if (validator != null) {
					final String code = getText();
					if (!SingleSourcingHelper.isRAP() && validator.isAsync()) {
						final Display display = getShell().getDisplay();
						try {
							new Thread() {
								public void run() {
									String json = validator.validate(code);
									if (StringUtils.isNotEmpty(json)) {
										final String js = "CMEclipse.onValidationResult(editor, "
												+ json + ")";
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
							return json;
						}
					}
				}
				return null;
			}
		};

		new BrowserFunction(browser, CM_OPEN_SEARCH) {

			public Object function(Object[] arguments) {
				if (findReplaceAction == null) {
					Shell shell = getShell();
					findReplaceAction = new FindReplaceAction(shell,
							CMControl.this);
				}
				findReplaceAction.run();
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

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
		notifyDirtyListeners();
	}

	public void setValidator(IValidator validator) {
		this.validator = validator;
	}

	public IValidator getValidator() {
		return validator;
	}

	public String getLineSeparator() {
		return lineSeparator;
	}

	public void setLineSeparator(String lineSeparator) {
		if (lineSeparator != null) {
			lineSeparator = lineSeparator.replaceAll("\r", "\\\\r").replaceAll(
					"\n", "\\\\n");
		}
		this.lineSeparator = lineSeparator;
	}

	public void execCommand(String command) {
		StringBuilder script = new StringBuilder("editor.execCommand(\"");
		script.append(command);
		script.append("\");");
		browser.evaluate(script.toString());
	}

	public boolean findNext(String query, boolean forwardSearch,
			boolean withOverlay) {
		StringBuilder script = new StringBuilder(
				"return CMEclipse.search(editor,\"");
		script.append(query);
		script.append("\",");
		script.append(!forwardSearch);
		script.append(",");
		script.append(withOverlay);
		script.append(");");
		return (Boolean) browser.evaluate(script.toString());
	}

	public int replaceAll(String query, String text) {
		StringBuilder script = new StringBuilder(
				"return CMEclipse.replaceAll(editor,\"");
		script.append(query);
		script.append("\",\"");
		script.append(text);
		script.append("\");");
		return ((Double) browser.evaluate(script.toString())).intValue();
	}

	public boolean isEditable() {
		return true;
	}

	public Action getFindReplaceAction() {
		return findReplaceAction;
	}

	public void setFindReplaceAction(Action findReplaceAction) {
		this.findReplaceAction = findReplaceAction;
	}

	public boolean canPerformFind() {
		return true;
	}

	public String getSelectionText() {
		if (isLoaded()) {
			return (String) browser.evaluate("return editor.getSelection();");
		} else {
			return "";
		}
	}

	public void replaceSelection(String replaceString) {
		StringBuilder script = new StringBuilder("editor.replaceSelection(\"");
		script.append(replaceString);
		script.append("\");");
		browser.evaluate(script.toString());
	}

	public void setSelection(int charStart, int charEnd) {
		if (isLoaded()) {
			StringBuilder script = new StringBuilder("editor.setSelection(");
			script.append("CMEclipse.posFromIndex(editor,");
			script.append(charStart);
			script.append("),CMEclipse.posFromIndex(editor,");
			script.append(charEnd);
			script.append("));");
			browser.evaluate(script.toString());
		} else {
			this.charStart = charStart;
			this.charEnd = charEnd;
		}
	}
}
