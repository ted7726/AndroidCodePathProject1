package com.example.wilsonsu.instagramclient.Models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by wilsonsu on 2/3/16.
 */
public class InstagramImages {
    @JsonProperty("low_resolution")
    public InstagramImage lowResolution;
    @JsonProperty("standard_resolution")
    public InstagramImage standard;
    @JsonProperty("thumbnail")
    public InstagramImage thumbnail;

}
