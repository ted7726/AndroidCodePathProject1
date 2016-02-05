package com.example.wilsonsu.instagramclient.Models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by wilsonsu on 2/2/16.
 */
public class InstagramImage {
    @JsonProperty("url")
    public String imageUrl;

    @JsonProperty("height")
    public int imageHeight;

    @JsonProperty("width")
    public int imageWidth;


}
