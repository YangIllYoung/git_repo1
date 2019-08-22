package com.example.cooker.model;

public class QnA {
    private String qna_date;
    private String qna_subject;
    private String qna_re_status;
    private String qna_content;
    private String qna_re;
    private String mem_email;
    private String mem_phone;
    private String qna_file;

    public QnA() {
    }

    public QnA(String qna_date, String qna_subject, String qna_re_status, String qna_content, String qna_re, String mem_email, String mem_phone, String qna_file) {
        this.qna_date = qna_date;
        this.qna_subject = qna_subject;
        this.qna_re_status = qna_re_status;
        this.qna_content = qna_content;
        this.qna_re = qna_re;
        this.mem_email = mem_email;
        this.mem_phone = mem_phone;
        this.qna_file = qna_file;
    }

    public String getQna_date() {
        return qna_date;
    }

    public void setQna_date(String qna_date) {
        this.qna_date = qna_date;
    }

    public String getQna_subject() {
        return qna_subject;
    }

    public void setQna_subject(String qna_subject) {
        this.qna_subject = qna_subject;
    }

    public String getQna_re_status() {
        return qna_re_status;
    }

    public void setQna_re_status(String qna_re_status) {
        this.qna_re_status = qna_re_status;
    }

    public String getQna_content() {
        return qna_content;
    }

    public void setQna_content(String qna_content) {
        this.qna_content = qna_content;
    }

    public String getQna_re() {
        return qna_re;
    }

    public void setQna_re(String qna_re) {
        this.qna_re = qna_re;
    }

    public String getMem_email() {
        return mem_email;
    }

    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public String getMem_phone() {
        return mem_phone;
    }

    public void setMem_phone(String mem_phone) {
        this.mem_phone = mem_phone;
    }

    public String getQna_file() {
        return qna_file;
    }

    public void setQna_file(String qna_file) {
        this.qna_file = qna_file;
    }
}
