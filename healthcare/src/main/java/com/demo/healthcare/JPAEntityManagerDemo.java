package com.demo.healthcare;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class JPAEntityManagerDemo implements CommandLineRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Patient p = new Patient("John Doe" , 30);
        entityManager.persist(p);
//        System.out.println("Persisted Patient: " + p);
    }
}
