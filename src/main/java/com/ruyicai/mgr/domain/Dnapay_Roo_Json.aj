// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.Dnapay;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Dnapay_Roo_Json {
    
    public String Dnapay.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static Dnapay Dnapay.fromJsonToDnapay(String json) {
        return new JSONDeserializer<Dnapay>().use(null, Dnapay.class).deserialize(json);
    }
    
    public static String Dnapay.toJsonArray(Collection<Dnapay> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Dnapay> Dnapay.fromJsonArrayToDnapays(String json) {
        return new JSONDeserializer<List<Dnapay>>().use(null, ArrayList.class).use("values", Dnapay.class).deserialize(json);
    }
    
}
