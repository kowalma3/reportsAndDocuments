package com.example.task01.service;

import com.example.task01.model.Example;

import java.util.List;
import java.util.Optional;

public interface ExampleService {
    List<Example> getAll();
    Optional<Example> findByExampleId(String exampleId);
    Example add(Example e);
    Example update(String exampleId, Example e);
    void deleteById(String id);
}
