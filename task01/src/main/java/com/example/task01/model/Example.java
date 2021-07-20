package com.example.task01.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Example {

    @NotNull
    @Size(max = 30)
    public String exampleId;

    @Size(max = 30)
    public String description;
}








