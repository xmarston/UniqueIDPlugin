(function(window) {
  var UniqueID = function() {

  }

  UniqueID.prototype = {

    getUniqueID: function(callback, errCallbac) {
      cordova.exec(callback, errCallbac, 'UniqueID', 'getUniqueID', []);
    }
  };

  cordova.addConstructor(function() {
    window.UniqueID = new UniqueID();
  });

})(window);