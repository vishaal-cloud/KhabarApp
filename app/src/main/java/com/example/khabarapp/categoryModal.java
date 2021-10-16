package com.example.khabarapp;

public class categoryModal {
    private String category;
    private String  categoryImgUrl;

    public categoryModal(String category, String categoryImgUrl) {
        this.category = category;
        this.categoryImgUrl = categoryImgUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryImgUrl() {
        return categoryImgUrl;
    }

    public void setCategoryImgUrl(String categoryImgUrl) {
        this.categoryImgUrl = categoryImgUrl;
    }
}
