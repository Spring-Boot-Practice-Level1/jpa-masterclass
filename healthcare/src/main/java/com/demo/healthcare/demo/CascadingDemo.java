package com.demo.healthcare.demo;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.MedicalRecordRepository;
import com.demo.healthcare.repository.PatientRepository;
import jakarta.persistence.CascadeType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class CascadingDemo implements CommandLineRunner {
    private PatientRepository patientRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private DoctorRepository doctorRepository;

    public CascadingDemo(PatientRepository patientRepository,
                                MedicalRecordRepository medicalRecordRepository,
                                DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor doctor1 = new Doctor("Dr. Alex");
//        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor("Dr. Alyne");
//        doctorRepository.save(doctor2);

        Patient patient1 = new Patient("John Doe" , 30);
        patient1.setDoctor(doctor1);
//        patientRepository.save(patient1);

        Patient patient2 = new Patient("Jane" , 33);
        patient2.setDoctor(doctor1);
//        patientRepository.save(patient2);

        patientRepository.saveAll(List.of(patient1, patient2));

        doctor1.setPatients(List.of(patient1, patient2));

//        CascadeType.REMOVE
//        Doctor doctor = doctorRepository.findById(1L).get();
//        doctorRepository.delete(doctor);

//        CascadeType.MERGE
//        System.out.println("====CascadeType.MERGE======");
//        Doctor managedDoctor = doctorRepository.findById(1L)
//                .orElseThrow();
//        managedDoctor.setName("Dr. Updated.");
//
//        Patient managedPatient = patientRepository.findById(1L)
//                .orElseThrow();
//        managedPatient.setAge(44);
//
//        managedDoctor.setPatients(List.of(managedPatient));
//
//        doctorRepository.save(managedDoctor);

    }
}
