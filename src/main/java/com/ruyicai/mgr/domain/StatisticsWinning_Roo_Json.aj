// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.StatisticsWinning;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect StatisticsWinning_Roo_Json {
    
    public String StatisticsWinning.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static StatisticsWinning StatisticsWinning.fromJsonToStatisticsWinning(String json) {
        return new JSONDeserializer<StatisticsWinning>().use(null, StatisticsWinning.class).deserialize(json);
    }
    
    public static String StatisticsWinning.toJsonArray(Collection<StatisticsWinning> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<StatisticsWinning> StatisticsWinning.fromJsonArrayToStatisticsWinnings(String json) {
        return new JSONDeserializer<List<StatisticsWinning>>().use(null, ArrayList.class).use("values", StatisticsWinning.class).deserialize(json);
    }
    
}
