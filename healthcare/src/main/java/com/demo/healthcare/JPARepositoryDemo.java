package com.demo.healthcare;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class JPARepositoryDemo implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public JPARepositoryDemo(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Patient p1 = new Patient("John Doe" , 30);
        patientRepository.save(p1);

        Patient p2 = new Patient("Jane" , 40);
        patientRepository.save(p2);

//        List<Patient> patientList = patientRepository.findAll();
//        for (Patient patient : patientList) {
//            System.out.println("Patient: " + patient.getName());
//        }

        patientRepository.findAll().forEach(
                x -> System.out.println("Patient: " + x.getName())
        );

        Patient patient = patientRepository.findById(Long.valueOf(2)).get();
        System.out.println("Patient ID 2 : " + patient.getName());

        // UPDATE DATA
        Patient patientIdOne = patientRepository.findById(Long.valueOf(1)).get();
        patientIdOne.setName("John Doe Updated");
        patientRepository.save(patientIdOne);

        // DELETE DATA
        Patient patientIdTwo = patientRepository.findById(Long.valueOf(2)).get();
        patientRepository.delete(patientIdTwo);

        System.out.println("Exist : " + patientRepository.existsById(100L));
    }
}
