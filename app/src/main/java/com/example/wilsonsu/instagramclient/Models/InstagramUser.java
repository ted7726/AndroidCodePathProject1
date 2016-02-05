package com.example.wilsonsu.instagramclient.Models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by wilsonsu on 2/2/16.
 */
public class InstagramUser {
    public String username;

    @JsonProperty("full_name")
    public String fullname;

    @JsonProperty("profile_picture")
    public String profileUrl;
}
