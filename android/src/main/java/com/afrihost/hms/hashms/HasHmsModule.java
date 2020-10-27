package com.afrihost.hms.hashms;

import android.util.Log;
import android.widget.Toast;
import android.content.Context;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.huawei.hms.api.HuaweiApiAvailability;

public class HasHmsModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext mContext;

    public HasHmsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mContext = reactContext;
    }

    @Override
    public String getName() {
        return "HasHms";
    }

    @ReactMethod
    public void getPackageName() {
        Toast.makeText(getReactApplicationContext(),"HasHms has been called",Toast.LENGTH_LONG).show();
    }

    @ReactMethod
    public void isHmsAvailable(Callback booleanCallback) {
        boolean isAvailable = false;
        Context context = getReactApplicationContext();
        if (null != context) {
            int result = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context);
            isAvailable = (com.huawei.hms.api.ConnectionResult.SUCCESS == result);
        }
        Log.i("React", "isHmsAvailable: " + isAvailable);
        booleanCallback.invoke(isAvailable);
    }

    @ReactMethod
    public void isGmsAvailable(Callback booleanCallback) {
        boolean isAvailable = false;
        Context context = getReactApplicationContext();
        if (null != context) {
            int result = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
            isAvailable = (com.google.android.gms.common.ConnectionResult.SUCCESS == result);
        }
        Log.i("React",  "isGmsAvailable: " + isAvailable);
        booleanCallback.invoke(isAvailable);
    }
}
