package com.example.wilsonsu.instagramclient.Models;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

/**
 * Created by wilsonsu on 2/3/16.
 */
public class InstagramComments {
    public int count;

    @JsonProperty("data")
    public ArrayList<InstagramComment> comments;
}
