package example.b;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class StudentCRUD {

    public static Integer create(String name, String email, int age) {
        Transaction tx = null;
        Integer id;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Student s = new Student(name, email, age);
            id = (Integer) session.save(s);
            tx.commit();
            return id;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public static Student read(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        }
    }

    public static List<Student> readAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from example.b.Student", Student.class).list();
        }
    }

    public static void update(Integer id, String newEmail) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Student s = session.get(Student.class, id);
            if (s != null) {
                s.setEmail(newEmail);
                session.update(s);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public static void delete(Integer id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Student s = session.get(Student.class, id);
            if (s != null) session.delete(s);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public static void main(String[] args) {
        Integer id = create("Riya", "riya@example.com", 21);
        System.out.println("Inserted id: " + id);

        Student s = read(id);
        System.out.println("Read student: " + s.getName() + " / " + s.getEmail());

        update(id, "riya.new@example.com");
        System.out.println("Updated email.");

        readAll().forEach(st -> System.out.println(st.getId() + ": " + st.getName()));

        delete(id);
        System.out.println("Deleted student.");

        HibernateUtil.shutdown();
    }
}
