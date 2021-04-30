package com.example.bookmanagement.model;

public class Book {
    private String idBook;
    private String idCategory;
    private String tieuDe;
    private String nxb;
    private String tacGia;
    private double giaBia;
    private int soLuong;

    public Book() {
    }

    public Book(String idBook, String idCategory, String tieuDe, String nxb, String tacGia, Double giaBia, int soLuong) {
        this.idBook = idBook;
        this.idCategory = idCategory;
        this.tieuDe = tieuDe;
        this.nxb = nxb;
        this.tacGia = tacGia;
        this.giaBia = giaBia;
        this.soLuong = soLuong;
    }



    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }
    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }
    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }



    public Double getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(Double giaBia) {
        this.giaBia = giaBia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString(){
        return "Book{" +
                "idBook='" +idBook+'\''+
                ", idCategory='"+idCategory+'\''+
                ", tieuDe='"+tieuDe+'\''+
                ", nxb='"+nxb+'\''+
                ", tacGia='"+tacGia+'\''+
                ", giaBia='"+giaBia+'\''+
                ", soLuong='"+soLuong+'\''+
                '}';
    }
}
