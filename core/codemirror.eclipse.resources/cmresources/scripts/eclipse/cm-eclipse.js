var CMEclipse = (function() {

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
    setValue : function(cm, text) {
	    cm.setValue(text);
	    	    cm.clearHistory();
	      cm.on('change', function() {
	        if (typeof cm_dirty == 'function') {
	          cm_dirty();
	        }
	      });
	    
    },
    posFromIndex: function(cm, off) {
        var doc=cm.doc, ch, lineNo = doc.first;
        doc.iter(function(line) {
          var sz = line.text.length + 2;
          if (sz > off) { ch = off; return true; }
          off -= sz;
          ++lineNo;
        });
        return cm.clipPos(CodeMirror.Pos(lineNo, ch));
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