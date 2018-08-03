package com.accuragroup.eg.VirAdmin.models;

/**
 * Created by Apex on 7/19/2017.
 */

public class CategoryModel {

    private String catName;
    private int catID;

    public CategoryModel(String catName, int catID) {
        this.catName = catName;
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public int getCatID() {
        return catID;
    }

    @Override
    public String toString() {
        return catName;
    }
}
