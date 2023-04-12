package org.nasdanika.graph.tests;

import java.util.Collection;

import org.json.JSONObject;

public record EClassifierRecord(JSONObject node, Collection<JSONObject> links) {

}
