package com.demo.healthcare.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@IdClass(PrescriptionId.class)
public class Prescription {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctorId;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patientId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "prescription_medicine",
            joinColumns = {
                    @JoinColumn(name = "prescription_doctor_id", referencedColumnName = "doctor_id"),
                    @JoinColumn(name = "prescription_patient_id", referencedColumnName = "patient_id")
            },
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> medicines;

    private String notes;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "prescription_medicine",
//            joinColumns = @JoinColumn(name = "prescription_id"),
//            inverseJoinColumns = @JoinColumn(name = "medicine_id")
//    )
//    private List<Medicine> medicines;


    public Prescription() {
    }

    public Prescription(Doctor doctorId, Patient patientId, List<Medicine> medicines) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.medicines = medicines;
    }


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
