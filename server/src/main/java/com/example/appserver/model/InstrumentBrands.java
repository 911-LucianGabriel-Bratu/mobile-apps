package com.example.appserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "InstrumentBrands")
public class InstrumentBrands {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int instrumentBrandID;
    private String instrumentBrandName;
    private String pngUrl;
}