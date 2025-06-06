package com.demo.healthcare.demo.jpql;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LearnQueries {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public void execute(EntityManager entityManager){

        List<Patient> older = patientRepository.findOrderThan(30);
        older.forEach(p -> System.out.println("Older than 30: "
                + p.getName()));

        List<Patient> nameMatch = patientRepository.findByNameStartingWith("s%");
        nameMatch.forEach(p -> System.out.println("Names Match: "
                + p.getName()));

        List<Patient> genderFilters = patientRepository.findByGenders(List.of(Gender.MALE, Gender.OTHER));
        genderFilters.forEach(p -> System.out.println("Gender Match: "
                + p.getName()));

        List<Patient> ageRange = patientRepository.findByAgeRange(32, 40);
        ageRange.forEach(p -> System.out.println("Age Range Match: "
                + p.getName()));

        List<Patient> unassignedPatients = patientRepository.findUnassignedPatients();
        unassignedPatients.forEach(p -> System.out.println("Unassigned Patients: "
                + p.getName()));


        // SORTING RESULTS
        List<Patient> sortBy = patientRepository.sortByAge();
        sortBy.forEach(p -> System.out.println("Sorted Data: "
                + p.getName()));

        List<Patient> sortAndFilter = patientRepository.findByGenderSortByAge(Gender.MALE);
        sortAndFilter.forEach(p -> System.out.println("Sorted Data: "
                + p.getName()));

        // JOINS
        List<Patient> innerJoin = patientRepository.findPatientsWithDoctor("Cardiology");
        innerJoin.forEach(p -> System.out.println("innerJoin Data: "
                + p.getName()));


        List<Patient> leftJoin = patientRepository.findPatientsWithDoctorLeft();
        leftJoin.forEach(p -> System.out.println("leftJoin Data: "
                + p.getName()));
//        leftJoin.forEach(p -> {
//            System.out.println("leftJoin Data: "
//                    + p.getName());
//            System.out.println("DOCTOR NAME : " + p.getDoctor().getName());
//        });


        List<Patient> joinFetch = patientRepository.findPatientsWithDoctorJoinFetch();
        joinFetch.forEach(p -> {
            System.out.println("joinFetch Data: "
                    + p.getName());
            System.out.println("DOCTOR NAME : " + p.getDoctor().getName());
        });

        // AGGREGATION
        System.out.println("AGGREGATION ");
        List<Object[]> results = doctorRepository.countOfPatientsBySpecialization("Cardiology");
        for (Object[] row : results) {
            String spec = (String) row[0];
            Long patientCount = (Long) row[1];
            System.out.println("SPEC: " + spec + " : Patients: " + patientCount);
        }

        System.out.println("AGGREGATION ");
        List<Object[]> resultsAgeAvg = patientRepository.averageAgeByGender();
        for (Object[] row : resultsAgeAvg) {
            Gender gender = (Gender) row[0];
            Double avgAge = (Double) row[1];
            System.out.println("GENDER: " + gender + " : avgAge: " + avgAge);
        }

        // BULK UPDATE
//        int count = patientRepository.bulkIncreaseAge();
//        System.out.println("BULK UPDATE DONE: " + count);
//
//        int deleteCount = patientRepository.bulkDeleteByAge(32);
//        System.out.println("BULK DELETE DONE: " + deleteCount);

        // SUMMARIES
        List<PatientSummary> summaries = patientRepository.getPatientSummary();
        summaries.forEach(System.out::println);

        // Named Queries
        System.out.println("Named Queries");
//        List<Patient> patientList = entityManager.createNamedQuery("Patient.findByNameStartingWith")
//                .setParameter("prefix", "J%")
//                .getResultList();

        List<Patient> patientList = patientRepository
                .findByNameStartingWith("J%");

//        System.out.println(patientList);
        patientList.forEach(p -> System.out.println("Named Queries: "
                + p.getName()));



    }
}
