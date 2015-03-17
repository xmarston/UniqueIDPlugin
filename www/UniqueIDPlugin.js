var exec = require('cordova/exec'),
    cordova = require('cordova'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils');

function UniqueID() {
	this.uniqueID = null;
}

UniqueID.prototype.getInfoID = function(successCallback) {
    exec(successCallback, null, "UniqueIDPlugin", "getUniqueID", []);
};

UniqueID.prototype.getUniqueID = function() {
	return this.uniqueID;
};

var me = new UniqueID();

channel.createSticky('onCordovaConnectionReady');
channel.waitForInitialization('onCordovaConnectionReady');

channel.onCordovaReady.subscribe(function() {
    me.getInfoID(function(result) {
        me.uniqueID = result
        channel.onCordovaInfoReady.fire();
    },function(e) {
        me.available = false;
        utils.alert("[ERROR] Error initializing Cordova: " + e);
    });
});

module.exports = me;