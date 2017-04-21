package com.vermilionsynergy.magadiskileton;

import java.util.HashMap;

public class Contact extends HashMap<String, String> {

    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String TEXT = "text";
    public static final int IMAGE = R.drawable.ic_menu_camera;

    // Конструктор с параметрами
    public Contact(String id, String name, String text) {
        super();
        super.put(NAME, name);
        super.put(ID, id);
        super.put(TEXT, text);
    }

    public String getText(){
        return this.TEXT;
    }
}