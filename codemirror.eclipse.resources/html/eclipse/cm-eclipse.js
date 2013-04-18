var cmIsDirtyFired = false;
var CMEclipse = (function() {
  return {
    loaded : function() {
      editor.on('change', function() {
        if (!cmIsDirtyFired) {
          cmIsDirtyFired = true;
          cm_dirty();
        }
      });
      setFullScreen(editor, true);
    }
  };
})();