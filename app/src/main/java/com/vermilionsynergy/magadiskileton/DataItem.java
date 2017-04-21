package com.vermilionsynergy.magadiskileton;

import java.util.HashMap;

public class DataItem extends HashMap<String, String> {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String SECTION = "section";
    public static final String WEIGHT = "weight";
    public static final String COST = "cost";
    public static final String RATING = "rating";

    public DataItem(String id, String name, String section, String weight, String cost, String Rating) {
        super();
        super.put(ID, id);
        super.put(TITLE, name);
        super.put(SECTION, section);
        super.put(WEIGHT, weight);
        super.put(COST, cost);
        super.put(RATING, Rating);
    }
}