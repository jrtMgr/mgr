// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.TwithdrawReview;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect TwithdrawReview_Roo_Json {
    
    public String TwithdrawReview.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static TwithdrawReview TwithdrawReview.fromJsonToTwithdrawReview(String json) {
        return new JSONDeserializer<TwithdrawReview>().use(null, TwithdrawReview.class).deserialize(json);
    }
    
    public static String TwithdrawReview.toJsonArray(Collection<TwithdrawReview> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<TwithdrawReview> TwithdrawReview.fromJsonArrayToTwithdrawReviews(String json) {
        return new JSONDeserializer<List<TwithdrawReview>>().use(null, ArrayList.class).use("values", TwithdrawReview.class).deserialize(json);
    }
    
}
