#import <Cordova/CDVPlugin.h>

@interface UniqueIDPlugin : CDVPlugin

- (void)getUniqueID:(CDVInvokedUrlCommand*)command;
+ (NSString*)UniqueAppId;

@end
