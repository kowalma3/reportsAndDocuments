package com.example.task01.service.jpa.repository;

import com.example.task01.service.jpa.mapping.ExampleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleMapping, Long>, JpaSpecificationExecutor<ExampleMapping> {

    Optional <ExampleMapping> findByExampleId(String exampleId);

    void deleteByExampleId(String exampleId);


}
