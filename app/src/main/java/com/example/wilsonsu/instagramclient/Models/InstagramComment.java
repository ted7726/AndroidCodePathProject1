package com.example.wilsonsu.instagramclient.Models;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * Created by weishengsu on 2/7/16.
 */
public class InstagramComment {
    @JsonProperty("created_time")
    public Date time;

    public String text;

    @JsonProperty("from")
    public InstagramUser user;

    public void setTime(Date time) {
        time.setTime(time.getTime()*1000);
        this.time = time;
    }
}
