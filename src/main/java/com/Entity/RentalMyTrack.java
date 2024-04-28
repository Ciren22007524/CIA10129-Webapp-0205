package com.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

@Entity
@Table(name = "rentalmytrack")
@IdClass(RentalMyTrack.CompositeTrack.class)
public class RentalMyTrack implements Serializable {
    @Id
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "rno", referencedColumnName = "rno")
    private Rental rental;
    @Id
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "memno", referencedColumnName = "memno")
    private Member member;
	@Column(name = "rtracktime")
    private Timestamp rTrackTime;
	@Column(name = "exprentaldate")
    private Date expRentalDate;

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

    static class CompositeTrack implements Serializable {

        private Rental rental;
        private Member member;

        public CompositeTrack() {

        }

        public CompositeTrack(Rental rental, Member member) {
            this.rental = rental;
            this.member = member;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeTrack that)) return false;
            return Objects.equals(getRental(), that.getRental()) && Objects.equals(getMember(), that.getMember());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRental(), getMember());
        }

    }

    @Override
    public String toString() {
        return "RentalMyTrackVO{" +
                "rental=" + rental +
                ", member=" + member +
                ", rTrackTime=" + rTrackTime +
                ", expRentalDate=" + expRentalDate +
                '}';
    }

    public RentalMyTrack() {

    }

}
