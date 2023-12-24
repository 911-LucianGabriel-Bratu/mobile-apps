package com.example.appserver.model.DTOs;

import com.example.appserver.model.MusicalInstruments;
import com.example.appserver.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDTO {
    private int orderID;
    private int musicalInstrumentID;
    private int userID;
    private Date orderedAt;
    private int quantity;
    private float totalPrice;
    private boolean deleted;
}