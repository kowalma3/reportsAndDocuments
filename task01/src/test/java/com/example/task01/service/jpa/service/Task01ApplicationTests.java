package com.example.task01.service.jpa.service;

import com.example.task01.model.Example;
import com.example.task01.service.ExampleService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.TestExecutionListeners;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestExecutionListeners(
        listeners = {DbUnitTestExecutionListener.class },
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
//@DatabaseTearDown("classpath:com/opuscapita/businessnetwork/masterdata/clearAllTables.xml")
@DatabaseSetup("data.xml")
@EnableJpaAuditing
class Task01ApplicationTests {

    @Autowired
    ExampleService service;

    @Test
    void getAll() {
        List<Example> allExamples = service.getAll();
        assertEquals(2, allExamples.size()); //porownac wszystkie
    }

    @Test
    void findByExampleId() {
        Example example = service.findByExampleId("1").orElse(null);
        assertEquals("1", example.getExampleId());
        assertEquals("first", example.getDescription());
    }

    @Test
    @ExpectedDatabase(value = "data-add-expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    void add() {
        Example example = new Example("3","third");
        service.add(example);
    }

    @Test
    @ExpectedDatabase(value = "data-update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    void update() {
        Example example = new Example("2","two");
        service.update("2",example);
    }

    @Test
    @ExpectedDatabase(value = "data-delete-expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    void deleteById() {
        service.deleteById("2");
    }
}
