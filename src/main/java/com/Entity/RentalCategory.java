package com.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Set;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity  //標示類別為"永續類別"
@Table(name = "rentalcategory")  //此"永續類別"對應到的表格
public class RentalCategory {

    @Id //標示此為Pk
    @Column(name="rentalcatno")
    private Integer rentalCatNo;

    @Column(name="rentalcatname", length=40)
    private String rentalCatName;

    @Column(name="rentalstockqty")
    private Integer rentalStockQty;

    @Column(name="rentalrentedqty")
    private Integer rentalRentedQty;

    @Column(name="rentaldesprice",columnDefinition="BigDecimal")
    private BigDecimal rentalDesPrice;
    @JsonBackReference
    @OneToMany(mappedBy = "rentalCategory", cascade = CascadeType.ALL) //CascadeType.ALL把對應到的相關資料刪除
    private Set<Rental> rentals;

    public RentalCategory() {
    }

    public RentalCategory(Integer rentalCatNo) {
        this.rentalCatNo = rentalCatNo;
    }

    public RentalCategory(String rentalCatName, Integer rentalStockQty, Integer rentalRentedQty, BigDecimal rentalDesPrice) {
        this.rentalCatName = rentalCatName;
        this.rentalStockQty = rentalStockQty;
        this.rentalRentedQty = rentalRentedQty;
        this.rentalDesPrice = rentalDesPrice;
    }

    public RentalCategory(Integer rentalCatNo, String rentalCatName, Integer rentalStockQty, Integer rentalRentedQty, BigDecimal rentalDesPrice) {
        this.rentalCatNo = rentalCatNo;
        this.rentalCatName = rentalCatName;
        this.rentalStockQty = rentalStockQty;
        this.rentalRentedQty = rentalRentedQty;
        this.rentalDesPrice = rentalDesPrice;
    }

    public Integer getRentalCatNo() {
        return rentalCatNo;
    }

    public void setRentalCatNo(Integer rentalCatNo) {
        this.rentalCatNo = rentalCatNo;
    }

    public String getRentalCatName() {
        return rentalCatName;
    }

    public void setRentalCatName(String rentalCatName) {
        this.rentalCatName = rentalCatName;
    }

    public Integer getRentalStockQty() {
        return rentalStockQty;
    }

    public void setRentalStockQty(Integer rentalStockQty) {
        this.rentalStockQty = rentalStockQty;
    }

    public Integer getRentalRentedQty() {
        return rentalRentedQty;
    }

    public void setRentalRentedQty(Integer rentalRentedQty) {
        this.rentalRentedQty = rentalRentedQty;
    }

    public BigDecimal getRentalDesPrice() {
        return rentalDesPrice;
    }

    public void setRentalDesPrice(BigDecimal rentalDesPrice) {
        this.rentalDesPrice = rentalDesPrice;
    }

    public Set<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }
}