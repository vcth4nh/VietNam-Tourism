package gui;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import tourismobject.TourismObject;

public class JSONWrapper {
    @JsonProperty("@graph")
    private List<TourismObject> graph;
    @JsonProperty("@context")
    private String context;
}
