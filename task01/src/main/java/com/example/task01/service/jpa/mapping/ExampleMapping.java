package com.example.task01.service.jpa.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Example")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleMapping {

    @Id
    @GeneratedValue(generator = "nativeId")
    @GenericGenerator(name = "nativeId", strategy = "native")
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "exampleid")
    private String exampleId;

    @Column(name = "description")
    private String description;
}