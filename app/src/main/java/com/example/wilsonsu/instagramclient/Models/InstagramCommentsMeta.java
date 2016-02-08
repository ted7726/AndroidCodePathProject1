package com.example.wilsonsu.instagramclient.Models;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

/**
 * Created by weishengsu on 2/7/16.
 */
public class InstagramCommentsMeta {
    @JsonProperty("data")
    public ArrayList<InstagramComment> data;
}
