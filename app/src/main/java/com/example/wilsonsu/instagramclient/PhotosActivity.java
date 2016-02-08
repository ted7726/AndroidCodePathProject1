package com.example.wilsonsu.instagramclient;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ListView;

import com.example.wilsonsu.instagramclient.Models.InstagramMeta;
import com.example.wilsonsu.instagramclient.Models.InstagramPhoto;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotoAdapter aPhotos;

    @Bind(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        ButterKnife.bind(this);


        photos = new ArrayList<InstagramPhoto>();
        // create adapter linking it to the resource
        aPhotos = new InstagramPhotoAdapter(this, R.layout.item_photo, photos);
        // Find the listView of layout
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(aPhotos);

        //setup swipe container
//        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // send async call to fetch from network
                fetchPopularPhotos();
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // need to fetch data at the first time
        fetchPopularPhotos();
    }

    public void fetchPopularPhotos() {
        PhotosClient client = new PhotosClient();
        client.getPopularPhotos(defaultPopularPhotosHandler());
    }

    private JsonHttpResponseHandler defaultPopularPhotosHandler() {
        return new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                    InstagramMeta meta = mapper.readValue(response.toString(), InstagramMeta.class);

                    photos.clear();
                    photos.addAll(meta.data);

                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                aPhotos.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        };
    }

    public void onCommentsClick(View view) {
        // show comments Activity

    }

    public void onPlayVideo(View view) {
        // show comments Activity
        String url = view.getTag().toString();
        if (url.length()>0) {
            FragmentManager fm = getSupportFragmentManager();
            VideoFragmentDialog videoFD = VideoFragmentDialog.newInstance(url);
            videoFD.show(fm, "fragment_video");
        }
    }
}

