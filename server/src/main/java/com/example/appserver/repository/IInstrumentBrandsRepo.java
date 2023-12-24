package com.example.appserver.repository;

import com.example.appserver.model.InstrumentBrands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInstrumentBrandsRepo extends JpaRepository<InstrumentBrands, Integer>{
}
