package com.demo.healthcare.model;

import jakarta.persistence.*;

import java.util.List;

/*

@Table(
    name = "patients", // The name of the table in the database.
    By default, JPA would use the class name (e.g., "Patient"),
    but here we're explicitly naming it "patients".

    catalog = "hospital_db", // Optional.
    Refers to the catalog (think of it as a database group).
    Rarely used unless your DB uses catalogs.

    schema = "public", // Refers to the schema under which the table resides
    (e.g., "public", "admin", etc.). Useful in PostgreSQL or Oracle DBs.

    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        // Ensures that the 'email' column must have unique values across
        all rows (i.e., no two patients can have the same email).

        @UniqueConstraint(columnNames = {"national_id"})
        // Similarly, ensures 'national_id' is unique — useful for real-world
        identifiers like SSNs, Aadhaar, etc.
    },

    indexes = {
        @Index(name = "idx_email", columnList = "email"),
        // Creates a named index "idx_email" on the 'email' column to
        improve search/query performance.

        @Index(name = "idx_created_at", columnList = "created_at DESC")
        // Adds an index on the 'created_at' column in descending order —
        useful if you often sort patients by most recent creation.
    }
)


 */


@Entity
@Table(name = "new_patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    @Embedded
    private Address address;

    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    @ManyToOne( cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "patientId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    public Patient() {
    }

    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
