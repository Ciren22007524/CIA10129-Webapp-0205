package com.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

@Entity
@Table(name = "rentalmytrack")
public class RentalMyTrack implements Serializable {
    @EmbeddedId
    private CompositeTrack compositeTrack;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "rentalno", referencedColumnName = "rentalno", insertable = false, updatable = false)
    private Rental rental;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "memno", referencedColumnName = "memno", insertable = false, updatable = false)
    private Member member;
    @Column(name = "rentaltracktime")
    private Timestamp rTrackTime;
    @Column(name = "exprentaldate")
    private Date expRentalDate;

    @Embeddable
    public static class CompositeTrack implements Serializable {

        @Column(name = "rentalno")
        private Integer rentalNo;

        @Column(name = "memno")
        private Integer memNo;

        public Integer getRentalNo() {
            return rentalNo;
        }

        public void setRentalNo(Integer rentalNo) {
            this.rentalNo = rentalNo;
        }

        public Integer getMemNo() {
            return memNo;
        }

        public void setMemNo(Integer memNo) {
            this.memNo = memNo;
        }

        public CompositeTrack() {
        }

        public CompositeTrack(Integer rentalNo, Integer memNo) {
            this.rentalNo = rentalNo;
            this.memNo = memNo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeTrack that)) return false;
            return Objects.equals(getRentalNo(), that.getRentalNo()) && Objects.equals(getMemNo(), that.getMemNo());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRentalNo(), getMemNo());
        }

    } // 內部類別結束

    public RentalMyTrack() {
    }

    public RentalMyTrack(CompositeTrack compositeTrack) {
        this.compositeTrack = compositeTrack;
    }

    public RentalMyTrack(Rental rental, Member member, Timestamp rTrackTime, Date expRentalDate) {
        this.rental = rental;
        this.member = member;
        this.rTrackTime = rTrackTime;
        this.expRentalDate = expRentalDate;
    }

    public RentalMyTrack(CompositeTrack compositeTrack, Rental rental, Member member, Timestamp rTrackTime, Date expRentalDate) {
        this.compositeTrack = compositeTrack;
        this.rental = rental;
        this.member = member;
        this.rTrackTime = rTrackTime;
        this.expRentalDate = expRentalDate;
    }

    public CompositeTrack getCompositeTrack() {
        return compositeTrack;
    }

    public void setCompositeTrack(CompositeTrack compositeTrack) {
        this.compositeTrack = compositeTrack;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Timestamp getrTrackTime() {
        return rTrackTime;
    }

    public void setrTrackTime(Timestamp rTrackTime) {
        this.rTrackTime = rTrackTime;
    }

    public Date getExpRentalDate() {
        return expRentalDate;
    }

    public void setExpRentalDate(Date expRentalDate) {
        this.expRentalDate = expRentalDate;
    }
}
