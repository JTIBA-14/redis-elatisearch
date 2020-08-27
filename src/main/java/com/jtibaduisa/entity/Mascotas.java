package com.jtibaduisa.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "mascotas")
public class Mascotas {
    @Id
    private String id;
    private String nombre;
    private int edad;
    private String color;
    private String raza;
}
