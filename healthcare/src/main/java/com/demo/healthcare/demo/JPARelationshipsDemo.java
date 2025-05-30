package com.demo.healthcare.demo;

import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.MedicalRecordRepository;
import com.demo.healthcare.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JPARelationshipsDemo implements CommandLineRunner {

    private PatientRepository patientRepository;
    private MedicalRecordRepository medicalRecordRepository;

    public JPARelationshipsDemo(PatientRepository patientRepository, MedicalRecordRepository medicalRecordRepository) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("Fever");
        medicalRecordRepository.save(medicalRecord);

        Patient patient = new Patient("John Doe" , 30);
        patient.setMedicalRecord(medicalRecord);
        patientRepository.save(patient);
    }
}
