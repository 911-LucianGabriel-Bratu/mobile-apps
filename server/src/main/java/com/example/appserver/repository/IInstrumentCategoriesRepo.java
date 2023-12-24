package com.example.appserver.repository;

import com.example.appserver.model.InstrumentCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInstrumentCategoriesRepo extends JpaRepository<InstrumentCategories, Integer> {
}
