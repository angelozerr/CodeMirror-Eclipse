var CMEclipse = (function() {
		
	// CodeMirror commands

	CodeMirror.commands.autocomplete = function(cm) {
		
	}

	CodeMirror.commands.passAndHint = function(cm) {
		setTimeout(function() {cm.execCommand("autocomplete");}, 100);
      	return CodeMirror.Pass;
	}
		
	CodeMirror.commands.format = function(cm) {
		
	}
	
	var dirty = false;

	function addAnnotation(error, found) {
		var startLine = error.startLine;
		var startChar = error.startChar;
		var endLine = error.endLine;
		var endChar = error.endChar;
		var message = error.message;
		found.push({
			from : CodeMirror.Pos(startLine, startChar),
			to : CodeMirror.Pos(endLine, endChar),
			message : message
		});
	}

	function refresh(cm, data) {
		if (data) {
			var found = [];
			if (data.annotations) {
				data = data.annotations;
			}
			if (data.length && data.length > 0) {
				for ( var i = 0; i < data.length; i++) {
					var error = data[i];
					addAnnotation(error, found);
				}
			} else {
				addAnnotation(data, found);
			}
		}
		cm._updateLinting(cm, found);
	}

	return {
		loaded : function() {
			editor.on('change', function() {
				dirty = true;
				if (typeof cm_dirty == 'function') {					
					cm_dirty();
				}				
			});
			setFullScreen(editor, true);
			editor.clearHistory();
		},
		setDirty : function(d) {
			dirty = d;
		},
		isDirty : function() {
			return dirty;		
		},
		validate : function(cm, updateLinting, options) {
			if (typeof cm_validate == 'function') {
				var code = cm.getValue();
				if (code != '') {
					cm._updateLinting = updateLinting;
					var data = cm_validate(code);
					if (data != null) {
						var json = eval(data);
						refresh(cm, json);
					}
				}
			}
		},
		onValidationResult : function(cm, data) {
			refresh(cm, data);
		}
	};
})();