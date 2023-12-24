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
@Table(name = "InstrumentCategories")
public class InstrumentCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int instrumentCategoryID;

    private String instrumentCategoryName;
    private String pngUrl;
}