package com.cloudbia.cloudcompare.contentProvider;

import android.content.Context;

import com.ibm.mobilefirstplatform.clientsdk.android.security.api.AuthenticationContext;
import com.ibm.mobilefirstplatform.clientsdk.android.security.api.AuthenticationListener;

import org.json.JSONObject;

/**
 * Created by shakeeb on 14/10/15.
 */
public class CustomAuthenticationListener implements AuthenticationListener {

    @Override
    public void onAuthenticationChallengeReceived(AuthenticationContext authContext, JSONObject challenge, Context context) {
        //1. read the challenge JSONObject
        //2. handle the challenge (use the context for handling UI based operations)
        //3. return response using the AuthenticationContext authContext
    }

    @Override
    public void onAuthenticationSuccess(Context context, JSONObject info) {
        //additional operations in case of authentication success
    }

    @Override
    public void onAuthenticationFailure(Context context, JSONObject info) {
        //additional operations in case of authentication failure
    }

}
