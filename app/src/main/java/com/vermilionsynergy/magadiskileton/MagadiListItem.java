package com.vermilionsynergy.magadiskileton;

/**
 * Created by ultron on 23.02.17.
 */

public class MagadiListItem {
    private int id;
    private String name;
    private String Category;
    private Double Cost;
    private String img;


    public MagadiListItem(String id, String name, String category, String cost, String img){
        try{
            this.id = Integer.parseInt(id);
            this.Cost = Double.parseDouble(cost);
        } catch (Exception e){
            this.id = 0;
        }
        this.name = name;
        this.Category = category;
        this.img = img;
    }

    public int getId(){
        return this.id;
    }
    public String getIds(){
        return this.id+"";
    }
    public String getCost(){
        return this.Cost + "";
    }
    public String getImg(){
        return this.img;
    }
    public String getName(){
        return this.name;
    }
    public String getCategory(){
        return this.Category;
    }
    public String toString(){
        return this.id + "/" + this.name + "/" + this.Category + "/" +this.Cost + "/" +this.img;
    }
}
