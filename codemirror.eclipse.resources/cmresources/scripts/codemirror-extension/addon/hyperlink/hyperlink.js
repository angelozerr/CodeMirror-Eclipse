(function() {
  "use strict";

  function showTooltip(e, content) {
    var tt = document.createElement("div");
    tt.className = "CodeMirror-lint-tooltip";
    if (typeof content == "string") {
    	content = document.createTextNode(content);
    }     
    tt.appendChild(content);
    document.body.appendChild(tt);

    function position(e) {
      if (!tt.parentNode) return CodeMirror.off(document, "mousemove", position);
      tt.style.top = Math.max(0, e.clientY - tt.offsetHeight - 5) + "px";
      tt.style.left = (e.clientX + 5) + "px";
    }
    CodeMirror.on(document, "mousemove", position);
    position(e);
    if (tt.style.opacity != null) tt.style.opacity = 1;
    return tt;
  }
  function rm(elt) {
    if (elt.parentNode) elt.parentNode.removeChild(elt);
  }
  function hideTooltip(tt) {
    if (!tt.parentNode) return;
    if (tt.style.opacity == null) rm(tt);
    tt.style.opacity = 0;
    setTimeout(function() { rm(tt); }, 600);
  }

  function showTooltipFor(e, content, node) {
    var tooltip = showTooltip(e, content);
    function hide() {
      CodeMirror.off(node, "mouseout", hide);
      CodeMirror.off(node, "click", hide);
      if (tooltip) { hideTooltip(tooltip); tooltip = null; }
    }
    var poll = setInterval(function() {
      if (tooltip) for (var n = node;; n = n.parentNode) {
        if (n == document.body) return;
        if (!n) { hide(); break; }
      }
      if (!tooltip) return clearInterval(poll);
    }, 400);
    CodeMirror.on(node, "mouseout", hide);
    CodeMirror.on(node, "click", hide);
  }

  function HyperlinkState(cm, options) {
    this.options = options;
    this.timeout = null;
    this.onMouseOver = function(e) { onMouseOver(cm, e); };
  }

  function parseOptions(cm, options) {
    if (options instanceof Function) return {getHyperlink: options};
    if (!options || options === true) options = {};
    if (!options.getHyperlink) options.getHyperlink = cm.getHelper(CodeMirror.Pos(0, 0), "hyperlink");
    if (!options.getHyperlink) throw new Error("Required option 'getHyperlink' missing (hy�rlink addon)");
    return options;
  }
  
  function onMouseOver(cm, e) {
    var node = e.target || e.srcElement;
    if (node) {
      var state = cm.state.hyperlink;      
      var content = state.options.getHyperlink(cm, node, e);
      if (content) {
        showTooltipFor(e, content, node);
      }
    }
  }
  
  function show() {
  
  }

  function popupSpanTooltip(ann, e) {
    var target = e.target || e.srcElement;
    alert(ann);
   // showTooltipFor(e, annotationTooltip(ann), target);
  }
  
  function optionHandler(cm, val, old) {
    if (old && old != CodeMirror.Init) {
      CodeMirror.off(cm.getWrapperElement(), "mouseover", cm.state.hyperlink.onMouseOver);
      delete cm.state.hyperlink;
    }

    if (val) {
      var state = cm.state.hyperlink = new HyperlinkState(cm, parseOptions(cm, val));
      CodeMirror.on(cm.getWrapperElement(), "mouseover", state.onMouseOver);
    }
  }
  
  CodeMirror.defineOption("hyperlink", false, optionHandler); // deprecated
  
})();