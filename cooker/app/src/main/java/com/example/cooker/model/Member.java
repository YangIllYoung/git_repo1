package com.example.cooker.model;

import java.io.Serializable;

public class Member implements Serializable {
    private int mem_num;			//고객번호
    private String mem_id;			//고객아이디
    private String mem_pwd;			//비밀번호
    private String mem_name;		//이름
    private String mem_regident;	//주민 앞자리(ex)940222)
    private String mem_phone;		//전화번호
    private String mem_email;		//이메일
    private String mem_date;		//등록일
    private String mem_authority;	//권한(관리자, 일반사용자)
    private String mem_address; 	//주소
    private String mem_deli; 		//배송지

    public int getMem_num() {
        return mem_num;
    }

    public void setMem_num(int mem_num) {
        this.mem_num = mem_num;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_pwd() {
        return mem_pwd;
    }

    public void setMem_pwd(String mem_pwd) {
        this.mem_pwd = mem_pwd;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public String getMem_regident() {
        return mem_regident;
    }

    public void setMem_regident(String mem_regident) {
        this.mem_regident = mem_regident;
    }

    public String getMem_phone() {
        return mem_phone;
    }

    public void setMem_phone(String mem_phone) {
        this.mem_phone = mem_phone;
    }

    public String getMem_email() {
        return mem_email;
    }

    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public String getMem_date() {
        return mem_date;
    }

    public void setMem_date(String mem_date) {
        this.mem_date = mem_date;
    }

    public String getMem_authority() {
        return mem_authority;
    }

    public void setMem_authority(String mem_authority) {
        this.mem_authority = mem_authority;
    }

    public String getMem_address() {
        return mem_address;
    }

    public void setMem_address(String mem_address) {
        this.mem_address = mem_address;
    }

    public String getMem_deli() {
        return mem_deli;
    }

    public void setMem_deli(String mem_deli) {
        this.mem_deli = mem_deli;
    }
}
