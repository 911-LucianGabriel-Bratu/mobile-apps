package com.example.appserver.repository;

import com.example.appserver.model.MusicalInstruments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMusicalInstrumentsRepo extends JpaRepository<MusicalInstruments, Integer> {
}
