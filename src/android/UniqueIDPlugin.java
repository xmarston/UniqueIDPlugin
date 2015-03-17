package es.dev_coders.uniqueidplugin;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.os.Build;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.provider.Settings;

public class UniqueIDPlugin extends CordovaPlugin {

    private CallbackContext uniqueIDCallbackContext;
	/**
     * Constructor.
    */
    public UniqueIDPlugin() {
    }

    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

     /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        this.uniqueIDCallbackContext = callbackContext;
        
         if (action.equals("getUniqueID")) {
            String id = "";
            //id = this.getId();
            id = this.getPseudoUniqueID();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, id);
            pluginResult.setKeepCallback(true);
            this.uniqueIDCallbackContext.sendPluginResult(pluginResult);
            return true;
        }
        return false;
    }

	public String getId() {
    	Context context = cordova.getActivity().getApplicationContext();
    	TelephonyManager  manager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    	String uuid = manager.getDeviceId();
    	
    	return uuid;
    }
	
	public String getPseudoUniqueID () {
		String m_szDevIDShort = "35" + //we make this look like a valid IMEI
	        	Build.BOARD.length()%10+ Build.BRAND.length()%10 + 
	        	Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 + 
	        	Build.DISPLAY.length()%10 + Build.HOST.length()%10 + 
	        	Build.ID.length()%10 + Build.MANUFACTURER.length()%10 + 
	        	Build.MODEL.length()%10 + Build.PRODUCT.length()%10 + 
	        	Build.TAGS.length()%10 + Build.TYPE.length()%10 + 
	        	Build.USER.length()%10 ; //13 digits
		
		return m_szDevIDShort;
	}
}
