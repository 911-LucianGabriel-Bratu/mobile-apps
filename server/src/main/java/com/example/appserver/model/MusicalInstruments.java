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
@Table(name = "MusicalInstruments")
public class MusicalInstruments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int musicalInstrumentID;

    @ManyToOne
    @JoinColumn(name = "instrumentCategoryID", nullable = false)
    private InstrumentCategories instrumentCategory;

    @ManyToOne
    @JoinColumn(name = "instrumentBrandID", nullable = false)
    private InstrumentBrands instrumentBrand;

    private String musicalInstrumentName;
    private String description;
    private String pngUrl;
    private float price;
    private int quantity;
    private boolean onSale;
}