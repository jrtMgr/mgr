// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.mysql.domain;

import com.ruyicai.mgr.mysql.domain.TjingcaiMatches;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect TjingcaiMatches_Roo_Json {
    
    public String TjingcaiMatches.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static TjingcaiMatches TjingcaiMatches.fromJsonToTjingcaiMatches(String json) {
        return new JSONDeserializer<TjingcaiMatches>().use(null, TjingcaiMatches.class).deserialize(json);
    }
    
    public static String TjingcaiMatches.toJsonArray(Collection<TjingcaiMatches> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<TjingcaiMatches> TjingcaiMatches.fromJsonArrayToTjingcaiMatcheses(String json) {
        return new JSONDeserializer<List<TjingcaiMatches>>().use(null, ArrayList.class).use("values", TjingcaiMatches.class).deserialize(json);
    }
    
}
