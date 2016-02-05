package com.example.wilsonsu.instagramclient.Models;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.Map;

/**
 * Created by wilsonsu on 2/2/16.
 */
public class InstagramPhoto {

    public String type;
    public String link;
    public InstagramComments comments;
    public InstagramUser user;

    @JsonProperty("location")
    public String locationName;

    @JsonProperty("caption")
    public String title;

    @JsonProperty("created_time")
    public Date time;

    @JsonProperty("images")
    public InstagramImages photo;

    @JsonProperty("likes")
    public int likesCount;

    public void setTime(Date time) {
        time.setTime(time.getTime()*1000);
        this.time = time;
    }

    public void setLocationName(Map<String, Object> locationObject) {
        if (locationObject != null && locationObject.get("name") != null) {
            this.locationName = (String)locationObject.get("name");
        }
    }

    public void setTitle(Map<String, Object> titleObject) {
        if (titleObject != null && titleObject.get("text") != null) {
            this.title = (String)titleObject.get("text");
        }
    }

    public void setLikesCount(Map<String, Object> likesObject) {
        if (likesObject!= null && likesObject.get("count") != null) {
            this.likesCount = (Integer) likesObject.get("count");
        }
    }
}
