package com.demo.healthcare.repository;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository
        extends JpaRepository<Doctor, Long> {
}
