package com.vermilionsynergy.magadiskileton;

import java.util.HashMap;

public class SearchItem extends HashMap<String, String> {

    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String SECTION = "section";

    public SearchItem(String id, String name, String section) {
        super();
        super.put(NAME, name);
        super.put(ID, id);
        super.put(SECTION, section);
    }
}