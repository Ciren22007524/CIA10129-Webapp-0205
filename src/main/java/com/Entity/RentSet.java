package com.Entity;

import javax.persistence.*;

@Entity  //標示類別為"永續類別"
@Table(name = "rentset")  //此"永續類別"對應到的表格
public class RentSet {

    @Id //標示為PK
    @Column(name = "rentalordno")
    private Integer rentalOrdNo;

    @Column(name = "rentalsetname", length = 20)
    private String rentalSetName;

    @Column(name = "rentalsetdays", columnDefinition = "tinyint")
    private Byte rentalSetDays;

    public RentSet() {
    }

    public RentSet(Integer rentalOrdNo) {
        this.rentalOrdNo = rentalOrdNo;
    }

    public RentSet(String rentalSetName, Byte rentalSetDays) {
        this.rentalSetName = rentalSetName;
        this.rentalSetDays = rentalSetDays;
    }

    public RentSet(Integer rentalOrdNo, String rentalSetName, Byte rentalSetDays) {
        this.rentalOrdNo = rentalOrdNo;
        this.rentalSetName = rentalSetName;
        this.rentalSetDays = rentalSetDays;
    }

    public Integer getRentalOrdNo() {
        return rentalOrdNo;
    }

    public void setRentalOrdNo(Integer rentalOrdNo) {
        this.rentalOrdNo = rentalOrdNo;
    }

    public String getRentalSetName() {
        return rentalSetName;
    }

    public void setRentalSetName(String rentalSetName) {
        this.rentalSetName = rentalSetName;
    }

    public Byte getRentalSetDays() {
        return rentalSetDays;
    }

    public void setRentalSetDays(Byte rentalSetDays) {
        this.rentalSetDays = rentalSetDays;
    }

    @Override
    public String toString() {
        return "RentSet{" +
                "rentalOrdNo=" + rentalOrdNo +
                ", rentalSetName='" + rentalSetName + '\'' +
                ", rentalSetDays=" + rentalSetDays +
                '}';
    }
}

