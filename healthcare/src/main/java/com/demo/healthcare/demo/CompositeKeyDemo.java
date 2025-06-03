package com.demo.healthcare.demo;

import com.demo.healthcare.model.*;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.MedicineRepository;
import com.demo.healthcare.repository.PatientRepository;
import com.demo.healthcare.repository.PrescriptionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Component
public class CompositeKeyDemo implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;

    public CompositeKeyDemo(DoctorRepository doctorRepository, PatientRepository patientRepository, PrescriptionRepository prescriptionRepository, MedicineRepository medicineRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor drSmith = new Doctor("Dr. Smith");

        Patient alice = new Patient("Alice Johnson", 30);
//        byte[] imageData = Files.readAllBytes(new ClassPathResource("/images/img.png").getFile().toPath());
//        alice.setProfilePicture(imageData);
        alice.setGender(Gender.MALE);
        alice.setDoctor(drSmith);

        MedicalRecord aliceRecord = new MedicalRecord("Allergic to penicillin");
        alice.setMedicalRecord(aliceRecord);
        patientRepository.save(alice);

        Medicine m1 = new Medicine("Paracetamol");
        Medicine m2 = new Medicine("Ibuprofen");
        Medicine m3 = new Medicine("Amoxicillin");

        medicineRepository.saveAll(List.of(m1, m2, m3));

        Prescription prescription = new Prescription(
                drSmith,
                alice,
                List.of(m1, m2)
        );
        prescription.setNotes("Test 1");

        prescriptionRepository.save(prescription);

        // FETCHING PRESCRIPTION WITH COMPOSITE KEY
        PrescriptionId key = new PrescriptionId(
                drSmith.getId(),
                alice.getId()
        );

        Optional<Prescription> fetched = prescriptionRepository.findById(key);
        if (fetched.isPresent()) {
            Prescription p = fetched.get();
            System.out.println("Found prescription for Dr. " + p.getDoctorId().getName()
            + " -> Patient: " + p.getPatientId().getName());
            System.out.println("Medicines Prescribed");
            p.getMedicines().forEach(
                    medicine -> System.out.println(" . " + medicine.getName())
            );
        }
    }
}
