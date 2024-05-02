package com.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity  //標示類別為"永續類別"
@Table(name = "rentalpic")  //此"永續類別"對應到的表格
public class RentalPic {

    @Id //標示為PK
    @Column(name = "rentalpicno", nullable = false)
    private Integer rentalPicNo;

    @Column(name = "rentalpic", columnDefinition = "longblob")
    private byte[] rentalPic;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "rentalno", referencedColumnName = "rentalno")
    private Rental rental;

    public RentalPic() {
    }

    public RentalPic(Integer rentalPicNo) {
        this.rentalPicNo = rentalPicNo;
    }

    public RentalPic(byte[] rentalPic, Rental rental) {
        this.rentalPic = rentalPic;
        this.rental = rental;
    }

    public RentalPic(Integer rentalPicNo, byte[] rentalPic, Rental rental) {
        this.rentalPicNo = rentalPicNo;
        this.rentalPic = rentalPic;
        this.rental = rental;
    }

    public Integer getRentalPicNo() {
        return rentalPicNo;
    }

    public void setRentalPicNo(Integer rentalPicNo) {
        this.rentalPicNo = rentalPicNo;
    }

    public byte[] getRentalPic() {
        return rentalPic;
    }

    public void setRentalPic(byte[] rentalPic) {
        this.rentalPic = rentalPic;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
