package com.example.cooker.model;

public class Review {
    private String foodname;
    private String review_date;
    private float rating;
    private String mem_id;
    private String review_content;
    private int image;

    public Review() {
    }

    public Review(String foodname, String review_date, float rating, String mem_id, String review_content, int image) {
        this.foodname = foodname;
        this.review_date = review_date;
        this.rating = rating;
        this.mem_id = mem_id;
        this.review_content = review_content;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }
}
