package com.example.wilsonsu.instagramclient.Models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by weishengsu on 2/7/16.
 */
public class InstagramVideos {
    @JsonProperty("low_resolution")
    public InstagramImage lowResolution;
    @JsonProperty("standard_resolution")
    public InstagramImage standard;
    @JsonProperty("low_bandwidth")
    public InstagramImage lobBandWidth;
}
