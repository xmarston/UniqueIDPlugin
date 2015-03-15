package es.dev_coders.UniqueIDPlugin;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.provider.Settings;

public class UniqueIDPlugin extends CordovaPlugin {

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
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
         if (action.equals("getUniqueID")) {
            String id = "";
            try {
                id = this.getId();
            } catch (JSONException e) { }

            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, id);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
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
}
