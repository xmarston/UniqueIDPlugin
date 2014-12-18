#import "UniqueIDPlugin.h"

#import "SSKeychain.h"
#import <AdSupport/ASIdentifierManager.h>

@implementation UniqueIDPlugin

-(void)getUniqueID:(CDVInvokedUrlCommand*)command {
    NSString* str = [[self class]UniqueAppId];
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:str];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

+(NSString*)UniqueAppId
{
    NSString *Appname = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleName"];
    NSString *strApplicationUUID = [SSKeychain passwordForService:Appname account:@"manab"];
    if (strApplicationUUID == nil)
    {
        strApplicationUUID  = [[[ASIdentifierManager sharedManager] advertisingIdentifier] UUIDString];
        [SSKeychain setPassword:strApplicationUUID forService:Appname account:@"manab"];
    }
    return strApplicationUUID;
}

@end