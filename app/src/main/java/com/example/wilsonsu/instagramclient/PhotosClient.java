package com.example.wilsonsu.instagramclient;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by wilsonsu on 2/2/16.
 */
public class PhotosClient {
//        CLIENT_ID = e05c462ebd86446ea48a5af73769b602
//                - https://api.instagram.com/v1/media/popular?client_id=e05c462ebd86446ea48a5af73769b602
//                - Type : data -> [] ->"type" ->"image" or "video"
//                - URL : data -> [] -> "images" -> "standard_resolution" -> "url"
    private final String API_BASE_URL = "https://api.instagram.com/v1/media/";
    private final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    private AsyncHttpClient client;

    public PhotosClient() {
        this.client = new AsyncHttpClient();
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    public void getPopularPhotos(JsonHttpResponseHandler handler) {
        String url = getApiUrl("popular");
        RequestParams params = new RequestParams("client_id", CLIENT_ID);
        client.get(url, params, handler);
    }


}
