package com.example.wilsonsu.instagramclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.wilsonsu.instagramclient.Models.InstagramComment;
import com.example.wilsonsu.instagramclient.Models.InstagramCommentsMeta;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class CommentsActivity extends AppCompatActivity {
    private ArrayList<InstagramComment> comments;
    private InstagramCommentAdapter aComments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);


        comments = new ArrayList<InstagramComment>();
        // create adapter linking it to the resource
        aComments = new InstagramCommentAdapter(this, R.layout.item_comment, comments);
        // Find the listView of layout
        ListView lvPhotos = (ListView) findViewById(R.id.lvComment);
        lvPhotos.setAdapter(aComments);

        String id = getIntent().getExtras().getString("commentsId");
        fetchPopularPhotos(id);
    }

    public void fetchPopularPhotos(String id) {
        PhotosClient client = new PhotosClient();
        client.getComments(id ,defaultPopularPhotosHandler());
    }


    private JsonHttpResponseHandler defaultPopularPhotosHandler() {
        return new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    InstagramCommentsMeta meta = mapper.readValue(response.toString(), InstagramCommentsMeta.class);
                    comments.clear();
                    comments.addAll(meta.data);
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                aComments.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        };
    }
}
