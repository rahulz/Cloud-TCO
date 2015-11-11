package com.cloudbia.cloudcompare.contentProvider;

import com.ibm.mobilefirstplatform.clientsdk.android.core.api.Response;

import org.json.JSONObject;

/**
 * Created by shakeeb on 14/10/15.
 */
public interface ResponseListener {

    /**
     * This method will be called only when a response from the server has been received with a status
     * in the 200 range.
     * @param response the server response
     */
    void onSuccess(Response response);

    /**
     * This method will be called in the following cases:
     * <ul>
     * <li>There is no response from the server.</li>
     * <li>The status from the server response is in the 400 or 500 ranges.</li>
     * <li>There is an operational failure such as: authentication failure, data validation failure, or custom failure.</li>
     * </ul>
     * @param response Contains detail regarding why the Http request failed. May be null if the request did not reach the server
     * @param t Exception that could have caused the request to fail. null if no Exception thrown.
     * @param extendedInfo Contains details regarding operational failure. null if no operational failure occurred.
     */

    void onFailure(Response response, Throwable t, JSONObject extendedInfo);

}
