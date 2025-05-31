package com.demo.healthcare.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "medicines")
    private List<Prescription> prescriptions;

    public Medicine(String name) {
        this.name = name;
    }
}
