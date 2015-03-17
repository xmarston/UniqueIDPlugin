var exec = require('cordova/exec'),
    cordova = require('cordova'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils');

channel.createSticky('onCordovaConnectionReady');
channel.waitForInitialization('onCordovaConnectionReady');

var UniqueID = function() {};

function UniqueID() {
	this.uniqueID = null;

	var me = this;

	channel.onCordovaReady.subscribe(function() {
        me.getInfoID(function(result) {
            //ignoring info.cordova returning from native, we should use value from cordova.version defined in cordova.js
            //TODO: CB-5105 native implementations should not return info.cordova
            me.uniqueID = result
            channel.onCordovaInfoReady.fire();
        },function(e) {
            me.available = false;
            utils.alert("[ERROR] Error initializing Cordova: " + e);
        });
    });
}

UniqueID.prototype.getInfoID = function(successCallback) {
    exec(successCallback, null, "UniqueIDPlugin", "getUniqueID", []);
};

UniqueID.prototype.getUniqueID = function() {
	return this.uniqueID;
};

module.exports = new UniqueID();