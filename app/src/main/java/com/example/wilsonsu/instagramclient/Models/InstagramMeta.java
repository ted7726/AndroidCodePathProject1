package com.example.wilsonsu.instagramclient.Models;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wilsonsu on 2/3/16.
 */
public class InstagramMeta {
    @JsonProperty("data")
    public ArrayList<InstagramPhoto> data;
}
