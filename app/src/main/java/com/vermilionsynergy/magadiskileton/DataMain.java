package com.vermilionsynergy.magadiskileton;

import java.util.HashMap;

public class DataMain {
    private static Integer id;
    private static Integer img;




    public DataMain(Integer id, Integer img) {
        this.id = id;
        this.img = img;
    }
    public Integer getId(){
        return this.id;
    }
    public Integer getImg(){
        return this.img;
    }
}