package com.example.task01.controller;

import com.example.task01.model.Example;
import com.example.task01.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
@RestController
public class ExampleController {

    @Autowired
    private ExampleService service;

    @GetMapping("/examples")
    public List<Example> getAll() {
        return service.getAll();
    }

    @GetMapping("/example/{exampleId}")
    public Example get(@PathVariable("exampleId") String exampleId) {
        return service.findByExampleId(exampleId).orElse(null);
    }

    @PutMapping("/example/{exampleId}")
    public Example update(@PathVariable("exampleId") String exampleId, @RequestBody Example example) {
        return service.update(exampleId, example);
    }

    @PostMapping("/example")
    private String add(@RequestBody Example example) {
        service.add(example);
        return example.getExampleId();
    }

    @DeleteMapping("/example/{exampleId}")
    private void deleteById(@PathVariable("exampleId") String exampleId) {

        service.deleteById(exampleId);
    }
}
