var argscheck = require('cordova/argscheck'),
	utils = require('cordova/utils'),
	exec = require('cordova/exec');

var UniqueID = function() {};

function UniqueID() {
	this.uniqueID = null;

	var me = this;

	channel.onCordovaReady.subscribe(function() {
        me.getInfoID(function(result) {
            //ignoring info.cordova returning from native, we should use value from cordova.version defined in cordova.js
            //TODO: CB-5105 native implementations should not return info.cordova
            me.UniqueID = result
            channel.onCordovaInfoReady.fire();
        },function(e) {
            me.available = false;
            utils.alert("[ERROR] Error initializing Cordova: " + e);
        });
    });
}

UniqueID.prototype.getInfoID = function(successCallback) {
    argscheck.checkArgs('fF', 'UniqueID.getInfoID', arguments);
    exec(successCallback, null, "UniqueIDPlugin", "getUniqueID", []);
};

UniqueID.prototype.getUniqueID = function() {
	return this.uniqueID;
};

module.exports = new UniqueID();