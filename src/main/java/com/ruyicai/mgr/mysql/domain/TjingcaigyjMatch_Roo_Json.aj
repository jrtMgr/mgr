// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.mysql.domain;

import com.ruyicai.mgr.mysql.domain.TjingcaigyjMatch;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect TjingcaigyjMatch_Roo_Json {
    
    public String TjingcaigyjMatch.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static TjingcaigyjMatch TjingcaigyjMatch.fromJsonToTjingcaigyjMatch(String json) {
        return new JSONDeserializer<TjingcaigyjMatch>().use(null, TjingcaigyjMatch.class).deserialize(json);
    }
    
    public static String TjingcaigyjMatch.toJsonArray(Collection<TjingcaigyjMatch> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<TjingcaigyjMatch> TjingcaigyjMatch.fromJsonArrayToTjingcaigyjMatches(String json) {
        return new JSONDeserializer<List<TjingcaigyjMatch>>().use(null, ArrayList.class).use("values", TjingcaigyjMatch.class).deserialize(json);
    }
    
}