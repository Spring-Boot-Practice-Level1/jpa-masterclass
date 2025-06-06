package com.demo.healthcare.demo.jpql;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryParameters{

    @Autowired
    private PatientRepository patientRepository;

    public void execute(EntityManager entityManager){

        // NAMED
//        TypedQuery<Patient> query = entityManager.createQuery(
//                "SELECT p FROM Patient p WHERE p.name = :name",
//                Patient.class
//        );
//        query.setParameter("name", "John Doe");
//        System.out.println("SIZE: " + query.getResultList().size());

        // POSITIONAL
//        TypedQuery<Patient> query = entityManager.createQuery(
//                "SELECT p FROM Patient p WHERE p.name = ?1" +
//                        "AND p.gender = ?2",
//                Patient.class
//        );
//        query.setParameter(1, "John Doe");
//        query.setParameter(2, Gender.MALE);
//        System.out.println("SIZE: " + query.getResultList().size());

        // USING REPOSITORIES
        List<Patient> p = patientRepository.findByName("John Doe");
        System.out.println("SIZE: " + p.size());

        List<Patient> p1 = patientRepository
                .findByNameAndGender("John Doe", Gender.MALE);
        System.out.println("SIZE: " + p1.size());

        System.out.println("Exists by email: "
                + patientRepository.existsByEmail("john@email.com"));

        System.out.println("Count by age: "
                + patientRepository.countByAge(31));

    }
}
