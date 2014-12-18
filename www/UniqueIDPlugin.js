var argscheck = require('cordova/argscheck'),
  utils = require('cordova/utils'),
  exec = require('cordova/exec');

var UniqueID = function() {
};

UniqueID.getUniqueID = function(callback) {
  exec(callback, null, "UniqueIDPlugin", "getUniqueID", []);
};

module.exports = UniqueID;