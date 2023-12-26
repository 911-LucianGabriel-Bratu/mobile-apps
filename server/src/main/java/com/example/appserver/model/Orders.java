package com.example.appserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Orders")
public class Orders {
    @Id
    private int orderID;

    @ManyToOne
    @JoinColumn(name = "musicalInstrumentID", nullable = false)
    private MusicalInstruments musicalInstrument;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private Users user;

    private Date orderedAt;
    private int quantity;
    private float totalPrice;
    private boolean deleted;
}