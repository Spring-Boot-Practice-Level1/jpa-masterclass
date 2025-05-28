import jakarta.persistence.EntityManager;

public class HibernateDemo {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            UserInfo user = new UserInfo("Alice", "alice@email.com");

            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();

            System.out.println("User saved with ID: " + user.getId());
        } finally {
            entityManager.close();
            JPAUtil.close();
        }
    }
}
