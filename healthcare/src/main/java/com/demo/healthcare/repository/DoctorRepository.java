package com.demo.healthcare.repository;

import com.demo.healthcare.demo.jpql.PatientSummary;
import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface DoctorRepository
        extends JpaRepository<Doctor, Long> {

    // AGGREGATION
/*
    COUNT() - Total number of rows
    SUM() - Sum of numeric values
    AVG() - Average value
    MAX() - Maximum value
    MIN() - Minimum value
 */
    @Query("SELECT d.specialization, count(p) FROM Doctor d JOIN d.patients p " +
            "WHERE d.specialization =?1 GROUP BY d.specialization")
    List<Object[]> countOfPatientsBySpecialization(String specialization);

}
