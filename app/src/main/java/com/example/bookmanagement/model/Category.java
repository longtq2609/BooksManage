package com.example.bookmanagement.model;

public class Category {
    private String idCategory;
    private String nameCategory;
    private String moTa;
    private String viTri;

    public Category(String idCategory, String nameCategory, String moTa, String viTri) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.moTa = moTa;
        this.viTri = viTri;
    }

    public Category() {
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String maTa) {
        this.moTa = maTa;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }
    @Override
    public String toString(){
        return getIdCategory()+" | "+getNameCategory();
    }
}
