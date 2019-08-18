package com.orelandshadi.gamerfinder.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class HttpRequest {

    private static final String BASE_URL = "http://192.168.1.101/GamerFinder/";
    private String result;
    private Context mContext;

    public HttpRequest(Context context) {
        result = null;
        mContext = context;
    }

    public void httpPostJsonRequest(final String requestType, final JSONObject params, final HttpResponseCallback callback) {

        String URL = "";
        switch (requestType) {
            case "login":
                URL = BASE_URL + "userHandle/Login";
                Log.d("@url:", URL);
                break;
            case "isEmailExists":
                URL = BASE_URL + "userHandle/isEmailExists";
                Log.d("@url:", URL);
                break;
            case "isUserNameExists":
                URL = BASE_URL + "userHandle/isUserNameExists";
                Log.d("@url:", URL);
                break;
            default:
                callback.onErrorResponse("Something went wrong!");
                break;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("@onResponse", "......");
                        try {
                            switch (requestType) {
                                case "login":
                                    Log.d("@onResponse login", response.getString("user"));
                                    callback.onSuccessResponse("");
                                    break;
                                case "isEmailExists":
                                    Log.d("@onResponse isEmail", response.toString());
                                    callback.onSuccessResponse("");
                                    break;
                                case "isUserNameExists":
                                    Log.d("@onResponse isUser", response.toString());
                                    callback.onSuccessResponse("");
                                    break;
                                default:
                                    return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("@onErrorResponse", "......");

                        if (error == null || error.networkResponse == null) {
                            callback.onErrorResponse("onError null");
                        } else {
                            Log.d("@@@ Response ", "[inside else] status code: " + error.networkResponse.statusCode);
//                            callback.onErrorResponse(result);
                        }

                        if (error.networkResponse.data != null) {
                            try { //get response body and parse with appropriate encoding
                                String bodyString = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                                JSONObject bodyJSON = new JSONObject(bodyString);
                                switch (requestType) {
                                    case "login":
                                        Log.d("@@@ Response ", "status code: " + error.networkResponse.statusCode);
                                        result = bodyJSON.getString("Message");
                                        callback.onErrorResponse(result);
                                        break;

                                    case "isEmailExists":
                                        if (bodyJSON.getString("Message").equals("Email is already taken")) {
                                            result = "Email is already taken";
                                            callback.onErrorResponse(result);
                                        } else {
                                            result = "Something went wrong!";
                                            callback.onErrorResponse(result);
                                        }
                                        Log.d("@@@ Error ", "bodyJSON -> Message: " + bodyJSON.getString("Message"));
                                        break;

                                    case "isUserNameExists":
                                        if (bodyJSON.getString("Message").equals("UserName is already taken")) {
                                            result = "UserName is already taken";
                                            callback.onErrorResponse(result);
                                        } else {
                                            result = "Something went wrong!";
                                            callback.onErrorResponse(result);
                                        }
                                        Log.d("@@@ Error ", "bodyJSON -> Message: " + bodyJSON.getString("Message"));
                                        break;
                                    default:
                                        return;
                                }
                            } catch (JSONException e) {
                                Log.d("@@@ Error ", "[catch] JSONException");
                            }
                        }

                        if (error instanceof TimeoutError) {
                            //This indicates that the request has either time out
                            Log.d("@@@ TimeoutError", error.toString());
                            result = "TimeoutError!";
                            callback.onErrorResponse(result);
                        } else if (error instanceof NoConnectionError) {
                            // Error indicating that there is no connection
                            Log.d("@@@ NoConnectionError", error.toString());
                            result = "NoConnectionError!";
                            callback.onErrorResponse(result);
                        } else if (error instanceof AuthFailureError) {
                            // Error indicating that there was an Authentication Failure while performing the request
                            Log.d("@@@ AuthFailureError", error.toString());
                            result = "AuthFailureError!";
                            callback.onErrorResponse(result);
                        } else if (error instanceof NetworkError) {
                            //Indicates that there was network error while performing the request
                            Log.d("@@@ NetworkError", error.toString());
                            result = "NetworkError!";
                            callback.onErrorResponse(result);
                        } else if (error instanceof ParseError) {
                            // Indicates that the server response could not be parsed
                            Log.d("@@@ ParseError", error.toString());
                            result = "ParseError!";
                            callback.onErrorResponse(result);
                        } else if (error instanceof ServerError) {
                            //Indicates that the server responded with a error response
                            Log.d("@@@ ServerError", error.toString());
                            result = "ServerError! " + error.toString();
                            callback.onErrorResponse(result);
                        }
                    }
                }
        ) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    Log.d("@@@ parseNetwork", response.toString());
                    String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    Log.d("@@@ Unsupported", response.toString());
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    Log.d("@@@ JSONException", response.toString());
                    return Response.error(new ParseError(je));
                }
            }
        };

        Log.d("@@@ before queue", ".");
        HttpRequestQueue.getInstance(mContext).addToRequestQueue(request);
    }

}

