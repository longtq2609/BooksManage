package com.example.bookmanagement.model;

import java.util.Date;

public class Bill {
    private String idBill;
    private Date dayBuy;

    public Bill() {
    }

    public Bill(String idBill, Date dayBuy) {
        this.idBill = idBill;
        this.dayBuy = dayBuy;
    }


    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public Date getDayBuy() {
        return dayBuy;
    }

    public void setDayBuy(Date dayBuy) {
        this.dayBuy = dayBuy;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "idBill='" + idBill + '\'' +
                ", dayBuy=" + dayBuy +
                '}';
    }
}
