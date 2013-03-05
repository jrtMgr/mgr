// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.Tlogic;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Tlogic_Roo_Json {
    
    public String Tlogic.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static Tlogic Tlogic.fromJsonToTlogic(String json) {
        return new JSONDeserializer<Tlogic>().use(null, Tlogic.class).deserialize(json);
    }
    
    public static String Tlogic.toJsonArray(Collection<Tlogic> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Tlogic> Tlogic.fromJsonArrayToTlogics(String json) {
        return new JSONDeserializer<List<Tlogic>>().use(null, ArrayList.class).use("values", Tlogic.class).deserialize(json);
    }
    
}
