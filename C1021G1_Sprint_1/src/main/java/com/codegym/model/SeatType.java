package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.Set;

@Entity  // Hạng ghế( Thương gia, thường )
public class SeatType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameSeatType;

    private Double priceSeatType;

    @JsonBackReference(value = "seatType_seat")
    @OneToMany(mappedBy = "seatType")
    private Set<Seat> seats;

    public SeatType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSeatType() {
        return nameSeatType;
    }

    public void setNameSeatType(String nameSeatType) {
        this.nameSeatType = nameSeatType;
    }

    public Double getPriceSeatType() {
        return priceSeatType;
    }

    public void setPriceSeatType(Double priceSeatType) {
        this.priceSeatType = priceSeatType;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }
}
