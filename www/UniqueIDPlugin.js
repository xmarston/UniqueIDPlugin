(function(window) {
  var UniqueID = function() {

  }

  UniqueID.prototype = {

    getIdentifier: function(callback, errCallbac) {
      cordova.exec(callback, errCallbac, 'UniqueID', 'getUniqueID', []);
    }
  };

  cordova.addConstructor(function() {
    window.UniqueID = new UniqueID();
  });

})(window);