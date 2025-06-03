package com.demo.healthcare.demo;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

//@Component
public class OptimisticLockingDemo implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public OptimisticLockingDemo(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor drSmith = new Doctor("Dr. Smith");

        Patient alice = new Patient("Alice Johnson", 30);
        alice.setGender(Gender.MALE);
        alice.setDoctor(drSmith);

        patientRepository.save(alice);

        Patient p1 = patientRepository.findById(1L).get(); // 0
        Patient p2 = patientRepository.findById(1L).get(); // 0
        p1.setName("New Alice 1");
        patientRepository.save(p1); // 1

        try{
            p2.setName("New Alice 2"); // HAD 0
            patientRepository.save(p2); // 0
        } catch (ObjectOptimisticLockingFailureException e) {
            e.printStackTrace();
        }
    }
}
