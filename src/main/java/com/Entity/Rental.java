package com.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Entity  //標示類別為"永續類別"
@Table(name = "rental")  //此"永續類別"對應到的表格
public class Rental {

    @Id //標示為PK
    @Column(name="rentalno")
    private Integer rentalNo;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "rentalcatno", referencedColumnName = "rentalcatno") //對應rental的rCatNo
    private RentalCategory rentalCategory;
    @Column(name="rentalname", length=40)
    private String rentalName;
    @Column(name="rentalprice",columnDefinition="BigDecimal")
    private BigDecimal rentalPrice;
    @Column(name="rentalsize")
    private Integer rentalSize;
    @Column(name="rentalcolor", length=10)
    private String rentalColor;
    @Column(name="rentalinfo", length=1000)
    private String rentalInfo;
    @Column(name="rentalstat",columnDefinition = "TINYINT")
    private Byte rentalStat;

    @JsonBackReference
    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private Set<RentalOrderDetails> rentalOrderDetails;
    @JsonBackReference
    @OneToMany(mappedBy = "rental",cascade = CascadeType.ALL)
    private Set<RentalMyFavorite> rentalMyFavorites;
    @JsonBackReference
    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private Set<RentalPic> rentalPics;
    @JsonBackReference
    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private Set<RentalMyTrack> rentalMyTracks;

    public Rental() {
    }

    public Rental(Integer rentalNo) {
        this.rentalNo = rentalNo;
    }

    public Rental(RentalCategory rentalCategory, String rentalName, BigDecimal rentalPrice, Integer rentalSize, String rentalColor, String rentalInfo, Byte rentalStat) {
        this.rentalCategory = rentalCategory;
        this.rentalName = rentalName;
        this.rentalPrice = rentalPrice;
        this.rentalSize = rentalSize;
        this.rentalColor = rentalColor;
        this.rentalInfo = rentalInfo;
        this.rentalStat = rentalStat;
    }

    public Rental(Integer rentalNo, RentalCategory rentalCategory, String rentalName, BigDecimal rentalPrice, Integer rentalSize, String rentalColor, String rentalInfo, Byte rentalStat) {
        this.rentalNo = rentalNo;
        this.rentalCategory = rentalCategory;
        this.rentalName = rentalName;
        this.rentalPrice = rentalPrice;
        this.rentalSize = rentalSize;
        this.rentalColor = rentalColor;
        this.rentalInfo = rentalInfo;
        this.rentalStat = rentalStat;
    }

    public Integer getRentalNo() {
        return rentalNo;
    }

    public void setRentalNo(Integer rentalNo) {
        this.rentalNo = rentalNo;
    }

    public RentalCategory getRentalCategory() {
        return rentalCategory;
    }

    public void setRentalCategory(RentalCategory rentalCategory) {
        this.rentalCategory = rentalCategory;
    }

    public String getRentalName() {
        return rentalName;
    }

    public void setRentalName(String rentalName) {
        this.rentalName = rentalName;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public Integer getRentalSize() {
        return rentalSize;
    }

    public void setRentalSize(Integer rentalSize) {
        this.rentalSize = rentalSize;
    }

    public String getRentalColor() {
        return rentalColor;
    }

    public void setRentalColor(String rentalColor) {
        this.rentalColor = rentalColor;
    }

    public String getRentalInfo() {
        return rentalInfo;
    }

    public void setRentalInfo(String rentalInfo) {
        this.rentalInfo = rentalInfo;
    }

    public Byte getRentalStat() {
        return rentalStat;
    }

    public void setRentalStat(Byte rentalStat) {
        this.rentalStat = rentalStat;
    }

    public Set<RentalOrderDetails> getRentalOrderDetails() {
        return rentalOrderDetails;
    }

    public void setRentalOrderDetails(Set<RentalOrderDetails> rentalOrderDetails) {
        this.rentalOrderDetails = rentalOrderDetails;
    }

    public Set<RentalMyFavorite> getRentalMyFavorites() {
        return rentalMyFavorites;
    }

    public void setRentalMyFavorites(Set<RentalMyFavorite> rentalMyFavorites) {
        this.rentalMyFavorites = rentalMyFavorites;
    }

    public Set<RentalPic> getRentalPics() {
        return rentalPics;
    }

    public void setRentalPics(Set<RentalPic> rentalPics) {
        this.rentalPics = rentalPics;
    }

    public Set<RentalMyTrack> getRentalMyTracks() {
        return rentalMyTracks;
    }

    public void setRentalMyTracks(Set<RentalMyTrack> rentalMyTracks) {
        this.rentalMyTracks = rentalMyTracks;
    }
}