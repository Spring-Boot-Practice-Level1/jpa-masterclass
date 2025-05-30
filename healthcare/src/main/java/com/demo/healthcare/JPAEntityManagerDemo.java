package com.demo.healthcare;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JPAEntityManagerDemo implements CommandLineRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        Patient patient = new Patient("John Doe" , 30);
//        entityManager.persist(patient);
//
//        System.out.println("Is Managed: " + entityManager.contains(patient));
//
//        entityManager.detach(patient);
//
//        System.out.println("Is Managed [After detached]: " + entityManager.contains(patient));
//
//        patient.setName("John Doe Updated");
//        patient.setAge(95);
//        System.out.println("Persisted Patient: " + p);


        // FINDING ENTITIES
//        Patient jane = new Patient("Jane" , 20);
//        entityManager.persist(jane);
//        entityManager.detach(jane);
//
//        Patient findPatient1L = entityManager.find(Patient.class, 1L);
//        System.out.println(findPatient1L.getId());
//
//        Patient findPatient1LAgain = entityManager.find(Patient.class, 2L);
//        System.out.println(findPatient1LAgain.getId());

        // getReference()
//        Patient temp = entityManager.find(Patient.class, 100L);
//        System.out.println(temp.getName());

//        Patient patientProxy = entityManager.getReference(Patient.class, 1L);
//        System.out.println("GOT PROXY");
//        System.out.println(patientProxy.getName());
//
//        Patient tempReference = entityManager.getReference(Patient.class, 100L);
//        System.out.println(tempReference.getName());

        // merge()
//        Patient patient = new Patient("John Doe" , 30);
//        entityManager.persist(patient);
//
//        patient.setName("Jane");
//        entityManager.detach(patient);
//
//        System.out.println("Is Managed? " + entityManager.contains(patient));
//        Patient mergedPatient = entityManager.merge(patient);
//        System.out.println("Is Managed? " + entityManager.contains(mergedPatient));
//        mergedPatient.setName("Managed Name");
//        patient.setName("Not managed name");
//
//        Patient patient2 = new Patient("John Doe2" , 302);
//        entityManager.merge(patient2);
//
//        Patient p = entityManager.find(Patient.class, 1L);
//        p.setAge(100);
//        entityManager.merge(p);

        // remove()
//        Patient patient = new Patient("John Doe" , 30);
//        entityManager.persist(patient);
//        entityManager.detach(patient);
//        entityManager.remove(patient);
//        Patient p1 = new Patient(1L, "Test", 1);
//        entityManager.remove(p1);

//        clear() and flush()

//        Patient patient = new Patient("John Doe" , 30);
//        entityManager.persist(patient);
//        patient.setName("Update 1");
//        entityManager.flush();
//        patient.setAge(33);
//
//        entityManager.clear();
//        patient.setName("Update 2");
//
//        // MORE OPERATIONS

//        refresh()
        Patient patient = new Patient("John Doe" , 30);
        entityManager.persist(patient);
        patient.setName("Update 1");
        entityManager.refresh(patient);
        patient.setAge(155);

    }
}
