// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.Tlot;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Tlot_Roo_Json {
    
    public String Tlot.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static Tlot Tlot.fromJsonToTlot(String json) {
        return new JSONDeserializer<Tlot>().use(null, Tlot.class).deserialize(json);
    }
    
    public static String Tlot.toJsonArray(Collection<Tlot> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Tlot> Tlot.fromJsonArrayToTlots(String json) {
        return new JSONDeserializer<List<Tlot>>().use(null, ArrayList.class).use("values", Tlot.class).deserialize(json);
    }
    
}
