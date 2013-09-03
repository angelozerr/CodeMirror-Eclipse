// Define search commands. Depends on dialog.js or another
// implementation of the openDialog method.

// Replace works a little oddly -- it will do the replace on the next
// Ctrl-G (or whatever is bound to findNext) press. You prevent a
// replace by making sure the match is no longer selected when hitting
// Ctrl-G.

(function() {
  function searchOverlay(query) {
    if (typeof query == "string") return {token: function(stream) {
      if (stream.match(query)) return "searching";
      stream.next();
      stream.skipTo(query.charAt(0)) || stream.skipToEnd();
    }};
    return {token: function(stream) {
      if (stream.match(query)) return "searching";
      while (!stream.eol()) {
        stream.next();
        if (stream.match(query, false)) break;
      }
    }};
  }

  function SearchState() {
    this.posFrom = this.posTo = this.query = null;
    this.overlay = null;
    this.found = false;
  }
  function getSearchState(cm) {
    if (!cm.state.search) {
  		cm.on("focus", function(cm) {if (!cm.state.search.selecting)clearSearch(cm);});
    }
    return cm.state.search || (cm.state.search = new SearchState());
  }
  function getSearchCursor(cm, query, pos) {
    // Heuristic: if the query string is all lowercase, do a case insensitive search.
    return cm.getSearchCursor(query, pos, typeof query == "string" && query == query.toLowerCase());
  }
  function parseQuery(query) {
    var isRE = query.match(/^\/(.*)\/([a-z]*)$/);
    return isRE ? new RegExp(isRE[1], isRE[2].indexOf("i") == -1 ? "" : "i") : query;
  }
  function doSearch(cm, rev) {
    var state = getSearchState(cm);
    if (state.query) return findNext(cm, rev);    
    /*dialog(cm, queryDialog, "Search for:", function(query) {
      cm.operation(function() {
        if (!query || state.query) return;
        state.query = parseQuery(query);
        cm.removeOverlay(state.overlay);
        state.overlay = searchOverlay(state.query);
        cm.addOverlay(state.overlay);
        state.posFrom = state.posTo = cm.getCursor();
        findNext(cm, rev);
      });
    });*/
  }
  
  function findNext(cm, rev) {cm.operation(function() {
    var state = getSearchState(cm);
    state.found = false;
    var cursor = getSearchCursor(cm, state.query, rev ? state.posFrom : state.posTo);
    if (!cursor.find(rev)) {
      cursor = getSearchCursor(cm, state.query, rev ? CodeMirror.Pos(cm.lastLine()) : CodeMirror.Pos(cm.firstLine(), 0));
      if (!cursor.find(rev)) return;
    }
    state.selecting = true;
    cm.setSelection(cursor.from(), cursor.to());
    state.found = true;
    state.selecting = false;
    state.posFrom = cursor.from(); state.posTo = cursor.to();
  });}
  function clearSearch(cm) {cm.operation(function() {
    var state = getSearchState(cm);
    if (!state.query) return;
    state.query = null;
    cm.removeOverlay(state.overlay);
  });}

  var replaceQueryDialog =
    'Replace: <input type="text" style="width: 10em"/> <span style="color: #888">(Use /re/ syntax for regexp search)</span>';
  var replacementQueryDialog = 'With: <input type="text" style="width: 10em"/>';
  var doReplaceConfirm = "Replace? <button>Yes</button> <button>No</button> <button>Stop</button>";
  function replace(cm, all) {
    dialog(cm, replaceQueryDialog, "Replace:", function(query) {
      if (!query) return;
      query = parseQuery(query);
      dialog(cm, replacementQueryDialog, "Replace with:", function(text) {
        if (all) {
          cm.operation(function() {
            for (var cursor = getSearchCursor(cm, query); cursor.findNext();) {
              if (typeof query != "string") {
                var match = cm.getRange(cursor.from(), cursor.to()).match(query);
                cursor.replace(text.replace(/\$(\d)/, function(_, i) {return match[i];}));
              } else cursor.replace(text);
            }
          });
        } else {
          clearSearch(cm);
          var cursor = getSearchCursor(cm, query, cm.getCursor());
          var advance = function() {
            var start = cursor.from(), match;
            if (!(match = cursor.findNext())) {
              cursor = getSearchCursor(cm, query);
              if (!(match = cursor.findNext()) ||
                  (start && cursor.from().line == start.line && cursor.from().ch == start.ch)) return;
            }
            cm.setSelection(cursor.from(), cursor.to());
            confirmDialog(cm, doReplaceConfirm, "Replace?",
                          [function() {doReplace(match);}, advance]);
          };
          var doReplace = function(match) {
            cursor.replace(typeof query == "string" ? text :
                           text.replace(/\$(\d)/, function(_, i) {return match[i];}));
            advance();
          };
          advance();
        }
      });
    });
  }

  function search(cm, query, rev, withOverlay) {
    var state = getSearchState(cm);
    //cm.operation(function() {    
        //if (!query || state.query) return;
        if (query === state.query) {
            if (!withOverlay) {
            	cm.removeOverlay(state.overlay);
            }
        	findNext(cm, rev);
        	return state.found;
        }
        clearSearch(cm);
        state.query = parseQuery(query);        
        cm.removeOverlay(state.overlay);
        if (withOverlay) {
        	state.overlay = searchOverlay(state.query);
        	cm.addOverlay(state.overlay);
        }
        state.posFrom = state.posTo = cm.getCursor();
        findNext(cm, rev);
        return state.found;
      //});
  }
  CMEclipse.search = search;
  
  function replaceAll2(cm, query, text) {
    //cm.operation(function() {
    var nbReplacement = 0;
            for (var cursor = getSearchCursor(cm, query); cursor.findNext();) {
              if (typeof query != "string") {
                var match = cm.getRange(cursor.from(), cursor.to()).match(query);
                cursor.replace(text.replace(/\$(\d)/, function(_, i) {return match[i];}));
                nbReplacement++;
              } else {
              	cursor.replace(text);
              	nbReplacement++;
              }
            }
            return nbReplacement;
          //});
  }
  CMEclipse.replaceAll = replaceAll2;
  
  function replace2(cm, query, text) {
  if (query === state.query) {
	  clearSearch(cm);
	  var cursor = getSearchCursor(cm, query, cm.getCursor());
	  var advance = function() {
	    var start = cursor.from(), match;
	    if (!(match = cursor.findNext())) {
	      cursor = getSearchCursor(cm, query);
	      if (!(match = cursor.findNext()) ||
	          (start && cursor.from().line == start.line && cursor.from().ch == start.ch)) return;
	    }
	    cm.setSelection(cursor.from(), cursor.to());
	    //confirmDialog(cm, doReplaceConfirm, "Replace?",
	    //              [function() {doReplace(match);}, advance]);
	  };
	  var doReplace = function(match) {
	    cursor.replace(typeof query == "string" ? text :
	                   text.replace(/\$(\d)/, function(_, i) {return match[i];}));
	    advance();
	  };
	  advance();
  }}
  CMEclipse.replace = replace2;
  
  //CodeMirror.commands.find = function(cm) {clearSearch(cm); doSearch(cm);};
  CodeMirror.commands.findNext = doSearch;
  CodeMirror.commands.findPrev = function(cm) {doSearch(cm, true);};
  CodeMirror.commands.clearSearch = clearSearch;
  CodeMirror.commands.replace = replace;
  CodeMirror.commands.replaceAll = function(cm) {replace(cm, true);};
})();
