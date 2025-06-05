package com.demo.healthcare.demo;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.model.Person;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.PatientRepository;
import com.demo.healthcare.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class InheritanceDemo implements CommandLineRunner {

    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private PersonRepository personRepository;

    public InheritanceDemo(DoctorRepository doctorRepository, PatientRepository patientRepository, PersonRepository personRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        doctor.setAge(45);
        doctor.setEmail("smith@hospital.com");
        doctor.setSpecialization("Cardiology");
        doctorRepository.save(doctor);

        Patient patient = new Patient(
                "John Doe",
                30,
                "john@email.com",
                Gender.MALE
        );
        patientRepository.save(patient);

        // Query all Person's
        List<Person> people = personRepository.findAll();
        System.out.println("All Person's");
        for (Person p : people) {
            System.out.println("- " + p.getName()
            + " (" + p.getClass().getSimpleName() + ")");
        }

        // USING PATIENT REPOSITORY
        Patient fetchedPatient = patientRepository.findById(patient.getId()).get();
        System.out.println(fetchedPatient.getName());

        // USING DOCTOR REPOSITORY
        Doctor fetchedDoctor = doctorRepository.findById(doctor.getId()).get();
        System.out.println(fetchedDoctor.getName());

    }
}
