package com.example.cooker.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int item_num;			// 상품번호
    private String item_category;	// 카테고리
    private String item_name;		// 상품명
    private String item_content;	// 상품내용
    private int item_price;			// 가격
    private int item_quantity;		// 재고량
    private String item_image;		// 상품이미지
    private String item_date;
    private String item_total;
    private String item_time;

    public Product(int item_num, String item_category, String item_name, String item_content, int item_price, int item_quantity, String item_image, String item_date, String item_total, String item_time) {
        this.item_num = item_num;
        this.item_category = item_category;
        this.item_name = item_name;
        this.item_content = item_content;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
        this.item_image = item_image;
        this.item_date = item_date;
        this.item_total = item_total;
        this.item_time = item_time;
    }

    public Product() {
    }



    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_content() {
        return item_content;
    }

    public void setItem_content(String item_content) {
        this.item_content = item_content;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public String getItem_date() {
        return item_date;
    }

    public void setItem_date(String item_date) {
        this.item_date = item_date;
    }


    public String getItem_total() {
        return item_total;
    }

    public void setItem_total(String item_total) {
        this.item_total = item_total;
    }

    public String getItem_time() {
        return item_time;
    }

    public void setItem_time(String item_time) {
        this.item_time = item_time;
    }
}
