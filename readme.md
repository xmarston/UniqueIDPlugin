Unique ID Plugin
=============

This plugin allows the user obtain a unique id like UUID in early iOS versions. Only for Cordova iOS apps.

Installation
============

cordova plugin add https://github.com/xmarston/UniqueIDPlugin.git

Usage
=====
    UniqueIDPlugin.getUniqueID(function(result){ alert(result);//result will be the unique ID, 
    	you can use it for device authentication, etc... });