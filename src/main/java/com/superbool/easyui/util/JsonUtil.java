package com.superbool.easyui.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class JsonUtil {

    /**
     * @param rs
     * @return
     * @throws Exception
     */
    public static JsonArray formatRsToJsonArray(ResultSet rs) throws Exception {
        ResultSetMetaData md = rs.getMetaData();
        int num = md.getColumnCount();
        JsonArray array = new JsonArray();
        while (rs.next()) {
            JsonObject mapOfColValues = new JsonObject();
            for (int i = 1; i <= num; i++) {
                mapOfColValues.add(md.getColumnName(i), (JsonElement) rs.getObject(i));
            }
            array.add(mapOfColValues);
        }
        return array;
    }
}
