package com.example.jpademo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class JPADemo {

    public static void main(String[] args) {
        SpringApplication.run(JPADemo.class, args);
    }
}

@Component
class JPAService implements CommandLineRunner {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try {
            UserInfo user = new UserInfo("Alice", "alice@email.com");

//            entityManager.getTransaction().begin();
            entityManager.persist(user);
//            entityManager.getTransaction().commit();

            System.out.println("User saved with ID: " + user.getId());
        } finally {
//            entityManager.close();
        }
    }
}
