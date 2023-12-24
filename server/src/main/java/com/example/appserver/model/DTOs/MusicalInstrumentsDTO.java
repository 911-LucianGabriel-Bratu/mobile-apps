package com.example.appserver.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicalInstrumentsDTO {
    private int musicalInstrumentID;
    private int instrumentCategoryID;
    private int instrumentBrandID;
    private String musicalInstrumentName;
    private String description;
    private String pngUrl;
    private float price;
    private int quantity;
    private boolean onSale;
}
