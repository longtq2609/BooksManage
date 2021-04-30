package com.example.bookmanagement.model;

public class DetailBill {
    private int idHDCT;
    private Bill bill;
    private Book book;
    private int soLuongMua;

    public DetailBill() {
    }

    public DetailBill(int idHDCT, Bill bill, Book book, int soLuongMua) {
        this.idHDCT = idHDCT;
        this.bill = bill;
        this.book = book;
        this.soLuongMua = soLuongMua;
    }

    public int getIdHDCT() {
        return idHDCT;
    }

    public void setIdHDCT(int idHDCT) {
        this.idHDCT = idHDCT;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }
    @Override
    public String toString() {
        return "DataiBill{" +
                "idHDCT=" + idHDCT +
                ", bill=" + bill +
                ", book=" + book +
                ", soLuongMua=" + soLuongMua +
                '}';
    }
}
