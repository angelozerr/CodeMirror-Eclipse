var cmIsDirtyFired = false;
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

  return {
    loaded : function() {
      editor.on('change', function() {
        if (!cmIsDirtyFired) {
          cmIsDirtyFired = true;
          if (typeof cm_dirty == 'function') {
            cm_dirty();
          }
        }
      });
      setFullScreen(editor, true);
    },
    validate : function(cm, updateLinting, options) {
      if (typeof cm_validate == 'function') {
        var code = cm.getValue();
        if (code != '') {
          cm._updateLinting = updateLinting;
          cm_validate(code);
        }
      }
    },
    onValidationResult : function(cm, data) {
      if (data) {
        var found = [];
        if (data.annotations) {
          data = data.annotations;
        }
        if (data.length || data.length == 0) {
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
  };
})();