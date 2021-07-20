package com.example.task01.service.jpa.service;

import com.example.task01.model.Example;
import com.example.task01.service.ExampleService;
import com.example.task01.service.jpa.mapping.ExampleMapping;
import com.example.task01.service.jpa.repository.ExampleRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class JpaExampleService implements ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    public List<Example> getAll() {
        return exampleRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Example> findByExampleId(String exampleId) {
        Optional<ExampleMapping> em = exampleRepository.findByExampleId(exampleId);
        return em.map(this::toModel);
    }

    public Example add(Example e) {
        ExampleMapping em = new ExampleMapping();
        BeanUtils.copyProperties(e, em);
        return toModel(exampleRepository.saveAndFlush(em));
    }

    public Example update(String exampleId, Example example) {
        Optional<ExampleMapping> em = exampleRepository.findByExampleId(exampleId);
        BeanUtils.copyProperties(example, em.orElseThrow(() -> new ObjectNotFoundException("asdfasdf", exampleId)), "id", "exampleId");
        return toModel(exampleRepository.saveAndFlush(em.get()));
    }

    public void deleteById(String exampleId) {
        exampleRepository.deleteByExampleId(exampleId);
    }

    protected Example toModel(ExampleMapping exampleMapping) {
        if (exampleMapping == null) {
            return null;
        }
        Example example = new Example();
        BeanUtils.copyProperties(exampleMapping, example);
        return example;
    }
}
